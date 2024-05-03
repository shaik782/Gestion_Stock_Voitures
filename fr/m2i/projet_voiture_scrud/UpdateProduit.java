package fr.m2i.projet_voiture_scrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProduit {
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
	}


