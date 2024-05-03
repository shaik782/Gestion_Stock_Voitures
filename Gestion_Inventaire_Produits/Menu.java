import java.util.Scanner;

public class Menu {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu :");
            System.out.println("1. Rajout Voiture");
            System.out.println("2. Modifier Voiture");
            System.out.println("3. Supprimer Voiture");
            System.out.println("4. Importer Voiture");
            System.out.println("5. Exporter Voiture");
            System.out.println("6. Afficher Voiture");
            System.out.println("7. Quitter");
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Rajout Voiture");
                    break;
                case 2:
                    System.out.println(" Modifier Voiture");
                    // Ajoutez le code de l'option 2 ici
                    break;
                case 3:
                    System.out.println(" Supprimer Voiture");
                    // Ajoutez le code de l'option 3 ici
                    break;
                case 4:
                    System.out.println("Importer Voiture");
                    break;
                case 5:
                    System.out.println("Exporter Voiture");
                    break;
                case 6:
                    System.out.println("Afficher Voiture.");
                    // Ajoutez le code de l'option 2 ici
                    break;
                case 7:
                    System.out.println("Au revoir !");
                    // Ajoutez le code de l'option 3 ici
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        } while (choice != 8);

        scanner.close();
    }
}