package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class MainUpdateProd {
	private static Scanner sc = new Scanner(System.in);
	private static final String URL = "jdbc:mysql://localhost:3306/voiture?useSSL=false&serverTimezone=UTC";
	private static final String LOGIN = "root";
	private static final String PSW = "root";

	 public static void main(String[] args) {
	//	 save(new Voiture(int numSerie, String marque, String modele, String categorie, String couleur, int nombrePlace,int annee, double prix, int quantite)); 
		 save(new Voiture());
	 
	 }

	public static void save(Voiture obj) {
		System.out.println("SAVING.....");
		try {
			// MainConnection con = new MainConnection();
			Connection con = DriverManager.getConnection(URL, LOGIN, PSW);
			System.out.println("Je suis connecté à ma base de données :-D");
			// Insérer voiture
			Voiture voiture = updateProduct();
			if (obj.getNumSerie() != 0) {
				PreparedStatement ps = con.prepareStatement("UPDATE produit set marque=?, modele=?, categorie=?, couleur=?, nombrePlace=?, annee=?, prix=? ,quantite=? WHERE numSerie=?");

				ps.setString(1, obj.getMarque());
				ps.setString(2, obj.getModele());
				ps.setString(3, obj.getCategorie());
				ps.setString(4, obj.getCouleur());
				ps.setInt(5, obj.getNombrePlace());
				ps.setInt(6, obj.getAnnee());
				ps.setDouble(7, obj.getPrix());
				ps.setInt(8, obj.getQuantite());
				ps.executeUpdate();
				System.out.println("UPDATE OK");
			} else {
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO produit (marque, modele, categorie, couleur, nombrePlace, annee, prix ,quantite) VALUES(?,?,?,?,?,?,?,?)");
				ps.setString(1, obj.getMarque());
				ps.setString(2, obj.getModele());
				ps.setString(3, obj.getCategorie());
				ps.setString(4, obj.getCouleur());
				ps.setInt(5, obj.getNombrePlace());
				ps.setInt(6, obj.getAnnee());
				ps.setDouble(7, obj.getPrix());
				ps.setInt(8, obj.getQuantite());

				ps.executeUpdate();
				System.out.println("ADD OK");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("SAVED NO");
		}

	}

	private static Voiture updateProduct() {
		Voiture voiture = new Voiture();
		try {

			System.out.println("========================= Modifier PRODUIT =================================");
			System.out.println("Marque à modifier: ");
			voiture.setMarque(sc.next());
			System.out.println("Modele à modifier: ");
			voiture.setModele(sc.next());
			System.out.println("Categorie à modifier : ");
			voiture.setCategorie(sc.next());
			System.out.println("Ccouleur à modifier : ");
			voiture.setCouleur(sc.next());
			System.out.println("Nombre de Place à modifier : ");
			voiture.setNombrePlace(sc.nextInt());
			System.out.println("Annee à modifier : ");
			voiture.setAnnee(sc.nextInt());
			System.out.println("Prix à modifier: ");
			voiture.setPrix(sc.nextDouble());
			System.out.println("Quantite à modifier : ");
			voiture.setQuantite(sc.nextInt());

		} catch (RuntimeException e) {
			System.out.println("IL FAUT SAISIR DE NOUVEAU !");
			return updateProduct();

		}
		System.out.println("========================= Produit Après update =================================");

		return voiture;

	}

}
