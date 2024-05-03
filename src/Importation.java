package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Importation {

	private static final String CSV_FILE_PATH = "donnees_voiture.csv";

	public static void main(String[] args) {
		try {
			parseCSVData();
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier CSV.");
			e.printStackTrace();
		}
	}

	public static void parseCSVData() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length >= 8) { // Verifier si on a bien 8 elements dans la ligne
					String marque = data[0].trim();
					String modele = data[1].trim();
					String categorie = data[2].trim();
					String couleur = data[3].trim();
					int nombrePlace = Integer.parseInt(data[4].trim());
					int annee = Integer.parseInt(data[5].trim());
					float prix = Float.parseFloat(data[6].trim());
					int quantite = Integer.parseInt(data[7].trim());

					
					Connection connexion = Connexion.Connect();

					try {
						// Check si la voiture existe dans la base de donnee
						boolean exists = CheckProduit.checkIfExists(connexion, marque, modele, categorie, couleur, nombrePlace,
								annee, prix);

						if (exists) {
							// si la voiture existe deja existe update la quantite
							UpdateProduit.updateQuantite(connexion, marque, modele, categorie, couleur, nombrePlace,
									annee, prix, quantite);
						} else {
							// si la voiture n'existe pas créer une nouvelle lione dans la BDD
							AjoutProduit.ajouterUnProduit(connexion, marque, modele, categorie, couleur, nombrePlace,
									annee, prix, quantite);
						}
					} catch (SQLException e) {
						System.err.println("Erreur lors de l'exécution de la requête SQL.");
						e.printStackTrace();
					} 
						try {
							connexion.close();
						} catch (SQLException e) {
							System.err.println("Erreur lors de la fermeture de la connexion à la base de données.");
							e.printStackTrace();
						}
					
				} else {
					System.err.println("Erreur: La ligne ne contient pas assez de données.");
				}
			}
		}
	}

}
