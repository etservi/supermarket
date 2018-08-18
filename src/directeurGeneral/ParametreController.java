package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import baseDeDonnées.ConnectionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;


public class ParametreController implements Initializable{
	
	@FXML private AnchorPane rootPane;
	
	@FXML private TextField refUtilisateur;
	@FXML private PasswordField refOldPssd,refNewPssd, refConfPssd;

	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
//		PasswordField textFd = TextFields.createClearablePasswordField();
//		rootPane.getChildren().add(textFd);
//		AnchorPane.setBottomAnchor(textFd, 20d);
//		AnchorPane.setLeftAnchor(textFd, 500d);	
		
	}
	
// -------------------------------------------------------------------
// -------------------------------------------------------------------
	public void changerPassd() {
		Connection connexion = ConnectionDB.maConnection();
	
			try {
				String requetteChange = "SELECT * FROM Utilisateur WHERE id= '" + refUtilisateur.getText() + "' AND password= '" + refOldPssd.getText() + "' ";
				PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteChange);
				ResultSet rs = pst.executeQuery();

				String logDataBase = null;
				String motDePass = null;

				if (rs.next()) {
					logDataBase = rs.getString("id");
					motDePass = rs.getString("password");
				}

				String logTextField = refUtilisateur.getText();
				String ancienMotDePasseTextFld = refOldPssd.getText();

				if (logTextField.equals(logDataBase) && ancienMotDePasseTextFld.equals(motDePass)) {
					if (refNewPssd.getText().equals(refConfPssd.getText())) {

						String sqlChange = "UPDATE Utilisateur SET password = '" + refNewPssd.getText() + "' WHERE id ='" + refUtilisateur.getText() + "' ";
						PreparedStatement psttt = (PreparedStatement) connexion.prepareStatement(sqlChange);
						psttt.execute();
						//-------------------------
						Alert alerte = new Alert(AlertType.INFORMATION);
						alerte.setTitle("Confirmation");
						alerte.setContentText("Votre mot de passe a bien été changé, \n merci de vous reconnecter !");
						alerte.showAndWait();
					} else if (refNewPssd.getText() != refConfPssd.getText()){
						Alert alerte = new Alert(AlertType.WARNING);
						alerte.setTitle("Evertissement");
						alerte.setContentText("Vos mot de passe sont different, \n merci de réessayer !");
						alerte.showAndWait();
					}
				} else if (logTextField != logDataBase && ancienMotDePasseTextFld != motDePass){
					Alert alerte = new Alert(AlertType.WARNING);
					alerte.setTitle("Evertissement");
					alerte.setContentText("Vos informations n'existent pas dans la base !");
					alerte.showAndWait();
				}
			} catch (Exception e) {
				e.printStackTrace();
				
			}
} 

	
// ----------------------------------------------------------------
// ----------------------------------------------------------------
	public void effacerChamps() {
		refOldPssd.clear();
		refNewPssd.clear();
		refConfPssd.clear();
	}
	
// ----------------------------------------------------------------
// ----------------------------------------------------------------
	// REDIRECTION AJOUTER RAYON
	@FXML
		private void ouvrirRayon(ActionEvent event) throws IOException {
			AnchorPane root = FXMLLoader.load(getClass().getResource("Rayon.fxml"));
			 rootPane.getChildren().setAll(root);
		}
// ----------------------------------------------------------------
// ----------------------------------------------------------------
	// REDIRECTION AJOUTER CATEGORIE
	@FXML
		private void ouvrirCategorie(ActionEvent event) throws IOException {
			AnchorPane paneCategorie = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
			 rootPane.getChildren().setAll(paneCategorie);
		}
// ----------------------------------------------------------------
// ----------------------------------------------------------------
	// REDIRECTION AJOUTER CATEGORIE
	@FXML
		private void retourAuMenu(ActionEvent event) throws IOException {
			AnchorPane paneExit = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
			 rootPane.getChildren().setAll(paneExit);
		}	
// ----------------------------------------------------------------
// ----------------------------------------------------------------
}