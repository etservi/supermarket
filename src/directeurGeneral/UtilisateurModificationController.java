package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class UtilisateurModificationController implements Initializable {
	
	@FXML private AnchorPane rootPane; // REDIRECTION DE PAGE ON A BESOIN DE SE POINTER DANS LE FICHIER CONTROLLEUR

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

	//------------------------------------------------------------------------------
		// REDIRECTION SUR ACCUEIL
		@FXML
		private void validerModification() throws IOException {
			Parent pane = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
			rootPane.getChildren().setAll(pane);	

		}
	// --------------------------------------------------------------------------

}
