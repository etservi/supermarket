package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javaBeansClass.Fournisseur;
import javaBeansClass.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UtilisateurModificationController implements Initializable {
	
	private Stage dialogStage;
	private boolean okClicked = false;
	private Utilisateur utilisateursss;
	
	
	@FXML private AnchorPane rootPane; // REDIRECTION DE PAGE ON A BESOIN DE SE POINTER DANS LE FICHIER CONTROLLEUR

	@FXML private TextField tfName, tfPrenom, tfAdress, tfTelephone, tdMotDePass, tfEmail ;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {


	}

	//------------------------------------------------------------------------------
		// REDIRECTION SUR ACCUEIL
		@FXML
		private void validerModification() throws IOException {
			Parent pane = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
			rootPane.getChildren().setAll(pane);	

		}
	// --------------------------------------------------------------------------
		
		//===============================================================================
		//===============================================================================
		
		public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }	
		public boolean isOkClicked() {
	        return okClicked;
	    }
		
		public void setUtilisateur(Utilisateur utilisateursss) {
	        this.utilisateursss = utilisateursss;
//	        tfName, tfPrenom, tfAdress, tfTelephone, tdMotDePass, tfEmail
	        tfName.setText(utilisateursss.getNom());
	        tfPrenom.setText(utilisateursss.getPrenom());
	        tfAdress.setText(utilisateursss.getAdresse());
	        tfTelephone.setText(utilisateursss.getTelephone());
	        tdMotDePass.setText(utilisateursss.getPassword());
	        tfEmail.setText(utilisateursss.getEmail());
	    }
		
		
		
		
}
