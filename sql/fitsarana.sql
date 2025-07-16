SELECT * FROM Reservation;
SELECT * FROM Retour;
SELECT * FROM StatutReservation;
SELECT * FROM abonnement;
SELECT * FROM adherant;
SELECT * FROM admin;
SELECT * FROM categorie;
SELECT * FROM duree_pret;
SELECT * FROM exemplaire;
SELECT * FROM exemplaire_statut;
SELECT * FROM jour_ferie;
SELECT * FROM livre;
SELECT * FROM penalite;
SELECT * FROM pret;
SELECT * FROM profil;
SELECT * FROM prolongement;
SELECT * FROM quota_type_pret;
SELECT * FROM reservation_statut;
SELECT * FROM statut_exemplaire;
SELECT * FROM statut_reservation;
SELECT * FROM type_pret;
SELECT * FROM weekend;

DELETE FROM Retour;
DELETE FROM Reservation;
DELETE FROM exemplaire_statut;
DELETE FROM exemplaire;
DELETE FROM prolongement;
DELETE FROM pret;
DELETE FROM penalite;
DELETE FROM abonnement;
DELETE FROM adherant;
DELETE FROM livre;
DELETE FROM jour_ferie;
DELETE FROM weekend;
DELETE FROM quota_type_pret;
DELETE FROM duree_pret;
DELETE FROM prolongement;
DELETE FROM categorie;
DELETE FROM profil;

ALTER TABLE Reservation AUTO_INCREMENT = 1;
ALTER TABLE Retour AUTO_INCREMENT = 1;
ALTER TABLE StatutReservation AUTO_INCREMENT = 1;
ALTER TABLE abonnement AUTO_INCREMENT = 1;
ALTER TABLE adherant AUTO_INCREMENT = 1;
ALTER TABLE admin AUTO_INCREMENT = 1;
ALTER TABLE categorie AUTO_INCREMENT = 1;
ALTER TABLE duree_pret AUTO_INCREMENT = 1;
ALTER TABLE exemplaire AUTO_INCREMENT = 1;
ALTER TABLE exemplaire_statut AUTO_INCREMENT = 1;
ALTER TABLE jour_ferie AUTO_INCREMENT = 1;
ALTER TABLE livre AUTO_INCREMENT = 1;
ALTER TABLE penalite AUTO_INCREMENT = 1;
ALTER TABLE pret AUTO_INCREMENT = 1;
ALTER TABLE profil AUTO_INCREMENT = 1;
ALTER TABLE prolongement AUTO_INCREMENT = 1;
ALTER TABLE quota_type_pret AUTO_INCREMENT = 1;
ALTER TABLE reservation_statut AUTO_INCREMENT = 1;
ALTER TABLE statut_exemplaire AUTO_INCREMENT = 1;
ALTER TABLE statut_reservation AUTO_INCREMENT = 1;
ALTER TABLE type_pret AUTO_INCREMENT = 1;
ALTER TABLE weekend AUTO_INCREMENT = 1;

INSERT INTO categorie (nomCategorie) VALUES 
('Littérature classique'),
('Philosophie'),
('Jeunesse / Fantastique');

INSERT INTO livre (titre, auteur)
VALUES
('Les Misérables', 'Victor Hugo'),
('L\'Étranger', 'Albert Camus'),
('Harry Potter à l''école des sorciers', 'J.K. Rowling');

INSERT INTO exemplaire (id_livre) VALUES
(1), -- MIS001
(1), -- MIS002
(1), -- MIS003
(2), -- ETR001
(2), -- ETR002
(3); -- HAR001

INSERT INTO exemplaire_statut (date_statut, id_exemplaire, id_statut_exemplaire) VALUES
(NOW(), 1, 1),
(NOW(), 2, 1),
(NOW(), 3, 1),
(NOW(), 4, 1),
(NOW(), 5, 1),
(NOW(), 6, 1);

ALTER TABLE profil
ADD COLUMN prolongement INT(11) DEFAULT 0;

INSERT INTO profil (cotisation, dureePenalite, nomProfil, quotaPret, quotaReservation, prolongement)
VALUES 
(NULL, 10, 'Etudiant', 2, 1, 3),
(NULL, 9, 'Enseignant', 3, 2, 5),
(NULL, 8, 'Professionnel', 4, 3, 7);

INSERT INTO duree_pret (duree, id_profil)
VALUES
(7, 1),   -- Etudiant
(9, 2),   -- Enseignant
(12, 3);  -- Professionnel

INSERT INTO abonnement (date_debut, date_fin, id_adherant)
VALUES 
('2025-02-01 00:00:00', '2025-07-24 00:00:00', 1),
('2025-02-01 00:00:00', '2025-07-01 00:00:00', 2),
('2025-04-01 00:00:00', '2025-12-01 00:00:00', 3),
('2025-07-01 00:00:00', '2026-07-01 00:00:00', 4),
('2025-08-01 00:00:00', '2026-05-01 00:00:00', 5),
('2025-07-01 00:00:00', '2026-06-01 00:00:00', 6),
('2025-06-01 00:00:00', '2025-12-01 00:00:00', 7),
('2024-10-01 00:00:00', '2025-06-01 00:00:00', 8);

INSERT INTO adherant (email, nomAdherant, password, prenomAdherant, id_profil)
VALUES 
('etu001@gmail.com', 'Bensaïd', 'etu001', 'Amine', 1),
('etu002@gmail.com', 'El Khattabi', 'etu002', 'Sarah', 1),
('etu003@gmail.com', 'Moujahid', 'etu003', 'Youssef', 1),
('ens001@gmail.com', 'Benali', 'ens001', 'Nadia', 2),
('ens002@gmail.com', 'Haddadi', 'ens002', 'Karim', 2),
('ens003@gmail.com', 'Touhami', 'ens003', 'Salima', 2),
('prof001@gmail.com', 'El Mansouri', 'prof001', 'Rachid', 3),
('prof002@gmail.com', 'Zerouali', 'prof002', 'Amina', 3);

INSERT INTO jour_ferie (date_jour_ferie, nom, decalage)
VALUES
('2025-07-13', 'Dimanche', 1),
('2025-07-20', 'Dimanche', 1),
('2025-07-27', 'Dimanche', 1),
('2025-08-03', 'Dimanche', 1),
('2025-08-10', 'Dimanche', 1),
('2025-08-17', 'Dimanche', 1),
('2025-07-26', 'Jour férié', 1),
('2025-07-19', 'Jour férié', 1);

UPDATE exemplaire SET libelle = 'MIS001' WHERE idExemplaire = 1;
UPDATE exemplaire SET libelle = 'MIS002' WHERE idExemplaire = 2;
UPDATE exemplaire SET libelle = 'MIS003' WHERE idExemplaire = 3;
UPDATE exemplaire SET libelle = 'ETR001' WHERE idExemplaire = 4;
UPDATE exemplaire SET libelle = 'ETR002' WHERE idExemplaire = 5;
UPDATE exemplaire SET libelle = 'HAR001' WHERE idExemplaire = 6;

INSERT INTO abonnement (date_debut, date_fin, id_adherant)
VALUES 
('2025-07-25 00:00:00', '2025-12-24 00:00:00', 1);