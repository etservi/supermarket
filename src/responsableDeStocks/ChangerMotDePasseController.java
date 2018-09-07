package responsableDeStocks;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import baseDeDonnées.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class ChangerMotDePasseController implements Initializable {
	
	@FXML private AnchorPane root;
	
	@FXML private TextField refUtilisateur;
	@FXML private PasswordField refOldPssd,refNewPssd, refConfPssd;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}
	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
		public void changerPassd() {
			Connection connexion = ConnectionDB.maConnection();
		
				try {
					String requetteChange = "SELECT * FROM Utilisateur WHERE (login = '" + refUtilisateur.getText( ) + "' || telephone  = '" + refUtilisateur.getText() + "') AND password= '" + refOldPssd.getText() + "' ";
					
					PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteChange);
					ResultSet rs = pst.executeQuery();

					String logDataBase = null;
					String numPhone = null;
					String motDePass = null;

					if (rs.next()) {
						logDataBase = rs.getString("login");	
						numPhone = rs.getString("telephone");	
						motDePass = rs.getString("password");	
					}

					if ( (refUtilisateur.getText().equals(numPhone) && refOldPssd.getText().equals(motDePass) ) && refNewPssd.getText().equals(refConfPssd.getText())) {
						try {
							String sqlChange = "UPDATE Utilisateur SET password = '" + refNewPssd.getText() + "' WHERE telephone  ='" + refUtilisateur.getText() + "'  ";
							System.err.println(sqlChange);
							
							PreparedStatement pst2 = (PreparedStatement) connexion.prepareStatement(sqlChange);
							int rs2 = pst2.executeUpdate();
							
							if (rs2 != 0) {
								Alert alerte = new Alert(AlertType.INFORMATION);
								alerte.setContentText("Votre mot de passe a bien été changé, \n merci de vous reconnecter !");
								alerte.showAndWait();	
							}
							} catch(Exception e) {
								e.printStackTrace();
							
							}
							//-------------------------
					} else if (refUtilisateur.getText() != logDataBase && refOldPssd.getText() != motDePass){
						Alert alerte = new Alert(AlertType.WARNING);
						alerte.setTitle("Evertissement");
						alerte.setContentText("Veuillez utiliser votre numéro de téléphone comme login.");
						alerte.showAndWait();
					}
				} catch (Exception e) {
					Alert alerte = new Alert(AlertType.WARNING);
					alerte.setTitle("Evertissement");
					alerte.setContentText("Veuillez Entrer vos informations.");
					alerte.showAndWait();
					
				}
	} 

		
	// ----------------------------------------------------------------
	// ----------------------------------------------------------------	

}
