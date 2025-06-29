-----------------------------------------------------
--              BASE DE DONNEES                    --
--               SAE Secouriste                    --
-- @autor                              J.maillard  --
-----------------------------------------------------
USE bd_sae_secouristes;
-----------------------------------------------------
--             REMPLISSAGE DES TABLES              --
-----------------------------------------------------

USE bd_sae_secouristes;

SET SQL_SAFE_UPDATES = 0;

-- Suppression des données dans l'ordre des dépendances inversées

DELETE FROM users;
DELETE FROM administrateur;

DELETE FROM affectation;
DELETE FROM secouriste_journee;
DELETE FROM secouriste_competence;
DELETE FROM besoin;

DELETE FROM dps;
DELETE FROM journee;
DELETE FROM site;
DELETE FROM sport;

DELETE FROM competence;
DELETE FROM secouriste;

-- Réinitialisation des identifiants auto-incrémentés
ALTER TABLE users AUTO_INCREMENT = 1;
ALTER TABLE dps AUTO_INCREMENT = 1;

-- COMPÉTENCES
INSERT INTO competence (intitule) VALUES
('CO'), ('CP'), ('CE'), ('PBF'), ('PSE1'), ('PSE2'), ('SSA'), ('VPSP');

-- JOURNÉES DES JO (mi-février 2025)
INSERT INTO journee (jour, mois, annee) VALUES
(14, 2, 2025), (15, 2, 2025), (16, 2, 2025), (17, 2, 2025), (18, 2, 2025);

-- SPORTS
INSERT INTO sport (code, nom) VALUES
('SKI', 'Ski Alpin'),
('PAT', 'Patinage Artistique'),
('HOCK', 'Hockey sur Glace'),
('BIAT', 'Biathlon');

-- SITES
INSERT INTO site (code, nom, longitude, latitude) VALUES
('VALT', 'Val Thorens', 6.5785, 45.2974),
('CHAM', 'Chamonix', 6.8694, 45.9237),
('GRNV', 'Grenoble Ice Arena', 5.7245, 45.1885),
('PARI', 'Paris Grand Palais Éphémère', 2.3124, 48.8566);

-- SECURISTES
INSERT INTO secouriste (id, nom, prenom, dateNaissance, email, tel, adresse) VALUES
(1, 'Durand', 'Luc', '1980-03-15', 'luc.durand@mail.com', '0611223344', '5 rue Neige, Chamonix'),
(2, 'Morel', 'Sophie', '1992-07-12', 'sophie.morel@mail.com', '0622334455', '21 bd Glace, Paris'),
(3, 'Bernard', 'Julien', '1985-01-25', 'julien.bernard@mail.com', '0633445566', '4 avenue Ski, Lyon'),
(4, 'Robert', 'Claire', '1990-11-30', 'claire.robert@mail.com', '0644556677', '33 rue Montagne, Grenoble'),
(5, 'Lemoine', 'Axel', '1988-09-09', 'axel.lemoine@mail.com', '0655667788', '18 rue Glacière, Marseille'),
(6, 'Girard', 'Emma', '1995-05-20', 'emma.girard@mail.com', '0666778899', '9 rue des Cimes, Paris'),
(7, 'Petit', 'Hugo', '1997-08-02', 'hugo.petit@mail.com', '0677889900', '76 avenue des JO, Paris'),
(8, 'Faure', 'Nina', '1982-02-10', 'nina.faure@mail.com', '0688990011', '19 bd du Repos, Grenoble'),
(9, 'Renard', 'Tom', '1993-04-17', 'tom.renard@mail.com', '0699001122', '1 rue Igloo, Chamonix'),
(10, 'Lopez', 'Julie', '1987-06-30', 'julie.lopez@mail.com', '0600112233', '7 chemin des Glaciers, Val d’Isère');

-- COMPÉTENCES PAR SECOURISTE (au moins une de chaque)
INSERT INTO secouriste_competence (secouriste_id, competence_intitule) VALUES
(1, 'CO'), (2, 'CP'), (3, 'CE'), (4, 'PBF'), (5, 'PSE1'),
(6, 'PSE2'), (7, 'SSA'), (8, 'VPSP'), (9, 'PSE1'), (10, 'PSE2');

-- DPS (événements à différents lieux et horaires)
INSERT INTO dps (id, horaire_depart, horaire_fin, jour, mois, annee, site_code, sport_code) VALUES
(1, 8, 16, 14, 2, 2025, 'VALT', 'SKI'),
(2, 10, 18, 14, 2, 2025, 'GRNV', 'HOCK'),
(3, 12, 20, 15, 2, 2025, 'PARI', 'PAT'),
(4, 8, 14, 16, 2, 2025, 'CHAM', 'BIAT'),
(5, 14, 22, 17, 2, 2025, 'VALT', 'SKI');

-- BESOINS PAR DPS
INSERT INTO besoin (dps_id, competence_intitule, nombre) VALUES
(1, 'PSE1', 2), (1, 'CP', 1),
(2, 'PSE2', 2), (2, 'SSA', 1),
(3, 'PBF', 2), (3, 'CE', 1),
(4, 'VPSP', 1), (4, 'PSE1', 1),
(5, 'CO', 1), (5, 'PSE2', 2);

-- DISPONIBILITÉS
INSERT INTO secouriste_journee (secouriste_id, jour, mois, annee) VALUES
(1, 14, 2, 2025), (2, 14, 2, 2025), (3, 15, 2, 2025),
(4, 15, 2, 2025), (5, 14, 2, 2025), (6, 16, 2, 2025),
(7, 17, 2, 2025), (8, 16, 2, 2025), (9, 17, 2, 2025), (10, 18, 2, 2025);

-- AFFECTATIONS
INSERT INTO affectation (secouriste_id, dps_id) VALUES
(1, 1), (2, 1),
(3, 3), (4, 3),
(5, 1), (6, 4),
(7, 2), (8, 4),
(9, 5), (10, 5);


-- ADMINISTATEUR
INSERT INTO administrateur(id, nom, prenom) VALUES
(1, 'Durand', 'Kevin'),
(2, 'Morin', 'Marc');


-- UTILISATEURS
INSERT INTO users (username, password_hash, role, admin) VALUES
('admin', 'admin_hash', 'admin', 1),
('morin.marc', 'admin', 'admin', 2);

INSERT INTO users (username, password_hash, role, secouriste) VALUES
('durand.luc', 'pass1', 'secouriste',1),
('morel.sophie', 'pass2', 'secouriste',2),
('bernard.julien', 'pass3', 'secouriste',3),
('robert.claire', 'pass4', 'secouriste',4),
('lemoine.axel', 'pass5', 'secouriste',5);

