package fr.m2i.projet_voiture_scrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SuppressionProduit {

    public static void supprimerUnProduit(Connection connexion, String marque, String modele, String couleur, int annee, int quantiteARetirer) throws SQLException {
        if (quantiteARetirer <= 0) {
            System.out.println("La quantité à retirer doit être supérieure à zéro.");
            return;
        }

        String sql = "SELECT quantite FROM produit WHERE marque = ? AND modele = ? AND couleur = ? AND annee = ?";
        PreparedStatement psSelect = connexion.prepareStatement(sql);
        psSelect.setString(1, marque);
        psSelect.setString(2, modele);
        psSelect.setString(3, couleur);
        psSelect.setInt(4, annee);

        ResultSet rs = psSelect.executeQuery();
        if (rs.next()) {
            int quantiteDispo = rs.getInt("quantite");
            int nouvelleQuantite = quantiteDispo - quantiteARetirer;
            if (nouvelleQuantite < 0) {
                System.out.println("Impossible de retirer " + quantiteARetirer + " produit(s). Quantité disponible insuffisante.");
            } else if (nouvelleQuantite == 0) {
                // Supprimer le produit si la quantité devient zéro
                supprimerProduit(connexion, marque, modele, couleur, annee);
            } else {
                // Diminuer la quantité du produit
                diminuerQuantite(connexion, marque, modele, couleur, annee, nouvelleQuantite);
            }
        } else {
            System.out.println("Aucun produit correspondant n'a été trouvé.");
        }
    }

    private static void supprimerProduit(Connection connexion, String marque, String modele, String couleur, int annee) throws SQLException {
        String sql = "DELETE FROM produit WHERE marque = ? AND modele = ? AND couleur = ? AND annee = ?";
        PreparedStatement psDelete = connexion.prepareStatement(sql);
        psDelete.setString(1, marque);
        psDelete.setString(2, modele);
        psDelete.setString(3, couleur);
        psDelete.setInt(4, annee);
        psDelete.executeUpdate();
        System.out.println("Produit de marque " + marque + ", modèle " + modele + ", couleur " + couleur + " pour l'année " + annee + " supprimé avec succès.");
    }

    private static void diminuerQuantite(Connection connexion, String marque, String modele, String couleur, int annee, int nouvelleQuantite) throws SQLException {
        String sql = "UPDATE produit SET quantite = ? WHERE marque = ? AND modele = ? AND couleur = ? AND annee = ?";
        PreparedStatement psUpdate = connexion.prepareStatement(sql);
        psUpdate.setInt(1, nouvelleQuantite);
        psUpdate.setString(2, marque);
        psUpdate.setString(3, modele);
        psUpdate.setString(4, couleur);
        psUpdate.setInt(5, annee);
        psUpdate.executeUpdate();
        System.out.println("Quantité du produit de marque " + marque + ", modèle " + modele + ", couleur " + couleur + " pour l'année " + annee + " diminuée avec succès.");
    }
    

}


