package com.springjpa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.entity.*;
import com.springjpa.repository.*;

import jakarta.transaction.Transactional;

@Service
public class PretService {

    @Autowired
    private AdherantRepository adherantRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private TypePretRepository typePretRepository;

    @Autowired
    private BibliothecaireRepository bibliothecaireRepository;

    @Autowired
    private DureePretRepository dureePretRepository;   
    
    @Autowired
    private ExemplaireStatutRepository exemplaireStatutRepository;
    
    @Autowired
    private StatutExemplaireRepository statutExemplaireRepository;
    
    @Autowired
    private PenaliteRepository penaliteRepository;
    

    @Autowired
    private ProlongementRepository prolongementRepository;

    @Autowired
    private JourFerieRepository jourFerieRepository;

    @Autowired
    private WeekendRepository weekendRepository;

    @Autowired
    private RetourRepository retourRepository;

    public void effectuerPret(Integer idAdherant, Integer idExemplaire, Integer idTypePret, LocalDateTime inputDateDebut) {
        Adherant adherant = getAdherantOrThrow(idAdherant);
        Exemplaire exemplaire = getExemplaireOrThrow(idExemplaire);
    
        LocalDateTime datePret = inputDateDebut != null ? inputDateDebut : LocalDateTime.now();
    
        TypePret typePret = getTypePretOrThrow(idTypePret);
        verifierPenaliteAdherant(adherant, datePret.toLocalDate());
        if (!"Sur place".equalsIgnoreCase(typePret.getType())) {
            verifierQuotaPret(adherant, datePret);
        }
        verifierAbonnementActif(adherant, datePret);
        // verifierAucunPretNonRetourne(adherant);
        verifierAucunPretEchuNonRetourne(adherant, datePret);

    
    
        Bibliothecaire bibliothecaire = getBibliothecaireParDefaut();
    
        Pret pret = new Pret();
        pret.setDateDebut(datePret);
        pret.setAdherant(adherant);
        pret.setExemplaire(exemplaire);
        pret.setTypePret(typePret);
        pret.setBibliothecaire(bibliothecaire);
    
        if ("Sur place".equalsIgnoreCase(typePret.getType())) {
            pret.setDateFin(datePret);
        } else {
            int duree = getDureePretPourProfil(adherant.getProfil());
            LocalDateTime dateFinPrevue = datePret.plusDays(duree);
            verifierAbonnementActif(adherant, dateFinPrevue);
            
            // si adherent n'est plus abonne a la dateFinPrevue
            // Abonnement abonnementActuel = abonnementRepository
            //     .findByAdherant_IdAdherant(idAdherant).stream()
            //     .filter(ab -> 
            //         !ab.getDateDebut().isAfter(datePret) &&
            //         !ab.getDateFin().isBefore(datePret)
            //     )
            //     .findFirst()
            //     .orElseThrow(() -> new IllegalStateException("Aucun abonnement actif trouvé pour cette date"));
            // LocalDateTime dateFinLimitee = abonnementActuel.getDateFin();
            // if (dateFinPrevue.isAfter(dateFinLimitee)) {
            //     dateFinPrevue = dateFinLimitee;
            // }

            LocalDateTime dateFinAjustee = ajusterSiNonOuvrable(dateFinPrevue);
            pret.setDateFin(dateFinAjustee);
        }

        // Vérifier si l’exemplaire est actuellement indisponible
        Optional<ExemplaireStatut> dernierStatut = exemplaireStatutRepository.findDernierStatut(exemplaire.getIdExemplaire());

        if (dernierStatut.isPresent() && "indisponible".equalsIgnoreCase(dernierStatut.get().getStatutExemplaire().getNomStatut())) {
            throw new IllegalStateException("Cet exemplaire est actuellement indisponible.");
        }

    
        pretRepository.save(pret);

        StatutExemplaire statutIndispo = statutExemplaireRepository.findByNomStatut("indisponible")
            .orElseThrow(() -> new IllegalStateException("Statut 'indisponible' introuvable"));

        ExemplaireStatut nouveauStatut = new ExemplaireStatut();
        nouveauStatut.setExemplaire(exemplaire);
        nouveauStatut.setStatutExemplaire(statutIndispo);
        nouveauStatut.setDateStatut(LocalDateTime.now());

        exemplaireStatutRepository.save(nouveauStatut);

    }

    // private void verifierAucunPretNonRetourne(Adherant adherant) {
    //     List<Pret> prets = pretRepository.findByAdherant_IdAdherant(adherant.getIdAdherant());
    
    //     for (Pret pret : prets) {
    //         boolean retourExiste = retourRepository.existsByPret_IdPret(pret.getIdPret());
    //         if (!retourExiste) {
    //             throw new IllegalStateException("Vous avez un prêt non encore retourné (ID prêt : " + pret.getIdPret() + "). Impossible de faire un nouveau prêt.");
    //         }
    //     }
    // }
    
    private void verifierAucunPretEchuNonRetourne(Adherant adherant, LocalDateTime dateNouveauPret) {
        List<Pret> anciensPrets = pretRepository.findByAdherant_IdAdherant(adherant.getIdAdherant());
    
        for (Pret pret : anciensPrets) {
            boolean estRetourne = retourRepository.existsByPret_IdPret(pret.getIdPret());
            boolean estDepasse = pret.getDateFin().isBefore(dateNouveauPret);
    
            if (!estRetourne && estDepasse) {
                throw new IllegalStateException(
                    "Vous avez un prêt (ID " + pret.getIdPret() + ") dont la date de retour est dépassée mais non encore retourné. Impossible de faire un nouveau prêt."
                );
            }
        }
    }
    
    private Adherant getAdherantOrThrow(Integer idAdherant) {
        return adherantRepository.findById(idAdherant)
            .orElseThrow(() -> new IllegalArgumentException("Adhérent introuvable"));
    }
    
    private Exemplaire getExemplaireOrThrow(Integer idExemplaire) {
        return exemplaireRepository.findById(idExemplaire)
            .orElseThrow(() -> new IllegalArgumentException("Exemplaire introuvable"));
    }
    
    private void verifierAbonnementActif(Adherant adherant, LocalDateTime dateCheck) {
        boolean actif = abonnementRepository.existsByAdherant_IdAdherantAndDateDebutBeforeAndDateFinAfter(
            adherant.getIdAdherant(), dateCheck, dateCheck
        );
        if (!actif) {
            throw new IllegalStateException("Adhérent inactif à la date de prêt");
        }
    }
    
    private TypePret getTypePretOrThrow(Integer idTypePret) {
        return typePretRepository.findById(idTypePret)
            .orElseThrow(() -> new IllegalStateException("Type de prêt introuvable"));
    }
    
    private Bibliothecaire getBibliothecaireParDefaut() {
        return bibliothecaireRepository.findById(1)
            .orElseThrow(() -> new IllegalStateException("Bibliothécaire par défaut non trouvé"));
    }
    
    private int getDureePretPourProfil(Profil profil) {
        DureePret dureePret = dureePretRepository.findByProfil(profil)
            .orElseThrow(() -> new IllegalStateException("Durée de prêt non définie pour le profil"));
        return dureePret.getDuree();
    }

    private void verifierQuotaPret(Adherant adherant, LocalDateTime datePret) {
        int quotaMax = adherant.getProfil().getQuotaPret();
    
        TypePret surPlace = typePretRepository.findByType("Sur place")
            .orElseThrow(() -> new IllegalStateException("Type de prêt 'Sur place' introuvable"));
    
        long nombrePretsActifs = pretRepository.countPretsActifsHorsSurPlace(
            adherant.getIdAdherant(), datePret, surPlace
        );
    
        if (nombrePretsActifs >= quotaMax) {
            throw new IllegalStateException("Quota de prêt atteint. Vous avez déjà " + nombrePretsActifs + " prêts actifs.");
        }
    }

    private void verifierPenaliteAdherant(Adherant adherant, LocalDate datePret) {
        boolean penalise = penaliteRepository.isAdherantPenalise(adherant.getIdAdherant(), datePret);
        if (penalise) {
            throw new IllegalStateException("L'adhérent est pénalisé à cette date et ne peut pas emprunter.");
        }
    }

    public List<Pret> listerPretsParAdherant(Integer idAdherant) {
        return pretRepository.findByAdherant_IdAdherant(idAdherant);
    }

    // public void demanderProlongement(Integer idPret) {
    //     Pret pret = pretRepository.findById(idPret)
    //         .orElseThrow(() -> new IllegalArgumentException("Prêt introuvable"));

    //     Prolongement prolongement = new Prolongement();
    //     prolongement.setPret(pret);
    //     prolongement.setDateProlongement(pret.getDateFin());
    //     prolongement.setStatut("en attente");

    //     prolongementRepository.save(prolongement);
    // }

    // @Transactional
    // public void demanderProlongement(Integer idPret) {
    //     Pret pret = pretRepository.findById(idPret)
    //         .orElseThrow(() -> new IllegalArgumentException("Prêt introuvable"));

    //     Adherant adherant = pret.getAdherant();

    //     // Compter les prolongements en attente pour cet adhérent
    //     long nbProlongementsEnAttente = prolongementRepository.countProlongementsEnAttenteByAdherantId(adherant.getIdAdherant());

    //     // Vérifier si le quota est dépassé
    //     if (nbProlongementsEnAttente >= adherant.getProfil().getProlongement()) {
    //         throw new IllegalStateException("Quota de prolongement en attente dépassé.");
    //     }

    //     // Créer la demande de prolongement
    //     Prolongement prolongement = new Prolongement();
    //     prolongement.setPret(pret);
    //     prolongement.setDateProlongement(pret.getDateFin());
    //     prolongement.setStatut("en attente");

    //     prolongementRepository.save(prolongement);
    // }

    @Transactional
public void demanderProlongement(Integer idPret) {
    Pret pret = pretRepository.findById(idPret)
        .orElseThrow(() -> new IllegalArgumentException("Prêt introuvable"));

    Adherant adherant = pret.getAdherant();
    Profil profil = adherant.getProfil();

    // 1. Obtenir la durée de prêt pour le profil
    int dureePret = dureePretRepository.findByProfil(profil)
        .orElseThrow(() -> new IllegalStateException("Durée de prêt non définie pour ce profil"))
        .getDuree();

    // 2. Récupérer les prolongements "en attente" de cet adhérent
    List<Prolongement> enAttente = prolongementRepository.findEnAttenteByAdherant(adherant.getIdAdherant());

    // 3. Filtrer ceux dont la période est encore active maintenant
    LocalDateTime now = LocalDateTime.now();
    long nbActifs = enAttente.stream()
        .filter(p -> {
            LocalDateTime debut = p.getDateProlongement();
            LocalDateTime fin = debut.plusDays(dureePret);
            return !now.isBefore(debut) && now.isBefore(fin);
        })
        .count();

    // 4. Vérifier le quota
    if (nbActifs >= profil.getProlongement()) {
        throw new IllegalStateException("Quota de prolongements en cours atteint.");
    }

    // 5. Créer le nouveau prolongement
    Prolongement prolongement = new Prolongement();
    prolongement.setPret(pret);
    prolongement.setDateProlongement(pret.getDateFin());
    prolongement.setStatut("en attente");

    prolongementRepository.save(prolongement);
}


    public LocalDateTime ajusterSiNonOuvrable(LocalDateTime dateInitiale) {
        int decalageWeekend = Optional.ofNullable(weekendRepository.findPremier())
                                      .map(Weekend::getDecalage)
                                      .orElse(1); // Par défaut +1 si absent
    
        LocalDateTime date = dateInitiale;
    
        while (estWeekend(date.toLocalDate()) || estFerie(date.toLocalDate())) {
            if (estWeekend(date.toLocalDate())) {
                date = date.plusDays(decalageWeekend);
            } else if (estFerie(date.toLocalDate())) {
                // Récupérer le décalage spécifique du jour férié
                int decalageFerie = jourFerieRepository.findByDate(date.toLocalDate())
                    .map(JourFerie::getDecalage)
                    .orElse(1);
                date = date.plusDays(decalageFerie);
            }
        }
    
        return date;
    }
    
    
    private boolean estWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 7; 
    }
    
    private boolean estFerie(LocalDate date) {
        return jourFerieRepository.existsByDate(date);
    }
}
