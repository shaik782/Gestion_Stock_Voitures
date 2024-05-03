package src;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class InsererProduitConsole {

	private static Scanner sc = new Scanner(System.in);

	public static void Inserer() {

		Connection connexion = Connexion.Connect();
		
		

		// Insérer voiture
		Voiture voiture = createProduct();

		try {
			AjoutProduit.ajouterUnProduit(connexion, voiture.getMarque(), voiture.getModele(), voiture.getCategorie(),
					voiture.getCouleur(),voiture.getNombrePlace(), voiture.getAnnee(), voiture.getPrix(), voiture.getQuantite());
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'ajout");
			e.printStackTrace();
		}
		}

	private static Voiture createProduct() {
		Voiture voiture = new Voiture();
		try {

			System.out.println("========================= AJOUT PRODUIT =================================");
			System.out.println("Indiquez la marque : ");
			voiture.setMarque(sc.next());
			System.out.println("Indiquez le modele : ");
			voiture.setModele(sc.next());
			System.out.println("Indiquez la categorie : ");
			voiture.setCategorie(sc.next());
			System.out.println("Indiquez la couleur : ");
			voiture.setCouleur(sc.next());
			System.out.println("Indiquez le nombre de Place : ");
			voiture.setNombrePlace(sc.nextInt());
			System.out.println("Indiquez l'annee : ");
			voiture.setAnnee(sc.nextInt());
			System.out.println("Indiquez le prix : ");
			voiture.setPrix(sc.nextFloat());
			System.out.println("Indiquez la quantite : ");
			voiture.setQuantite(sc.nextInt());

		} catch (RuntimeException e) {
			System.out.println("IL FAUT SAISIR DE NOUVEAU !");
			return createProduct();
		}
		return voiture;
	

	}

}
