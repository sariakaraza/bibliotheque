CREATE TABLE auteur(
   id_auteur INT AUTO_INCREMENT,
   nom_auteur VARCHAR(50) NOT NULL,
   prenom_auteur VARCHAR(50),
   PRIMARY KEY(id_auteur)
);

CREATE TABLE editeur(
   id_editeur INT AUTO_INCREMENT,
   nom_editeur VARCHAR(50),
   localisation VARCHAR(50),
   PRIMARY KEY(id_editeur)
);

CREATE TABLE categorie(
   id_categorie INT AUTO_INCREMENT,
   nom_categorie INT NOT NULL,
   PRIMARY KEY(id_categorie)
);

CREATE TABLE profil(
   id_profil INT AUTO_INCREMENT,
   nom_profil VARCHAR(50) NOT NULL,
   quota_pret INT,
   quota_reservation INT,
   cotisation DECIMAL(15,2),
   duree_penalite INT,
   PRIMARY KEY(id_profil)
);

CREATE TABLE admin(
   id_admin INT AUTO_INCREMENT,
   nom_admin VARCHAR(50),
   prenom_admin VARCHAR(50),
   email VARCHAR(100),
   password VARCHAR(50),
   PRIMARY KEY(id_admin)
);

CREATE TABLE type_pret(
   id_type_pret INT AUTO_INCREMENT,
   type VARCHAR(50),
   PRIMARY KEY(id_type_pret)
);

CREATE TABLE duree_pret(
   id_duree_pret INT AUTO_INCREMENT,
   duree INT,
   id_profil INT NOT NULL,
   PRIMARY KEY(id_duree_pret),
   FOREIGN KEY(id_profil) REFERENCES profil(id_profil)
);

CREATE TABLE statut_reservation(
   id_statut_reservation INT AUTO_INCREMENT,
   nom_statut VARCHAR(50),
   PRIMARY KEY(id_statut_reservation)
);

CREATE TABLE statut_prolongement(
   id_statut_prolongement INT AUTO_INCREMENT,
   nom_statut VARCHAR(50),
   PRIMARY KEY(id_statut_prolongement)
);

CREATE TABLE jour_ferie(
   id_jour_ferie INT AUTO_INCREMENT,
   date_jour_ferie DATE,
   PRIMARY KEY(id_jour_ferie)
);

CREATE TABLE statut_exemplaire(
   id_statut_exemplaire INT AUTO_INCREMENT,
   nom_statut VARCHAR(50),
   PRIMARY KEY(id_statut_exemplaire)
);

CREATE TABLE livre(
   id_livre INT AUTO_INCREMENT,
   titre VARCHAR(50),
   annee_publication INT,
   nb_page INT,
   age_restriction INT,
   id_editeur INT NOT NULL,
   id_auteur INT NOT NULL,
   PRIMARY KEY(id_livre),
   FOREIGN KEY(id_editeur) REFERENCES editeur(id_editeur),
   FOREIGN KEY(id_auteur) REFERENCES auteur(id_auteur)
);

CREATE TABLE adherant(
   id_adherant INT AUTO_INCREMENT,
   nom_adherant VARCHAR(50),
   prenom_adherant VARCHAR(50),
   date_naissance DATE,
   email VARCHAR(100),
   password VARCHAR(50),
   id_profil INT NOT NULL,
   PRIMARY KEY(id_adherant),
   FOREIGN KEY(id_profil) REFERENCES profil(id_profil)
);

CREATE TABLE abonnement(
   id_abonnement INT AUTO_INCREMENT,
   date_debut DATETIME,
   date_fin DATETIME,
   id_adherant INT NOT NULL,
   PRIMARY KEY(id_abonnement),
   FOREIGN KEY(id_adherant) REFERENCES adherant(id_adherant)
);

CREATE TABLE penalite(
   id_penalite INT AUTO_INCREMENT,
   duree INT,
   date_penalite DATETIME,
   id_adherant INT NOT NULL,
   PRIMARY KEY(id_penalite),
   FOREIGN KEY(id_adherant) REFERENCES adherant(id_adherant)
);

CREATE TABLE exemplaire(
   id_exemplaire INT AUTO_INCREMENT,
   id_livre INT NOT NULL,
   PRIMARY KEY(id_exemplaire),
   FOREIGN KEY(id_livre) REFERENCES livre(id_livre)
);

CREATE TABLE pret(
   id_pret INT,
   date_debut DATETIME,
   id_admin INT NOT NULL,
   id_type_pret INT NOT NULL,
   id_exemplaire INT NOT NULL,
   id_adherant INT NOT NULL,
   PRIMARY KEY(id_pret),
   FOREIGN KEY(id_admin) REFERENCES admin(id_admin),
   FOREIGN KEY(id_type_pret) REFERENCES type_pret(id_type_pret),
   FOREIGN KEY(id_exemplaire) REFERENCES exemplaire(id_exemplaire),
   FOREIGN KEY(id_adherant) REFERENCES adherant(id_adherant)
);

CREATE TABLE reservation(
   id_reservation INT,
   date_de_reservation DATETIME,
   id_admin INT NOT NULL,
   id_exemplaire INT NOT NULL,
   id_adherant INT NOT NULL,
   PRIMARY KEY(id_reservation),
   FOREIGN KEY(id_admin) REFERENCES admin(id_admin),
   FOREIGN KEY(id_exemplaire) REFERENCES exemplaire(id_exemplaire),
   FOREIGN KEY(id_adherant) REFERENCES adherant(id_adherant)
);

CREATE TABLE retour(
   id_retour INT AUTO_INCREMENT,
   date_retour DATETIME,
   id_pret INT NOT NULL,
   PRIMARY KEY(id_retour),
   FOREIGN KEY(id_pret) REFERENCES pret(id_pret)
);

CREATE TABLE prolongement(
   id_prolongement INT AUTO_INCREMENT,
   date_de_prolongement DATE,
   id_pret INT NOT NULL,
   PRIMARY KEY(id_prolongement),
   UNIQUE(id_pret),
   FOREIGN KEY(id_pret) REFERENCES pret(id_pret)
);

CREATE TABLE categorie_livre(
   id_livre INT,
   id_categorie INT,
   PRIMARY KEY(id_livre, id_categorie),
   FOREIGN KEY(id_livre) REFERENCES livre(id_livre),
   FOREIGN KEY(id_categorie) REFERENCES categorie(id_categorie)
);

CREATE TABLE quota_type_pret(
   id_profil INT,
   id_type_pret INT,
   quota INT,
   PRIMARY KEY(id_profil, id_type_pret),
   FOREIGN KEY(id_profil) REFERENCES profil(id_profil),
   FOREIGN KEY(id_type_pret) REFERENCES type_pret(id_type_pret)
);

CREATE TABLE reservation_statut(
   id_reservation INT,
   id_statut_reservation INT,
   PRIMARY KEY(id_reservation, id_statut_reservation),
   FOREIGN KEY(id_reservation) REFERENCES reservation(id_reservation),
   FOREIGN KEY(id_statut_reservation) REFERENCES statut_reservation(id_statut_reservation)
);

CREATE TABLE categorie_profil(
   id_categorie INT,
   id_profil INT,
   PRIMARY KEY(id_categorie, id_profil),
   FOREIGN KEY(id_categorie) REFERENCES categorie(id_categorie),
   FOREIGN KEY(id_profil) REFERENCES profil(id_profil)
);

CREATE TABLE prolongement_statut(
   id_prolongement INT,
   id_statut_prolongement INT,
   PRIMARY KEY(id_prolongement, id_statut_prolongement),
   FOREIGN KEY(id_prolongement) REFERENCES prolongement(id_prolongement),
   FOREIGN KEY(id_statut_prolongement) REFERENCES statut_prolongement(id_statut_prolongement)
);

CREATE TABLE Asso_23_1(
   id_exemplaire INT,
   id_statut_exemplaire INT,
   PRIMARY KEY(id_exemplaire, id_statut_exemplaire),
   FOREIGN KEY(id_exemplaire) REFERENCES exemplaire(id_exemplaire),
   FOREIGN KEY(id_statut_exemplaire) REFERENCES statut_exemplaire(id_statut_exemplaire)
);