package login;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.TimerTest.RepeatTask;


public class LoginController implements Initializable{
	
	@FXML private AnchorPane paneLogin;
	@FXML private Label signError;
	@FXML public TextField loginnfild;
	@FXML private PasswordField psswFild;
	
	@FXML private Button btValidCon,effacerChp;
	
    @FXML private Label dateduJour;
	
	int count = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		valideLogin() ; // LONGUEUR QUE PRENDRE LE LOGIN	
	}
	
	//================================================================================
	
		// REDIRECTION SUR ACCUEIL ! CAISSIER / DIRECTEUR / RESPONSABLE DE STOCKS
		@FXML
		private void validerConnexion(ActionEvent event) throws IOException {
			
			// INTERVALLE HEURE DE CONNEXION ---------------------------------
			var currentTime = LocalTime.now();
			LocalTime before = LocalTime.parse("07:00");
	        LocalTime after = LocalTime.parse("18:00");

	        if (currentTime.isBefore(after) && currentTime.isAfter(before) ) {
			//----------------------------------------------
			
			if( !(loginnfild.getText().isEmpty() && psswFild.getText().isEmpty()) ){
					
			try {
				
				Connection connexion = ConnectionDB.maConnection();
				String sql = "SELECT * from Utilisateur WHERE (login =? || telephone =?) AND password =?";
				
				PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(sql);
				pst.setString(1, loginnfild.getText().trim());
				pst.setString(2, loginnfild.getText().trim());
				pst.setString(3, psswFild.getText());
				ResultSet rs = pst.executeQuery();
				//==========================================================================================================
				//-----------RECUPERATION LE NOM UTILISATEUR QUI EST CONNECTER
				//----------------------------------------------
				StaticInfo.USERNAME = loginnfild.getText();
				
				StaticInfo.PASSWORD = psswFild.getText(); // RECUPERER LE MOT DE PASSE POUR CONFIRMER ACCES AU PARAMETRE ...
				//----------------------------------------------
				//==========================================================================================================

				String logRole = null;
				
				if (rs.next()) {
					
//					// ------------------------------------
					logRole = rs.getString("role");
					//-------------------------------------

					if ( logRole.equalsIgnoreCase("Administrateur") ) {
						
						Parent fxml = FXMLLoader.load(getClass().getResource("/directeurGeneral/Accueil.fxml"));
						paneLogin.getChildren().removeAll();
						paneLogin.getChildren().setAll(fxml);
						
					} else if (logRole.equalsIgnoreCase("Responsable de stock")) {
						
						Parent pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
						paneLogin.getChildren().removeAll();
						paneLogin.getChildren().setAll(pane);
					
						
					} else if (logRole.equalsIgnoreCase("Responsable commercial")) {
//						
						Parent pane = FXMLLoader.load(getClass().getResource("/responsableDeStocks/Accueil.fxml"));
						paneLogin.getChildren().removeAll();
						paneLogin.getChildren().setAll(pane);

					} else {
						
						System.out.println("Pas confome");
						AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
						paneLogin.getChildren().removeAll();
						paneLogin.getChildren().setAll(pane);
						effacer();
						
					} 
				} else {
		
					signError.setText("Veuillez vérifier vos identifiants.");
					effacer();
					// Show the error message.
		            Alert alert = new Alert(AlertType.ERROR);
		            alert.setTitle("Utilisateur n'existe pas!!");
		            alert.setHeaderText("Utilisateur incorrect");
		            alert.showAndWait();
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("HEURE DE TRAVAIL");
            alert.setHeaderText(" 07H00 à 18H00 ! \n Merci de réssayer à partir de 07H");
            alert.showAndWait();
		}
}		
//////////////////////////////////////////
		//////////////////////////

public void effacer() {
	loginnfild.clear();
	psswFild.clear();
	
}		

	//-----------------------------------------------------------------------
	//-----------------------------------------------------------------------
	///////// RECUPERER LA MARQUE DE L'ORDINATEUR
		public static String marqueDeLordinateur() {
			String markDeLOrdinateur = null;
			try {
				final InetAddress addr = InetAddress.getLocalHost();
				markDeLOrdinateur = new String(addr.getHostName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return markDeLOrdinateur;
		}
	//-----------------------------------------------------------------------
	//----------------------------------------------------------------------

//--------------------------------------------------------------------
		  @FXML  // PERMET DE FERMET LE PROGRAM
		    private void closeProgram(ActionEvent event) throws IOException {
		        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        app_stage.close();
		    }
			//-----------------------------------------------------------------------------------
			//-----------------------------------------------------------------------------------	
				public void valideLogin() {  // CE GENRE DE METHODE ON LES APPELLE DIRECTEMENT DANS LA METHODE QUI RECHARGE LES DONNEES AUTOMATIQUE
					loginnfild.setOnKeyTyped(e -> {
						String ch = e.getCharacter();
						if (( ch.equals("BACK_SPACE")) || (!(loginnfild.getText().length() < 9))) {
							e.consume();
							java.awt.Toolkit.getDefaultToolkit().beep();
						}
					});
				}
				

				
				
				
	
}

