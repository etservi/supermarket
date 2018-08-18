package caissier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AccueilController implements Initializable{
	
	@FXML private AnchorPane rootAccueil;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
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
		Scene scene = new Scene(root,370,500);
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
		rootAccueil.getChildren().setAll(pane);

	}
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
	// REDIRECTION SUR ACCUEIL - IMAGE
	@FXML
	private void statistique() throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
		rootAccueil.getChildren().setAll(pane);

	}
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
	
}
