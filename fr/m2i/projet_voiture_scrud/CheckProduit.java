package fr.m2i.projet_voiture_scrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckProduit {
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
}
