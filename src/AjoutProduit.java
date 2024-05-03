package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AjoutProduit {
	public static void ajouterUnProduit(Connection connexion, String marque, String modele, String categorie,
			String couleur, int nombrePlace, int annee, double d, int quantite) throws SQLException {
		PreparedStatement ps = connexion.prepareStatement(
				"INSERT INTO produit(marque, modele, categorie, couleur, nombrePlace, annee, prix, quantite) VALUES(?,?,?,?,?,?,?,?)");
		ps.setString(1, marque);
		ps.setString(2, modele);
		ps.setString(3, categorie);
		ps.setString(4, couleur);
		ps.setInt(5, nombrePlace);
		ps.setInt(6, annee);
		ps.setDouble(7, d);
		ps.setInt(8, quantite);
		ps.executeUpdate();
		ps.close();
	}
}
