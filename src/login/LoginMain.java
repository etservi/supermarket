package login;

import java.sql.Connection;

import baseDeDonnées.ConnectionDB;
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;

public class LoginMain extends Application {
	
	// LONGUEUR - LARGEUR - DEMARrAGE AVEC IMAGE - COMPLEMENT VOIR LIGNE 33 -------------------
			private static final double height = Screen.getPrimary().getVisualBounds().getHeight();
			private static final double width = Screen.getPrimary().getVisualBounds().getWidth();
			private Image app, splash;
			
	@Override
	public void start(Stage StgLogin) {

//		StgLogin.setTitle("ATHENTIFICATION");
		StgLogin.setResizable(false);
		
		VericationConnexionBaseDeDonnees();

		try {
			// DEMARAGE AVEC UNE IMAGE---------------- COMPLEMENT VOIR LIGNE 19 ---------------------------------------------
			app = new Image(getClass().getResource("/images/ajouter.png").toExternalForm());
			splash = new Image(getClass().getResource("/images/logo demarrage.jpg").toExternalForm());
			SplashScreen.render(new double[] { LoginMain.height, LoginMain.width } , app, splash).showAndWait(); // APPELLE METHODE DANS LA CLASSE SplashScreen	
			
			try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root, 950, 500);
			scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			StgLogin.setScene(scene);
			StgLogin.show();
			
			// ADAPTER L'ECRAN SELON L'ORDINATEUR
			Rectangle2D rd = Screen.getPrimary().getVisualBounds();
			StgLogin.setX( (rd.getWidth() - StgLogin.getWidth()) /2 );
			StgLogin.setY( (rd.getHeight() - StgLogin.getHeight()) /2 );
			
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (NullPointerException ex) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
//==============================================================================================
	// CONNEXION DANS LA BASE DE DONNEES
	public void VericationConnexionBaseDeDonnees() {

		Connection connexion = ConnectionDB.maConnection();
		if (connexion == null) {
			System.out.println("Connexion de la base de données n'est pas réussie");
			System.exit(1);
		} else {
			System.out.println("Connexion de la base de données réussie");
		}
	}
//==============================================================================================
}