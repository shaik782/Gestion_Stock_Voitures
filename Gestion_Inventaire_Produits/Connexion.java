import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Connexion {

	private static final String URL = "jdbc:mysql://localhost:3306/voiture";
	private static final String LOGIN = "root";
	private static final String PSW = "password";

	public static  Connection Connect() {
		Connection connexion = null;
		// Créer une connexion
		try {
			connexion = DriverManager.getConnection(URL, LOGIN, PSW);
		} catch (SQLException e) {
			System.out.println("Erreur de connexion");
			e.printStackTrace();
		}
		return connexion;
	}
}