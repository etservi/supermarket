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

public class SwGestionArticleController implements Initializable{
	
	@FXML private AnchorPane rootP;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	//--------------------------------------------------------------------------


//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
	// REDIRECTION SUR ACCUEIL - IMAGE
	@FXML
	private void historikVente() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/caissier/DetailsVente.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,950,500);
		scene.getStylesheets().add(getClass().getResource("DetailsVente.css").toExternalForm());
		stage.setScene(scene);
		stage.showAndWait();

	}
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
		// REDIRECTION SUR ACCUEIL - IMAGE
		@FXML
		private void prixArticle() throws IOException {
			
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/caissier/prixArticle.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,950,500);
			scene.getStylesheets().add(getClass().getResource("prixArticle.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();

		}
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
}