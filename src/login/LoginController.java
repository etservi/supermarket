package login;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Timer;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LoginController implements Initializable{
	
	@FXML private AnchorPane paneLogin;
	@FXML private Label signError;
	@FXML public TextField loginnfild;
	@FXML private PasswordField psswFild;
	
	@FXML private Button btValidCon,effacerChp;
	
    @FXML private Label dateduJour;
    
    @FXML private Circle cercle1, cerlce2;
	
    int compteurAdminStock = 0;
    int compteurCaissier = 0;
    int compteurAdminGeneral = 0;
    int compteurEchec = 0;
    
   Stage StgLogin;
    
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		valideLogin() ; // LONGUEUR QUE PRENDRE LE LOGIN
//====================================================================================== TRANSITION ==
		RotateTransition btnRotate = new RotateTransition(Duration.seconds(2), btValidCon);
		btnRotate.setByAngle(360);
		btnRotate.setDelay(Duration.seconds(1));
		btnRotate.setRate(5);
		btnRotate.setCycleCount(5);
		btnRotate.play();
		// COMMENCER A PARTIR DE -70
		btValidCon.setLayoutX(-70); 
		btValidCon.setLayoutY(275);
		// QUITTER DE GAUCHE A DROITE - JUSKA SUR L'ABCISSE 440
		TranslateTransition btnTranslate = new TranslateTransition(Duration.seconds(2), btValidCon);
		btnTranslate.setDelay(Duration.seconds(1));
		btnTranslate.setToX(440);
		btnTranslate.setCycleCount(1);
		btnTranslate.play();
//====================================================================================================
		RotateTransition btnAnnulerRotate = new RotateTransition(Duration.seconds(2), effacerChp);
		btnAnnulerRotate.setByAngle(360);
		btnAnnulerRotate.setDelay(Duration.seconds(1));
		btnAnnulerRotate.setRate(5);
		btnAnnulerRotate.setCycleCount(5);
		btnAnnulerRotate.play();
//====================================================================================================		
		setRotae(cercle1, true, 360, 10);
		setRotae(cerlce2, true, 180, 18);
}
//====================================================================================================
	
	int rotate = 0;
	 // TRANSITION ------------------------------------------ TRANSITION //
	public void setRotae(Circle c, boolean reverse, int angle, int duration) {
		RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration), c);
		rotateTransition.setAutoReverse(reverse);
		rotateTransition.setByAngle(angle);
		rotateTransition.setDelay(Duration.seconds(0));
		rotateTransition.setRate(3);
		rotateTransition.setCycleCount(18);
		rotateTransition.play();
		
	}
	
//====================================================================================================
	
		// REDIRECTION SUR ACCUEIL ! CAISSIER / DIRECTEUR / RESPONSABLE DE STOCKS
		@FXML
		private void validerConnexion(ActionEvent event) throws IOException {
			
			// INTERVALLE HEURE DE CONNEXION ---------------------------------
			var currentTime = LocalTime.now();
			LocalTime before = LocalTime.parse("00:00");
	        LocalTime after = LocalTime.parse("04:00");
	        
//	        java.time.ZonedDateTime before = java.time.ZonedDateTime.parse("07:00");
//	        java.time.ZonedDateTime after = java.time.ZonedDateTime.parse("07:00");
	        
	        if (currentTime.isBefore(after) && currentTime.isAfter(before) ) {
			//----------------------------------------------------------------
			
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
					
					if ( logRole.equalsIgnoreCase("Admin Général") ) {
						
						Parent fxml = FXMLLoader.load(getClass().getResource("/directeurGeneral/Accueil.fxml"));
						paneLogin.getChildren().removeAll();
						paneLogin.getChildren().setAll(fxml);
						
					} else if (logRole.equalsIgnoreCase("Caissier")) {
						
						Parent pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
						paneLogin.getChildren().removeAll();
						paneLogin.getChildren().setAll(pane);
					
					} else if (logRole.equalsIgnoreCase("Admin Stock")) {
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
						signError.setText("Veuillez vérifier vos identifiants.");
						
					} 
				} else {
					compteurEchec++;
				    System.out.println("exit "+compteurEchec);
				    
				    if(compteurEchec==3) {
//				    	System.exit(0);
				    	signError.setText("Impossible de se connecter");
				    	loginnfild.setEditable(false);
				    	psswFild.setEditable(false);
				    	
				    	btValidCon.disabledProperty();
//				    	effacerChp;
				    	
				    	Timer timer = new Timer();
				        timer.schedule(new TimerTest.RepeatTask(timer, 10), 0, 2500);
				    	System.out.println(TimerTest.val);
				    		
				    } 
		
				    
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

