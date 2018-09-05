
package directeurGeneral;


import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXComboBox;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Qr_Code.LireCodeBArre;
import baseDeDonnées.ConnectionDB;
import codeBarre.CodeBarreImage;
import directeurGeneral.AjoutFournisseurController.codeBarreStart;
import javaBeansClass.Utilisateur;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javafx.stage.Stage;

public class AjoutUtilisateurController implements Initializable{
	
	AjoutUtilisateurMain userUpdate;
	
	@FXML private AnchorPane utilisateurPane;
	@FXML private TableView<Utilisateur> tableViewUtilisateur;
	
	@FXML private TextField refNom;
	@FXML private TextField refPrenom;
	@FXML private TextField refAdress;
	@FXML private TextField refTelephone;
	@FXML private TextField refEmail;
	@FXML private TextField refeLoginUSer;
	@FXML private PasswordField refpassword;
	@FXML private Label duJour;
	
	Stage primaryStage;
	@FXML BorderPane borderPanee;
	
	@FXML private TableColumn<Utilisateur, String> colonnePrenom;
	@FXML private TableColumn<Utilisateur, String> colonneId;
	ObservableList<Utilisateur> UtilisateurList = FXCollections.observableArrayList();
	
	@FXML private JFXComboBox<String> comBoBoxSelectDroit;
	
	@FXML Label LabTof;
	@FXML ImageView imageSet;
	private FileChooser fileChooser ;
	private File file;
	private FileInputStream fis;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
//		ControlChiffPhone(); // CONTROLE DE SAISIT NUMERO TELEPHONE
		ActualiserDonneesFournisseurTableau();
		tableViewUtilisateur.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//---------------------------------------------------------------------------------
		comBoBoxSelectDroit.getItems().addAll("Admin Général", "Admin Stock", "Caissier");  // LISTE DROIT UTILISATEUR
		//------------------------------------------------------------------
		try {  
			genereRandom(); // METHODE RANDON GENERE ID IRTICLE S'IL N'EXISTE PAS DANS LA BASE DE DONNEES
			codeBarreStart.main(null); // APPELLE METHODE - CREER CODE BARRE ========== VOIR LIGNE 465
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		//------------------------------------------------------------------
	
		
		
	}
	
	//////////////////////////////////////////////////
	
	
	public String genereRandom() throws SQLException {	
		
		Connection con = ConnectionDB.maConnection();
		
		String sqlCheck = "SELECT login from Utilisateur";
		PreparedStatement pst = (PreparedStatement) con.prepareStatement(sqlCheck);
		
		String log = null;
		
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			log = rs.getString("login");
		}
		//---------------------------
		
		final Random RANDOM = new SecureRandom();
		
//		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
		String letters = "0123456789";
		String pw = "";
		
	      for (int i = 0; i < 6; i++)
	      {
	    	  int index = (int)(RANDOM.nextDouble()*letters.length());
	          pw += letters.substring(index, index+1);     
	      }
	      //...........................
	      if(log != pw) {
	    	  refeLoginUSer.setText(""+ pw);  
	      } else {
	    	  refeLoginUSer.setText("");
	      }
		return pw;		
}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------

	// METHODE VALIDER NOM
	private boolean validerNom() {

		Pattern p = Pattern.compile("[a-zA-Z ]+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(refNom.getText());

		if (m.find() && m.group().equals(refNom.getText())) {
			return true;

		} else {
			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setHeaderText("Entrer un Nom valide !");
			alerte.showAndWait();
		}
		return false;
	}
	//----------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------

	private boolean validerPrenom() {
			
		Pattern p = Pattern.compile("[a-zA-Z ]+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(refPrenom.getText());

		if (m.find() && m.group().equals(refPrenom.getText())) {
			return true;

		} else {
			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setHeaderText("Entrer un Prénom valide !");
			alerte.showAndWait();
		}
		return false;
	}	
	//---------------------------------------------------------------------------------- 
	// ---------------------------------------------------------------------------------

	private boolean validerAdresse() {
			
		Pattern p = Pattern.compile("[a-zA-Z ]+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(refAdress.getText());

		if (m.find() && m.group().equals(refAdress.getText())) {
			return true;

		} else {
			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setHeaderText("Entrer une Adresse valide !");
			alerte.showAndWait();
		}
		return false;
	}
	
	private boolean validerTelephone() {

		Pattern p = Pattern.compile("^?[0-9]{2}\\-?[0-9]{3}\\-?[0-9]{2}\\-?[0-9]{2}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(refTelephone.getText());

		if (m.find() && m.group().equals(refTelephone.getText())) {
			return true;

		} else {
			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setHeaderText("Entrer un bon Numero Téléphone SVT!!");
			alerte.showAndWait();
		}
		return false;
	}
	//---------------------------------------------------------------------------------- 
	// ---------------------------------------------------------------------------------
		// ACTUALISER LES DONNEES SUR TABLEAU
		public void ActualiserDonneesFournisseurTableau() {
			UtilisateurList.clear(); // EFFACE REPERTION DONNEES TABLEAU
			Connection connexion = ConnectionDB.maConnection();
			
			String requetteIni = "SELECT prenom, nom FROM Utilisateur"; 
			
			try {
				PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteIni);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					UtilisateurList.addAll(new Utilisateur(rs.getString(1), rs.getString(2) ));
				}
				tableViewUtilisateur.setItems(UtilisateurList);
				colonneId.setCellValueFactory(new PropertyValueFactory<>("prenom"));
				colonnePrenom.setCellValueFactory(new PropertyValueFactory<>("nom"));
				
				
			} catch (Exception exActualiserDonneesFournisseurTableau) {
				Logger.getLogger(AjoutFournisseurController.class.getName()).log(Level.SEVERE, null, exActualiserDonneesFournisseurTableau);
			}
		}
		
		// CHOISIR UNE PHOTO
		@FXML
			private void prendrePhoto(ActionEvent event) throws IOException {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("FileChooser Example");
			
			File homeDir = new File(System.getProperty("user.home"));
			fileChooser.setInitialDirectory(homeDir);
			
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
			
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
			try {
			//--- Open the file with associated application
			Desktop.getDesktop().open(selectedFile);
			}
			catch (Exception e) {
			System.err.println("ERROR: Unable to open the file");
			}
			}
		}
		//----------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------
		public void ControlChiffPhone(KeyEvent er) {  // CE GENRE DE METHODE ON LES APPELLE DIRECTEMENT DANS LA METHODE QUI RECHARGE LES DONNEES AUTOMATIQUE
			refTelephone.setOnKeyTyped(e -> {
				String ch = e.getCharacter();
				if (!(ch.equals("0") || ch.equals("1") || ch.equals("2") | ch.equals("3") || ch.equals("4")
						|| ch.equals("5") || ch.equals("6") || ch.equals("7") || ch.equals("8") || ch.equals("9")
						|| ch.equals("BACK_SPACE")) || (!(refTelephone.getText().length() < 9))) {
					e.consume();
					java.awt.Toolkit.getDefaultToolkit().beep();
				}
			});
		}
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------

		private boolean validerEmail() { // CONTROLE DE SAISIE ADRESSE EMAIL

			Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(refEmail.getText());

			if (m.find() && m.group().equals(refEmail.getText())) {
				return true;

			} else {
				Alert alerte = new Alert(AlertType.WARNING);
				alerte.setTitle("Attention");

				alerte.setContentText("Entrer un E-mail valide SVT!!");
				alerte.showAndWait();
			}
			return false;
		}
//--------------------------------------------------------------------- 
//---------------------------------------------------------------------
//=====================================================================
//=====================================================================
			
			public void resetTextFieldAndCombox() {
				
				refNom.setText("");
				refPrenom.setText("");
				refAdress.setText("");
				refTelephone.setText("");
				refEmail.setText("");
				refeLoginUSer.setText("");
				refpassword.setText("");
			}
			
//-----------------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------
		public void retourMnu() throws IOException {  // UTILISATEUR RETOUR MENU - CLIIQUE SUR LA PHOTO
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/directeurGeneral/Accueil.fxml"));
		utilisateurPane.getChildren().setAll(pane);
		}		
		
		
		// ----------------------------------------------------------------------------------	
		//-----------------------------------------------------------------------------------
		@FXML
		public void UploadImage() throws IOException {  
			
			
			FileChooser fileChooser = new FileChooser();
			file = fileChooser.showOpenDialog(primaryStage);
			if(file != null) {
				LabTof.setText(file.getAbsolutePath());
				Image imgae = new Image(file.toURI().toString(), 100, 150, true, true);
				
			    ImageView imageView = new ImageView(imgae);
				imageView.setFitWidth(170);
				imageView.setFitHeight(220);
				imageView.setPreserveRatio(true);
				borderPanee.setCenter(imageView);
		
			}	
		}			
// ==================================================================================================================================
// ==================================================================================================================================

// ==================================================================================================================================
// ==================================================================================================================================
			
			// REDIRECTION SUR ACCUEIL - IMAGE
			@FXML
			private void modiferUytilisateur() throws IOException { //OUVERTURE DANS UNE AUTRE FENETRE
				
				int selectedIndex = tableViewUtilisateur.getSelectionModel().getSelectedIndex();
		        if (selectedIndex >= 0) {
			
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/directeurGeneral/UtilisateurModification.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root,800,500);
				scene.getStylesheets().add(getClass().getResource("/directeurGeneral/UtilisateurModification.css").toExternalForm());
				stage.setScene(scene);
				stage.showAndWait();
		        } else {
		        	Alert alert = new Alert(AlertType.WARNING);
		            alert.setHeaderText("Aucune selection d'Article");
		            alert.setContentText("Selectionnez un article dans la tablle SVP.");
		            
		            alert.showAndWait();
		        }

			}
// ---------------------------------------------------------------------------------------------------------------------------------------	
//========================================================================================================================================
//----------------------------------------------------------------------------------------------------------------------------------------
 
// ---------------------------------------------------------------------------------------------------------------------------------------	
//========================================================================================================================================
//----------------------------------------------------------------------------------------------------------------------------------------
		
	public void addUser() {
		
	//// DATE DU JOUR
		java.util.Date today = new java.util.Date();
		java.sql.Date dateAutomatique = new java.sql.Date(today.getTime());
		
		
	       String a = refNom.getText();
	       String b = refPrenom.getText();
	       String c = refAdress.getText();
	       String d = refTelephone.getText();
	       String e = refeLoginUSer.getText();
	       String f =refpassword.getText();
	       String g =refEmail.getText();
	      
		
	      Connection con = ConnectionDB.maConnection();
	      
	      String sql = "INSERT into Utilisateur (`nom`, `prenom`, `adresse`, `telephone`, `login`, `password`, `email`, `date`, `role`) VALUES ('"+a+"', '"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"', '"+dateAutomatique+"', '"+comBoBoxSelectDroit.getValue()+"' )";

	      if( (validerNom() && validerPrenom() && validerAdresse() && validerTelephone() && validerEmail() ) && ( refeLoginUSer.getText()!= null && refpassword.getText() != null ) && comBoBoxSelectDroit.getValue() != null ) { 
	      
	      try {
			int pst = con.createStatement().executeUpdate(sql);
			
			if(pst != 0) {
				System.out.println("Ressi");
				Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setHeaderText( a + " " + b + " a été crée en tant que " + comBoBoxSelectDroit.getValue());
	            alert.showAndWait();
	            
//	            new notificationThred().start(); // NOTIFICATION AJOUT EFFECTUE
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
	      } else if (refeLoginUSer.getText().isEmpty()) {
	    	  Alert alert = new Alert(AlertType.WARNING);
	          alert.setHeaderText("Vérifiez votre login");
	          alert.showAndWait();
	         
	    	  
	      } else if (refpassword.getText().isEmpty()) {
	    	  Alert alert = new Alert(AlertType.WARNING);
	          alert.setHeaderText("Vérifiez votre mot de passe");
	          alert.showAndWait();
	         
	    	  
	      } else if (comBoBoxSelectDroit.getItems().isEmpty()) {
	    	  Alert alert = new Alert(AlertType.WARNING);
	          alert.setHeaderText("Vérifiez le type d'utilisateur");
	          alert.showAndWait();
	          
	      }
	      
	      
	      /*
		       fis = new FileInputStream(file);
		       pre.setBinaryStream(9, (InputStream) fis,(int) file.length());
	       */

	    //==============================================================================================  
			

		
		
	}
	
//=====================================================================================================
	
	//=====================================================================================================
	//---------------------------------------------- CODE BARRE - Qr_CODE
	//////////////////////////////////////////////////////////////////
	// DEMARRER CODE BARRE AUTOMATIQUE - J'AI CREE UNE METHODE MAIN //
	//////////////////////////////////////////////////////////////////
	public static class codeBarreStart{
		public static void main(String[] args) throws SQLException {

			Connection connexion = ConnectionDB.maConnection();
			String query = "SELECT telephone, email from Utilisateur";
			Statement stmt = null;
			stmt = (Statement) connexion.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				CodeBarreImage.createImage(rs.getString(1), rs.getString(2)); // CREE LE CODE IMAGE
				LireCodeBArre.generete_qr( rs.getString(1), rs.getString(2) ); // CREE LE Qr_Code
			}
		}
	}
//=======================================================================================================
	
//======================================================================================================
/*	
	public class notificationThred extends Thread {
		@Override
		public void run(){
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
				ex.printStackTrace();
			}
			
			Image img = new Image("/images/YES.png");
			Notifications notificationBuilder = Notifications.create()
					.title("Action Réussie")
					.text("Requette bien effectuée")
					.graphic(new ImageView(img))
					.hideAfter(Duration.seconds(5))
					.position(Pos.TOP_RIGHT)
					.onAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							System.out.println("Notification valide");							
						}
					});
//			notificationBuilder.darkStyle();
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					notificationBuilder.show();
				}			
		});
	  }
	}
		*/
		
		
		
		
		
		
		
		
}
