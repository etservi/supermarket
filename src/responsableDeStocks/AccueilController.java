package responsableDeStocks;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AccueilController implements Initializable{

	@FXML private AnchorPane rootPane;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	// REDIRECTION LOGIN
		@FXML
		private void deconnexion(ActionEvent event) throws IOException {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));
			rootPane.getChildren().setAll(pane);
		}
		

	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
		
		// REDIRECTION SUR ACCUEIL - IMAGE
		@FXML
		private void ajouterCategorie() throws IOException {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsableDeStocks/Categorie.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,370,500);
			scene.getStylesheets().add(getClass().getResource("Categorie.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();

		}
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
		// REDIRECTION SUR ACCUEIL - IMAGE
		@FXML
		private void ajouterRayon() throws IOException {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsableDeStocks/Rayon.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,370,500);
			scene.getStylesheets().add(getClass().getResource("Rayon.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();

		}
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
		// REDIRECTION SUR ACCUEIL - IMAGE
		@FXML
		private void listeRayon() throws IOException {
			Parent pane = FXMLLoader.load(getClass().getResource("/responsableDeStocks/Accueil.fxml"));
			rootPane.getChildren().setAll(pane);

		}
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
		// REDIRECTION SUR ACCUEIL - IMAGE
		@FXML
		private void listeCategorie() throws IOException {
			Parent pane = FXMLLoader.load(getClass().getResource("/responsableDeStocks/Accueil.fxml"));
			rootPane.getChildren().setAll(pane);

		}
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
}
