
package directeurGeneral;


import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javaBeansClass.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
	
	

	
	@FXML Label LabTof;
	@FXML ImageView imageSet;
	private FileChooser fileChooser ;
	private File file;
	private FileInputStream fis;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ActualiserDonneesFournisseurTableau();
		tableViewUtilisateur.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//---------------------------------------------------------------------------------
		try {
			genereRandom(); // METHODE RANDON GENERE ID IRTICLE S'IL N'EXISTE PAS DANS LA BASE DE DONNEES
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		//------------------------------------------------------------------
		ControlChiffPhone(); // CONTROLE DE SAISIT NUMERO TELEPHONE
		
		
		
		
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
	public void dateDuJourMethode() throws IOException {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
//		duJour.setText("Date : "+reportDate);
		
}
	//-----------------------------------------------------------------------------------

	// METHODE VALIDER NOM
	@SuppressWarnings("unused")
	private boolean validerNom() {

		Pattern p = Pattern.compile("[a-zA-Z]+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(refNom.getText());

		if (m.find() && m.group().equals(refNom.getText())) {
			return true;

		} else {
			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setTitle("Attention");

			alerte.setContentText("Entrer un Nom valide SVT!!");
			alerte.showAndWait();
		}
		return false;
	}
	//----------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------
		@SuppressWarnings("unused")
	private boolean validerPrenom() {
			
		Pattern p = Pattern.compile("[a-zA-Z]+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(refPrenom.getText());

		if (m.find() && m.group().equals(refPrenom.getText())) {
			return true;

		} else {
			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setTitle("Attention");
			alerte.setContentText("Entrer un Prénom valide SVT!!");
			alerte.showAndWait();
		}
		return false;
	}
	//----------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------
		@SuppressWarnings("unused")
	private boolean validerAdresse() {
			
		Pattern p = Pattern.compile("[a-zA-Z]+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(refPrenom.getText());

		if (m.find() && m.group().equals(refPrenom.getText())) {
			return true;

		} else {
			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setTitle("Attention");
			alerte.setContentText("Entrer un Prénom valide SVT!!");
			alerte.showAndWait();
		}
		return false;
	}
	@SuppressWarnings("unused")
	private boolean validerTelephone() {

		Pattern p = Pattern.compile("^?[0-9]{2}\\-?[0-9]{3}\\-?[0-9]{2}\\-?[0-9]{2}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(refTelephone.getText());

		if (m.find() && m.group().equals(refTelephone.getText())) {
			return true;

		} else {
			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setTitle("Attention");
			alerte.setContentText("Entrer un bon Numero Téléphone SVT!!");
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
		@SuppressWarnings("unused")  // CONTROLE DE SAISIE NUMERO DE TELEPHONE
		private boolean validerTelephoneMethode() {

			Pattern p = Pattern.compile("^?[0-9]{2}\\-?[0-9]{3}\\-?[0-9]{2}\\-?[0-9]{2}$", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(refTelephone.getText());

			if (m.find() && m.group().equals(refTelephone.getText())) {
				return true;

			} else {
				Alert alerte = new Alert(AlertType.WARNING);
				alerte.setTitle("Attention");
				alerte.setContentText("Entrer un bon Numero Téléphone SVT!!");
				alerte.showAndWait();
			}
			return false;
		}
		//----------------------------------------------------------------------------------
		public void ControlChiffPhone() {  // CE GENRE DE METHODE ON LES APPELLE DIRECTEMENT DANS LA METHODE QUI RECHARGE LES DONNEES AUTOMATIQUE
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
		@SuppressWarnings("unused")
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
		//----------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------	
/*		
			// APPEERSU D'UNE LIGNE SUR LE TABLEAU UNE FOIS CLIQUER
			public void AfficheTableViewChampsAjoutUtilsateur(ActionEvent event) {
				
				Connection connexion = ConnectionDB.maConnection();
				String requetteTAb = "SELECT nom, prenom, adresse, telephone, login, password, email, image, role from Utilisateur";
				
				try {
					PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteTAb);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						Utilisateur mat = tableViewUtilisateur.getSelectionModel().getSelectedItem();
						
						refNom.setText(rs.getString(1));		
						refPrenom.setText(rs.getString(2));									
						refAdress.setText(rs.getString(3));						
						refTelephone.setText(rs.getString(4));				
						refEmail.setText(rs.getString(5));				
						refeLoginUSer.setText(mat.getLogin());		
						
						refTelephone.setText(rs.getString(6));				
//						refEmail.setText(rs.getString(7));				
//						refeLoginUSer.setText(rs.getString(8));
//						refeLoginUSer.setText(rs.getString(9));
						
					}
				} catch (Exception e) {
					
				}
				
				
			}
			*/
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------
			
			@FXML private CheckBox CaissierChechbox,AdminChechbox,RespoStokChechbox  ;
			ObservableList<String> listUserChechbox = FXCollections.observableArrayList();
			
			String p ;
			// CONTROLE CHECKBOX CQISSIER
						@FXML 
						public void checkBoxCaissier() {
							if(CaissierChechbox.isSelected()) {
								AdminChechbox.setSelected(false);
								RespoStokChechbox.setSelected(false);
//								listUserChechbox.add(CaissierChechbox.getText());
								
								p = String.valueOf(CaissierChechbox.isSelected());
								System.out.println(p);
							}
						}
						
						// CONTROLE CHECKBOX CQISSIER
						@FXML 
						public void checkBoxAdmin() {
							if(AdminChechbox.isSelected()) {
								CaissierChechbox.setSelected(false);
								RespoStokChechbox.setSelected(false);
//								listUserChechbox.add(AdminChechbox.getText());
								
								p = String.valueOf(AdminChechbox.isSelected());
								System.out.println(p);
							}
						}
						
						// CONTROLE CHECKBOX CQISSIER
						@FXML 
						public void checkBoxResponsableStock() {
							if(RespoStokChechbox.isSelected()) {
								CaissierChechbox.setSelected(false);
								AdminChechbox.setSelected(false);
//								listUserChechbox.add(RespoStokChechbox.getText());
								
								p = String.valueOf(RespoStokChechbox.isSelected());
								
								System.out.println(p);
							}
						}		
//======================================================================
			
			public void checkBoxVerificationSiSectected() {
				if(! (CaissierChechbox.isSelected() | AdminChechbox.isSelected() | RespoStokChechbox.isSelected()) ) {
					Alert alert = new Alert(AlertType.WARNING);
		            alert.setHeaderText("SVP Selectionnez le droit d'utilisateur");
//		            alert.setContentText("Selectionnez un article dans la tablle SVP.");
		            
		            alert.showAndWait();
				}
			}
			
//=====================================================================
			
			public void resetTextFieldAndCombox() {
				
				refNom.setText("");
				refPrenom.setText("");
				refAdress.setText("");
				refTelephone.setText("");
				refEmail.setText("");
				refeLoginUSer.setText("");
				refpassword.setText("");
				
				CaissierChechbox.setSelected(false);
				RespoStokChechbox.setSelected(false);
				AdminChechbox.setSelected(false);
				listUserChechbox.clear();
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
				imageView.setFitWidth(140);
				imageView.setFitHeight(190);
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
 /*
	public void ajouterUtilisateur() throws FileNotFoundException {
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); // DATE AUTOMATIQUE - DATE, HEURE
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
	//-----------------------------------------------s
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(primaryStage);
		fis = new FileInputStream(file);
		
		checkBoxVerificationSiSectected(); // METHODE VERIFICATION SI UN DES CHECKBOX EST SELECTIONNE
	//-----------------------------------------------s
														// SA RESTE L'INSERTION D'IMAGE
		Connection connexion = ConnectionDB.maConnection();
		
		
		
		String requette = " INSERT INTO `Utilisateur`(`id`, `nom`, `prenom`, `adresse`, `telephone`, `login`, `password`, `email`, `image`, `date`, `role`) VALUES ('12', '"+ refNom.getText() +"', '" +refPrenom.getText() +"', "
				+ "'"+ refAdress.getText() +"', '"+ refTelephone.getText() +"', '"+ refeLoginUSer.getText() +"', '"+ refpassword.getText() +"', '"+ refEmail.getText() +"', '"+imageSet+"',"+ reportDate +", '"+ listUserChechbox.toString() +"'   ) " ;
		
		System.out.println(requette);
		
		int status;
		try {
			status = connexion.createStatement().executeUpdate(requette);
			
			if (status != 0) {
				System.out.println("Reussi");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println("Pas Reussi");
		}	

		// IMAGE USING
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(primaryStage);
		fis = new FileInputStream(file);
		pst.setBinaryStream(8, (InputStream)fis, (int)file.length());  // PONITER SUR LA CELLULE IMAGE

		

		
	}*/
		
// ---------------------------------------------------------------------------------------------------------------------------------------	
//========================================================================================================================================
//----------------------------------------------------------------------------------------------------------------------------------------
		
	public void addUser() throws SQLException, FileNotFoundException {
	/*	
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); // DATE AUTOMATIQUE - DATE, HEURE
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		
		
		*/
		
	//// DATE DU JOUR
		java.util.Date today = new java.util.Date();
		java.sql.Date dateAutomatique = new java.sql.Date(today.getTime());
		
	      Connection con = ConnectionDB.maConnection();

	       PreparedStatement pre = (PreparedStatement) con.prepareStatement("insert into Utilisateur values(?,?,?,?,?,?,?,?,?,?)");

	       pre.setInt(1,32);
	       pre.setString(2,refNom.getText());
	       pre.setString(3,refPrenom.getText());
	       pre.setString(4,refAdress.getText());
	       pre.setString(5, refTelephone.getText());
	       pre.setString(6,refeLoginUSer.getText());
	       pre.setString(7,refpassword.getText());
	       pre.setString(8,refEmail.getText());
	       //--------------------------------------------
	       fis = new FileInputStream(file);
	       pre.setBinaryStream(9, (InputStream) fis,(int) file.length());
	       //--------------------------------------------
//	       pre.setDate(10,dateAutomatique);
	       pre.setString(10,listUserChechbox.toString());
	       
	       
	       
	       pre.executeUpdate();
	       System.out.println("Successfully inserted the file into the database!");

	       pre.close();
	       con.close(); 

	    //==============================================================================================  
	       checkBoxVerificationSiSectected(); // METHODE VERIFICATION SI UN DES CHECKBOX EST SELECTIONNE
		//-----------------------------------------------s
															// SA RESTE L'INSERTION D'IMAGE
			
			
		
		
	}
		
		
		
		
		
		
		
		
		
		
		
}
