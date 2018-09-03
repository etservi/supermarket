package caissier;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.StaticInfo;

public class AccueilController implements Initializable{
	
	@FXML private AnchorPane rootAccueil;
	@FXML private Label affichLogin;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		affichLogin(); // RECUPERATION UTILISATEUR CONNECTER
		
	}
	//====================================================
	public void affichLogin() {
			
			Connection connexion = ConnectionDB.maConnection();
			String sqll = "SELECT prenom, nom FROM Utilisateur WHERE telephone =" +StaticInfo.USERNAME +" OR login = "+ StaticInfo.USERNAME + " ";
			
				PreparedStatement pst;
				try {
					pst = (PreparedStatement) connexion.prepareStatement(sqll);
					ResultSet rs = pst.executeQuery();
					if(rs.next()) {
						affichLogin.setText( "Bienvenue " + rs.getString(1) +" "+ rs.getString(2));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
					
		}
	//====================================================
	
//------------------------------------------------------------------------------
	// REDIRECTION SUR ACCUEIL - IMAGE
	@FXML
	private void deconnexion() throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));
		rootAccueil.getChildren().setAll(pane);
	}
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
	
	// REDIRECTION SUR ACCUEIL - IMAGE
	@FXML
	private void nouveauClient() throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("/caissier/Facture.fxml"));
		rootAccueil.getChildren().removeAll();
		rootAccueil.getChildren().setAll(pane);

	}
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
	// REDIRECTION SUR ACCUEIL - IMAGE
	@FXML
	private void gestionArticleClik() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/caissier/SwGestionArticle.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,370,200);
		scene.getStylesheets().add(getClass().getResource("SwGestionArticle.css").toExternalForm());
		stage.setScene(scene);
		stage.showAndWait();

	}
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
	// REDIRECTION SUR ACCUEIL - IMAGE
	@FXML
	private void gestionArticle() throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
		rootAccueil.getChildren().removeAll();
		rootAccueil.getChildren().setAll(pane);

	}
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
	// REDIRECTION SUR ACCUEIL - IMAGE
	@FXML
	private void statistique() throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
		rootAccueil.getChildren().removeAll();
		rootAccueil.getChildren().setAll(pane);

	}
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
		// REDIRECTION SUR ACCUEIL - IMAGE
		@FXML
		private void voireStatistikVenteCaissier() throws IOException {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/caissier/Statistique.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,950, 500);
			scene.getStylesheets().add(getClass().getResource("Statistique.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();

		}
		//-------------------------------------------------------------------------
		//-------------------------------------------------------------------------
		// REDIRECTION SUR ACCUEIL - IMAGE
				@FXML
				private void voireListeCategorieRayonCaissier() throws IOException {
					Stage stage = new Stage();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/caissier/ListeCategorieRayon.fxml"));
					Parent root = loader.load();
					Scene scene = new Scene(root,950, 500);
					scene.getStylesheets().add(getClass().getResource("ListeCategorieRayon.css").toExternalForm());
					stage.setScene(scene);
					stage.showAndWait();

				}
//==================================================
}
