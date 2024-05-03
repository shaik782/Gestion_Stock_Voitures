package fr.m2i.projet_voiture.import_export;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.Connexion;

public class Exportation {

    private static final String CSV_FILE_PATH = "export_donnees_voiture.csv";

    public static void main(String[] args) {
        try {
            exportDataToCSV();
        } catch (SQLException | IOException e) {
        	System.err.println("Erreur lors de l'exportation des donnes vers le fichier CSV.");
            e.printStackTrace();
        }
    }

    public static void exportDataToCSV() throws SQLException, IOException {
        try (Connection connexion = Connexion.Connect();
             Statement stmt = connexion.createStatement();
             FileWriter writer = new FileWriter(CSV_FILE_PATH)) {

            // Exécutez la requête pour récupérer les données de la base de données
            String sql = "SELECT numSerie, marque, modele, categorie, couleur, nombrePlace, annee, prix, quantite FROM produit";
            ResultSet rs = stmt.executeQuery(sql);

            // Écrire les données dans le fichier CSV
            writer.append("numSerie,marque,modele,categorie,couleur,nombrePlace,annee,prix,quantite\n");

            while (rs.next()) {
                int numSerie = rs.getInt("numSerie");
                String marque = rs.getString("marque");
                String modele = rs.getString("modele");
                String categorie = rs.getString("categorie");
                String couleur = rs.getString("couleur");
                int nombrePlace = rs.getInt("nombrePlace");
                int annee = rs.getInt("annee");
                float prix = rs.getFloat("prix");
                int quantite = rs.getInt("quantite");

                writer.append(String.join(",", String.valueOf(numSerie), marque, modele, categorie, couleur,
                        String.valueOf(nombrePlace), String.valueOf(annee),
                        String.valueOf(prix), String.valueOf(quantite)));
                writer.append("\n");
            }

            System.out.println("Données exportées avec succès vers " + CSV_FILE_PATH);
        }
    }
}