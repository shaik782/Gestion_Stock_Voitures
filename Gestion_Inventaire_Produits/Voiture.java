import java.util.Objects;

public class Voiture {
	private int numSerie;
	private String marque;
	private String modele;
	private String categorie;
	private String couleur;
	private int nombrePlace;
	private int annee;
	private double prix;
	private int quantite;
	
	public int getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(int numSerie) {
		this.numSerie = numSerie;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public int getNombrePlace() {
		return nombrePlace;
	}
	public void setNombrePlace(int nombrePlace) {
		this.nombrePlace = nombrePlace;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Voiture(int numSerie, String marque, String modele, String categorie, String couleur, int nombrePlace,
			int annee, double prix, int quantite) {
		super();
		this.numSerie = numSerie;
		this.marque = marque;
		this.modele = modele;
		this.categorie = categorie;
		this.couleur = couleur;
		this.nombrePlace = nombrePlace;
		this.annee = annee;
		this.prix = prix;
		this.quantite = quantite;
	}
	public Voiture() {
		
	}
	@Override
	public String toString() {
		return "Voiture [numSerie=" + numSerie + ", marque=" + marque + ", modele=" + modele + ", categorie="
				+ categorie + ", couleur=" + couleur + ", nombrePlace=" + nombrePlace + ", annee=" + annee + ", prix="
				+ prix + ", quantite=" + quantite + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(annee, categorie, couleur, marque, modele, nombrePlace, numSerie, prix, quantite);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voiture other = (Voiture) obj;
		return annee == other.annee && Objects.equals(categorie, other.categorie)
				&& Objects.equals(couleur, other.couleur) && Objects.equals(marque, other.marque)
				&& Objects.equals(modele, other.modele) && nombrePlace == other.nombrePlace
				&& numSerie == other.numSerie && Double.doubleToLongBits(prix) == Double.doubleToLongBits(other.prix)
				&& quantite == other.quantite;
	}
	

	
}
