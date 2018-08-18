package baseDeDonnées;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {
	
	public final static String url = "jdbc:mysql://localhost/gestionCommercialeSupermarche?useSSL=false";
	public final static String user = "gaye";
	public final static String password = "gaye";
	static Connection connexion = null;

	public static Connection maConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection(url, user, password);
			return connexion;

		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println(" Il y'a eu une erreur de chargement du pilote ou une erreur de connection au serveur de la  base de données\n");
		}
		return null;
	}
}
