import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				if (data.length >= 8) { // Assurez-vous que chaque ligne contient au moins 8 éléments
					String marque = data[0].trim();
					String modele = data[1].trim();
					String categorie = data[2].trim();
					String couleur = data[3].trim();
					int nombrePlace = Integer.parseInt(data[4].trim());
					int annee = Integer.parseInt(data[5].trim());
					float prix = Float.parseFloat(data[6].trim());
					int quantite = Integer.parseInt(data[7].trim());

					// Get connection from Connexion class
					Connection connexion = Connexion.Connect();

					try {
						// Check si la voiture existe dans la base de donnee
						boolean exists = checkIfExists(connexion, marque, modele, categorie, couleur, nombrePlace,
								annee, prix);

						if (exists) {
							// si elle existe update la quantite
							updateQuantite(connexion, marque, modele, categorie, couleur, nombrePlace, annee, prix,
									quantite);
						} else {
							// If it doesn't exist, insert a new row with the provided values
							insertNewRow(connexion, marque, modele, categorie, couleur, nombrePlace, annee, prix,
									quantite);
						}
					} catch (SQLException e) {
						System.err.println("Erreur lors de l'exécution de la requête SQL.");
						e.printStackTrace();
					} finally {
						try {
							connexion.close();
						} catch (SQLException e) {
							System.err.println("Erreur lors de la fermeture de la connexion à la base de données.");
							e.printStackTrace();
						}
					}
				} else {
					System.err.println("Erreur: La ligne ne contient pas assez de données.");
				}
			}
		}
	}

	// Method to check if the combination of marque, modele, categorie, couleur,
	// nombrePlace, annee, and prix already exists in the database
	public static boolean checkIfExists(Connection connexion, String marque, String modele, String categorie,
			String couleur, int nombrePlace, int annee, float prix) throws SQLException {
		PreparedStatement ps = connexion.prepareStatement(
				"SELECT COUNT(*) FROM produit WHERE marque = ? AND modele = ? AND categorie = ? AND couleur = ? AND nombrePlace = ? AND annee = ? AND prix = ?");
		ps.setString(1, marque);
		ps.setString(2, modele);
		ps.setString(3, categorie);
		ps.setString(4, couleur);
		ps.setInt(5, nombrePlace);
		ps.setInt(6, annee);
		ps.setFloat(7, prix);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		rs.close();
		ps.close();
		return count > 0;
	}

	// Fonction qui update la quantite si le produit existe deja
	public static void updateQuantite(Connection connexion, String marque, String modele, String categorie,
			String couleur, int nombrePlace, int annee, float prix, int quantite) throws SQLException {
		PreparedStatement ps = connexion.prepareStatement(
				"UPDATE produit SET quantite = quantite + ? WHERE marque = ? AND modele = ? AND categorie = ? AND couleur = ? AND nombrePlace = ? AND annee = ? AND prix = ?");
		ps.setInt(1, quantite);
		ps.setString(2, marque);
		ps.setString(3, modele);
		ps.setString(4, categorie);
		ps.setString(5, couleur);
		ps.setInt(6, nombrePlace);
		ps.setInt(7, annee);
		ps.setFloat(8, prix);
		ps.executeUpdate();
		ps.close();
	}

	// Fonction qui cree la ligne si le produit n'existe pas
	public static void insertNewRow(Connection connexion, String marque, String modele, String categorie,
			String couleur, int nombrePlace, int annee, float prix, int quantite) throws SQLException {
		PreparedStatement ps = connexion.prepareStatement(
				"INSERT INTO produit(marque, modele, categorie, couleur, nombrePlace, annee, prix, quantite) VALUES(?,?,?,?,?,?,?,?)");
		ps.setString(1, marque);
		ps.setString(2, modele);
		ps.setString(3, categorie);
		ps.setString(4, couleur);
		ps.setInt(5, nombrePlace);
		ps.setInt(6, annee);
		ps.setFloat(7, prix);
		ps.setInt(8, quantite);
		ps.executeUpdate();
		ps.close();
	}
}
