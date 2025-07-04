
INSERT INTO profil (nomProfil, quotaPret, quotaReservation, cotisation, dureePenalite) VALUES
('Enfant', 3, 1, 1000, 5),
('Etudiant', 5, 3, 2000, 10),
('Professeur', 10, 5, 5000, 15);

-- Adhérants
INSERT INTO adherant (nomAdherant, prenomAdherant, dateNaissance, password, email, idProfil) VALUES
('Randria', 'Miora', '2010-05-12', 'pass1', 'miora.randria@example.com', 1),
('Rakoto', 'Jean', '2003-09-21', 'pass2', 'jean.rakoto@example.com', 2),
('Rabe', 'Tiana', '1985-11-10', 'pass3', 'tiana.rabe@example.com', 3),
('Andry', 'Sonia', '2000-01-30', 'pass4', 'sonia.andry@example.com', 2);

-- Admin
INSERT INTO admin (nomAdmin, prenomAdmin, password, email) VALUES 
('Raza', 'Sariaka', 'admin123', 'sariaka.raza@example.com');

INSERT INTO auteur (nom_auteur, prenom_auteur) VALUES
('Verne', 'Jules'),
('Rowling', 'J.K.'),
('Tolkien', 'J.R.R.'),
('Camus', 'Albert'),
('Zola', 'Émile');

INSERT INTO editeur (nom_editeur, localisation) VALUES
('Gallimard', 'Paris'),
('Hachette', 'Paris'),
('Flammarion', 'Lyon'),
('Bayard', 'Toulouse'),
('Actes Sud', 'Arles');

INSERT INTO categorie (nom_categorie) VALUES
('Roman'),
('Science-Fiction'),
('Fantastique'),
('Philosophie'),
('Histoire');

INSERT INTO livre (titre, annee_publication, nb_page, age_restriction, id_editeur, id_auteur) VALUES
('Voyage au centre de la Terre', 1864, 300, 10, 1, 1),
('Harry Potter à l’école des sorciers', 1997, 350, 9, 2, 2),
('Le Seigneur des Anneaux', 1954, 600, 12, 3, 3),
('L’Étranger', 1942, 180, 16, 4, 4),
('Germinal', 1885, 450, 15, 5, 5),
('20 000 lieues sous les mers', 1870, 400, 10, 1, 1),
('Harry Potter et la Chambre des secrets', 1998, 360, 9, 2, 2),
('Le Hobbit', 1937, 310, 10, 3, 3),
('La Peste', 1947, 300, 15, 4, 4),
('L’Assommoir', 1877, 420, 14, 5, 5),
('Les Enfants du capitaine Grant', 1868, 370, 11, 1, 1),
('Harry Potter et le Prisonnier d’Azkaban', 1999, 435, 10, 2, 2),
('Silmarillion', 1977, 500, 14, 3, 3),
('Le Mythe de Sisyphe', 1942, 210, 18, 4, 4),
('La Terre', 1887, 480, 16, 5, 5);

INSERT INTO exemplaire (id_livre) VALUES
(1), (2), (3), (4), (5),
(6), (7), (8), (9), (10),
(11), (12), (13), (14), (15);

INSERT INTO statut_exemplaire (nom_statut) VALUES
('disponible'),
('reserve'),
('indisponible');

-- Enfant : livres 1, 2, 3
INSERT INTO statut_exemplaire_livre VALUES (1, 3), (2, 2), (3, 1);

-- Etudiant : livres 4, 5, 6
INSERT INTO statut_exemplaire_livre VALUES (4, 3), (5, 2), (6, 1);

-- Professeur : livres 7, 8, 9
INSERT INTO statut_exemplaire_livre VALUES (7, 3), (8, 2), (9, 1);

-- Le reste (livres 10 à 15) dispo
INSERT INTO statut_exemplaire_livre VALUES
(10, 1), (11, 1), (12, 1), (13, 1), (14, 1), (15, 1);


-- Enfant
INSERT INTO categorie_profil VALUES (1, 1), (2, 1);

-- Etudiant
INSERT INTO categorie_profil VALUES (2, 2), (3, 2), (5, 2);

-- Professeur
INSERT INTO categorie_profil VALUES (1, 3), (4, 3), (5, 3);


-- Adhérent 1 (janvier à mars 2025)
INSERT INTO abonnement (date_debut, date_fin, id_adherant) VALUES
('2025-01-10', '2025-03-10', 1);

-- Adhérent 2 (février à avril 2025)
INSERT INTO abonnement (date_debut, date_fin, id_adherant) VALUES
('2025-02-15', '2025-08-15', 2);

-- Adhérent 3 (mars à mai 2025)
INSERT INTO abonnement (date_debut, date_fin, id_adherant) VALUES
('2025-03-01', '2025-07-01', 3);

-- Adhérent 4 (avril à juin 2025)
INSERT INTO abonnement (date_debut, date_fin, id_adherant) VALUES
('2025-04-05', '2025-12-05', 4);
