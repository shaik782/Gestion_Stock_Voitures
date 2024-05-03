import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class MainInsererProduit {


	private static Scanner sc = new Scanner(System.in);
	private static final String URL = "jdbc:mysql://localhost:3306/voiture?useSSL=false&serverTimezone=UTC";
	private static final String LOGIN = "root";
	private static final String PSW = "root";

	public static void main(String[] args) {

		try {
			Connection connexion = DriverManager.getConnection(URL, LOGIN, PSW);

			System.out.println("Connection à la bdd ok");

			// Utiliser la connextion pour utiliser des ordres SQL
			Statement requete = connexion.createStatement();	


			// Insérer voiture
			Voiture voiture = addProduct();
			PreparedStatement ps = connexion.prepareStatement("INSERT INTO produit(marque, modele, categorie, couleur, nombrePlace, annee, prix ,quantite) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1, voiture.getMarque());
			ps.setString(2, voiture.getModele());
			ps.setString(3, voiture.getCategorie());
			ps.setString(4, voiture.getCouleur());
			ps.setInt(5, voiture.getNombrePlace());
			ps.setInt(6, voiture.getAnnee());
			ps.setDouble(7, voiture.getPrix());
			ps.setInt(8, voiture.getQuantite());

			int nombreDeLignesTouchees = ps.executeUpdate();
			System.out.println(nombreDeLignesTouchees + " lignes affectées par la modification");
				// récupérer les info du produit
			ResultSet voitures = requete.executeQuery("select marque, modele, categorie, couleur, nombrePlace, annee ,prix,quantite from produit");
			
			// parcourir les résultats
			ArrayList<Voiture> listeVoitures = new ArrayList<>();
			while (voitures.next()) {
				Voiture voit = new Voiture();
				voit.setMarque(voitures.getString("marque"));
				voit.setModele(voitures.getString("modele"));
				voit.setCategorie(voitures.getString("categorie"));
				voit.setCouleur(voitures.getString("couleur"));
				voit.setNombrePlace(voitures.getInt("nombrePlace"));
				voit.setAnnee(voitures.getInt("annee"));
				voit.setPrix(voitures.getDouble("prix"));
				voit.setQuantite(voitures.getInt("quantite"));
				listeVoitures.add(voit);
			}
			//  Afficher la liste
			System.out.println("=============================");
			for(Voiture v :listeVoitures) {
				System.out.println(v.getMarque()+" - "+ v.getPrix()+" € ");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}


	}
	
	private static Voiture addProduct() {
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
			voiture.setPrix(sc.nextDouble());
			System.out.println("Indiquez la quantite : ");
			voiture.setQuantite(sc.nextInt());

			

		} catch (RuntimeException e) {
			System.out.println("IL FAUT SAISIR DE NOUVEAU !");
			return addProduct();

		}
		System.out.println("========================= Détail du produit ajouté =================================");

		return voiture;
		
	}

}

