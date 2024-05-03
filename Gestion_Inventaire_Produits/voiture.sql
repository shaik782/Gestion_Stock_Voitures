CREATE DATABASE IF NOT EXISTS voiture;

USE voiture;

CREATE TABLE IF NOT EXISTS produit (
    numSerie INT AUTO_INCREMENT PRIMARY KEY,
    marque VARCHAR(50),
    modele VARCHAR(50),
    categorie VARCHAR(50),
    couleur VARCHAR(50),
	nombrePlace int(3),
    annee int(4),
    prix float,
    quantite int
);
