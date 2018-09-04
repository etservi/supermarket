package directeurGeneral;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import Qr_Code.LireCodeBArre;
import baseDeDonnées.ConnectionDB;
import boiteAlert.Confirmation;
import codeBarre.CodeBarreImage;
import javaBeansClass.Fournisseur;
import javafx.application.Platform;
import javafx.beans.binding.BooleanExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AjoutFournisseurController implements Initializable{
	
	UtilisateurModificationMain userUpdate;
	
	@FXML private AnchorPane paneFournisseur;
	
	@FXML private TextField textFieldRaisonSociale;
	@FXML private TextField TextFieldSigle;
	@FXML private TextField TextFieldTelephone;
	@FXML private TextField TextFieldAdresse;
	@FXML private TextField TextFieldCourriel;
	@FXML private Label duJour;
	 
	@FXML private TextField recherch;
	Fournisseur fourni;
	
	@FXML private TableView<Fournisseur> tableViewFournisseur;
	
	@FXML private TableColumn<Fournisseur, String> colonneRaisonSociale;
	@FXML private TableColumn<Fournisseur, String> colonneSigle;
	@FXML private TableColumn<Fournisseur, String> colonneTelephone;
	@FXML private TableColumn<Fournisseur, String> colonneAdesse;
	@FXML private TableColumn<Fournisseur, String> colonneCourriel;
	
//	String alertMesaz = "";
	
	private ObservableList<Fournisseur> fournisseurList = FXCollections.observableArrayList();
	
	protected ImageView bottom_bar_dt;
	
	@FXML Button btValidModif, imprim, supprimer, modifier;
	


	
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------	
//---------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
	// CHARGEMENT AUTOMATIQUE
	@Override  
	public void initialize(URL location, ResourceBundle resources) {

		ActualiserDonneesFournisseurTableau();	
		//----------------GENERER LES MOTS AUTOMATIQUE
		dateDuJourMethode();   // GERE LA DATE ET HEURE AUTOMATIQUE
		genereRandom();		   // GERE LES ID AUTOMATIK
		ControlChiffPhone();  //  GERE LE CONTROLE CHIFFRES NUM PHONE
//		rechercheFiltr();
		//--------------------------------------------------
		selctionAuto(); // SELECTION MULTIPLE
		//-------------------------------------------------------
		
		// AFFICHER LES INFO DANS LES TEXTFIELD UNE FOIS CLICK SUR UNE LIGNE DU TABAEAU
		afficheDetailsFouenisseurSiClikTableau(null);
		tableViewFournisseur.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> afficheDetailsFouenisseurSiClikTableau(newValue));
		//-----------------------------------------------------
		//-------------- AUTOCOMPLETE TEXTFIELD
		try {
			genererMot();
			codeBarreStart.main(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//========================
		
}
//---------------------------------------------------------------------------------	
//---------------------------------------------------------------------------------

	// INSERTION FOURNISSEUR ////////////////////////////////////////////////////// METHODE AJOUTER FOURNISSEUR
	public void ajouterFournsseur() {
		String RaisonSocial = textFieldRaisonSociale.getText().trim();
		String Sigl = TextFieldSigle.getText().trim();
		String Telephon = TextFieldTelephone.getText();
		String Adess = TextFieldAdresse.getText().trim();
		String Couriel = TextFieldCourriel.getText();	
		
		Connection connexion = ConnectionDB.maConnection();
		
		// VERIFICATION SI LES CHAMPS SONT BIEN REMPLIS
		if(RaisonSocial.isEmpty() && Sigl.isEmpty() && Telephon.isEmpty() && Adess.isEmpty() && Couriel.isEmpty() && Adess.isEmpty()){
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Veuillez remplir les champs SVP!!!");
			alert.showAndWait();
			
		} else if( validerTelephone() && validerEmail() && textFieldRaisonSociale.getText()!= null && TextFieldAdresse.getText() != null ) {

		try {
			String requetteInsertion = "INSERT INTO `Fournisseur`(`raisonSociale`, `sigle`, `telephone`, `adresse`, `email`) VALUES ('"+RaisonSocial+"','"+Sigl+"','"+Telephon+"','"+Adess+"','"+Couriel+ "')";
			
			int statut = connexion.createStatement().executeUpdate(requetteInsertion);
			if (statut != 0) {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Fournisseur ajouté");
				alert.setHeaderText("Fournisseur a été bien ajouté");
				alert.showAndWait();
				
				new notificationThred().start();
				ActualiserDonneesFournisseurTableau();	
				EffacerLesChamps();
				
				// ------------------------------------------------------
		
				
/////////////////////////////////PDF TEST ///////////// PDF TEST /////////
/////////////////////////////////PDF TEST ////////////// PDF TEST /////////
				String chemin="BonsEntrant/pdfTest.pdf";				
				Document document = new Document();
			        
				try {

		            PdfWriter.getInstance(document, new FileOutputStream(new File(chemin)));
		            document.open();

		            Paragraph p = new Paragraph();
		            p.add("GESTION COMMERCIALE SUPERMARCHÉ \n");
		            p.add("----------------- \n");
		            p.add("DAKAR - 772774465 / 773667724");
		            p.add("\n ******************************************************* \n");
		            
		           
		            bottom_bar_dt = new ImageView( new Image( new File("../resources/icons/bottom_bar_dt.png").toURI().toString(), true));
//		            p.add(bottom_bar_dt);
//		            -------------------------------
		            for (int i = 0; i < 5; i++) {
		            	p.add("\n");
					}
		            p.setAlignment(Element.ALIGN_CENTER);
		            document.add(p);
		            // ------------------------------------ENTETE TABLEAU
		            
		            //-----------------------------------------
		            PdfPTable table = new PdfPTable(5);
		            table.setWidthPercentage(100);
		         //   table.getDefaultCell().setBorder(Rectangle.NO_BORDER); // ELIMIME LE BORDER
		            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		            table.getDefaultCell().setFixedHeight(30);
		            
		            PdfPCell enteteTatb = new PdfPCell(new Paragraph("header with colspan 3"));
		            enteteTatb.setColspan(5);
		            
//		            enteteTatb.setBorderColor(new Color(0xC0, 0xC0, 0xC0, 0xC0));
		            enteteTatb.setHorizontalAlignment(Element.ALIGN_CENTER);
		            table.addCell(enteteTatb);
		            //--------------------------------------- 
		            
		            table.addCell(new Phrase(new Chunk("RAISON SOCIALE", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD))));
				    table.addCell(new Phrase(new Chunk("SIGLE", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD))));
				    table.addCell(new Phrase(new Chunk("ADRESSE", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD))));
				    table.addCell(new Phrase(new Chunk("TÉLEPHONE", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD))));
				    table.addCell(new Phrase(new Chunk("E-mail", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD))));
			
				    //	    
				    
		            table.addCell(new Phrase(new Chunk(RaisonSocial)));
				    table.addCell(new Phrase(new Chunk(Sigl)));
				    table.addCell(new Phrase(new Chunk(Telephon)));
				    table.addCell(new Phrase(new Chunk(Adess)));
				    table.addCell(new Phrase(new Chunk(Couriel)));
				    document.add(table);
		            
		            //-------------------------------------------------------
		            Paragraph paragraphList = new Paragraph("A to E:");
		            List list = new List(false, 10);
		            list.add("A");
		            list.add("B");
		            list.add("C");
		            list.add("D");
		            list.add("E");
		            paragraphList.add(list);
		            document.add(paragraphList);
		            
		            //--------------------------------------------------------
		            Paragraph espaceVide = new Paragraph(" \n");
		            for (int i = 0; i < 7; i++) {
		            	p.add(espaceVide);
					}
		           //------------------------------------------------------
		            String tabulation = null;
		            for (int i = 0; i < 20; i++) {
						 tabulation ="\t";
					}
		            Paragraph piedDePageGauche = new Paragraph("Directeur " + tabulation + " Client");
		            piedDePageGauche.setAlignment(Element.ALIGN_LEFT);
		            document.add(piedDePageGauche);
		            //-------------------------------------------------------
		            //-------------------------------------------------------
		            
		            Font f = new Font();
		            f.setStyle(Font.BOLD);
		            f.setSize(8);

//		            document.add(new Paragraph("Fournisseur Ajouter ", f));
		            document.close();
		         
		        } catch (DocumentException | IOException e) {
		            e.printStackTrace();
		        
		        }
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////
				
			} else { 
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Insertion Fournisseur n'est pas réussie");
				alert.setHeaderText("Echec Ajout Fournisseur");
				alert.showAndWait();
			}
			connexion.close();
			
		} catch (Exception exInsertFournisseur) {
			Logger.getLogger(AjoutFournisseurController.class.getName()).log(Level.SEVERE, null, exInsertFournisseur);
		}

	} else {
		System.out.println("Error Rapl");
	}
	}
//---------------------------------------------------------------------------------
	// EFFACER LES CHAMPS DU TABLEAU
	public void EffacerLesChamps() {
		textFieldRaisonSociale.clear();
		TextFieldSigle.clear();
		TextFieldTelephone.clear();
		TextFieldAdresse.clear();
		TextFieldCourriel.clear();
	}
//--------------------------------------------------------------------------------
//---------------------------------------------------------------------------------	
	// APPEERSU D'UNE LIGNE SUR LE TABLEAU UNE FOIS CLIQUER
	public void AfficheTableViewChampsFournisseur(ActionEvent event) {
		
		String RaisonSocial = textFieldRaisonSociale.getText().trim();
		String Sigl = TextFieldSigle.getText().trim();
		String Telephon = TextFieldTelephone.getText();
		String Adess = TextFieldAdresse.getText().trim();
		String Couriel = TextFieldCourriel.getText();	
		
		Fournisseur mat = tableViewFournisseur.getSelectionModel().getSelectedItem();
		
		if( RaisonSocial.isEmpty() && Sigl.isEmpty() && Telephon.isEmpty() && Adess.isEmpty() && Couriel.isEmpty() && Adess.isEmpty() ){
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Veuillez selectionner une ligne SVP!!!");
			alert.showAndWait();
			
		} else {
			
		textFieldRaisonSociale.setText(mat.getRaisonSociale());		
		TextFieldSigle.setText(mat.getSigle());						
		TextFieldTelephone.setText(mat.getTelephone());				
		TextFieldAdresse.setText(mat.getAdresse());					
		TextFieldCourriel.setText(mat.getEmail());	
	}
	}
//---------------------------------------------------------------------------------
	
	public void ModifierFournisseur() {
		Connection connexion = ConnectionDB.maConnection();
		
		String RaisonSocial = textFieldRaisonSociale.getText();
		String Sigl = TextFieldSigle.getText();
		String Telephon = TextFieldTelephone.getText();
		String Adess = TextFieldAdresse.getText();
		String Couriel = TextFieldCourriel.getText();	
		
		try {	
			
			String updateFournisseur = "UPDATE Fournisseur SET raisonSociale='"+RaisonSocial+"', sigle='"+Sigl+"', telephone='"+Telephon+"', adresse='"+Adess+"', email='"+Couriel+"' WHERE raisonSociale='"+RaisonSocial+"' ";
			System.out.println(updateFournisseur);
				int statut = connexion.createStatement().executeUpdate(updateFournisseur);
				if (statut != 0) {
					
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Fournisseur modifié");
					alert.setHeaderText("Fournisseur a été bien modifié");
					alert.showAndWait();
				}
	//-------------------------------- APPEL METHODES --------------------------
			new notificationThred().start();
			ActualiserDonneesFournisseurTableau();
			EffacerLesChamps();
	//--------------------------------------------------------------------------		
		} catch (Exception exModifierFournisseur) {
			Logger.getLogger(AjoutFournisseurController.class.getName()).log(Level.SEVERE, null, exModifierFournisseur);
		}
	}

//-----------------------------------------------------------------------------------
	// ACTUALISER LES DONNEES SUR TABLEAU
	public void ActualiserDonneesFournisseurTableau() {
		fournisseurList.clear(); // EFFACE REPERTION DONNEES TABLEAU
		Connection connexion = ConnectionDB.maConnection();
		
		String requetteIni = "SELECT `raisonSociale`, `sigle`, `telephone`, `adresse`, `email` FROM `Fournisseur` "; 
		
		try {
			PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteIni);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				fournisseurList.addAll(new Fournisseur(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			
			colonneRaisonSociale.setCellValueFactory(new PropertyValueFactory<>("raisonSociale"));
			colonneSigle.setCellValueFactory(new PropertyValueFactory<>("sigle"));
			colonneTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
			colonneAdesse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			colonneCourriel.setCellValueFactory(new PropertyValueFactory<>("email"));
			tableViewFournisseur.setItems(fournisseurList);
/*			
			for(int i = 0 ; i < tableViewFournisseur.getItems().size() ; i++){
//	            System.out.println("Fournisseur Raison Sociale " + tableViewFournisseur.getItems().get(i).getRaisonSociale());
				
				String p = tableViewFournisseur.getItems().get(i).getRaisonSociale();
				System.out.println(p);
	        }
			*/
//			CodeBarreImage cig = new CodeBarreImage();
			
			
		} catch (Exception exActualiserDonneesFournisseurTableau) {
			Logger.getLogger(AjoutFournisseurController.class.getName()).log(Level.SEVERE, null, exActualiserDonneesFournisseurTableau);
		}
	}
	
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

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
//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------

//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
	public void exporter() {
	
	}
//---------------------------------------------------------------------------------	
//--------------------------------------------------------------------------------

	// BOUTON SUPPRESSION AU NIVEAU DU TABLEAU
		public void suppressionSurLeTableau() {
			
			Fournisseur mat = tableViewFournisseur.getSelectionModel().getSelectedItem();
			System.out.println(mat.getRaisonSociale());
			int selectedIndex = tableViewFournisseur.getSelectionModel().getSelectedIndex();
			Connection connexion = ConnectionDB.maConnection();
			
			if (selectedIndex >= 0) {
//				tableViewFournisseur.getItems().remove(selectedIndex);
				
//			try{
//					String sql = "DELETE FROM `Fournisseur` WHERE telephone = "+ mat.getTelephone() + "  " ;
				
					try {
						
						String sql = "DELETE FROM `Fournisseur` WHERE raisonSociale = '"+mat.getRaisonSociale()+"'   " ;
						PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(sql);
						
						int rs = pst.executeUpdate();
						
						if (rs != 0) {
							System.out.println("Reussi");
						}
						
//						pst.setString(1, mat.getRaisonSociale());
//						
//						pst.executeUpdate();
						connexion.close();
						
						System.out.println("Reussi");
						//--------------------------------------
						tableViewFournisseur.getItems().remove(selectedIndex); // ENLEVE L'ARTICLE SELECTIONNER DANS LE TABLEAU
						// --------------------------------------------------------				
						new notificationThred().start();
						//----------------------------
						System.out.println("Reussi");
					} catch (SQLException e) {
						
						e.printStackTrace();
						System.out.println("Non Reussi");
					}
			}
					
					
					/*  String rsql="DELETE from Fournisseur WHERE raisonSociale=? AND sigle=? AND telephone=? AND adresse=? AND email=?";
			            PreparedStatement ps = (PreparedStatement) connexion.prepareStatement(rsql);
			            Fournisseur fcs = new Fournisseur();
			            
			            ps.setString(1,fcs.getRaisonSociale());
			            ps.setString(2,fcs.getSigle());
			            ps.setString(3,fcs.getTelephone());
			            ps.setString(4, fcs.getEmail());
			            ps.setString(5, fcs.getAdresse());
			            
			            ps.executeUpdate();*/
//			            ps.close();
						
	/*				// --------------------------------------------------------
					Confirmation.alerter();
					// --------------------------------------------------------
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText("Fournisseur a été bien supprimé");
					alert.showAndWait();*/
					
					
//			}  catch (SQLException ex) {
//				Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
//				ex.printStackTrace();
/*//			}
			} else {
				Alert alertt = new Alert(Alert.AlertType.INFORMATION);
				  alertt.setHeaderText("Veuillez choisir un fournisseur SVP");
				  alertt.showAndWait();
			}*/
			 
		}
// -------------------------------------------------------------------------------
// -------------------------------------------------------------------------------
		public void dateDuJourMethode() {
					DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					Date today = Calendar.getInstance().getTime();
					String reportDate = df.format(today);
					duJour.setText(reportDate);
		}		

//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------
public String genereRandom() {	
	
	final Random RANDOM = new SecureRandom();
//	String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
	String letters = "N0123456789";
	String pw = "";
	
      for (int i = 0; i < 8; i++)
      {
    	  int index = (int)(RANDOM.nextDouble()*letters.length());
          pw += letters.substring(index, index+1);     
      }
      textFieldRaisonSociale.setText(pw);
	return pw;
}

//-----------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------

// METHODE VALIDER NOM
@SuppressWarnings("unused")
private boolean validerAdresse() {

	Pattern p = Pattern.compile("[a-zA-Z]+", Pattern.CASE_INSENSITIVE);
	Matcher m = p.matcher(TextFieldAdresse.getText());

	if (m.find() && m.group().equals(TextFieldAdresse.getText())) {
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
private boolean validerTelephone() {

	Pattern p = Pattern.compile("^?[0-9]{2}\\-?[0-9]{3}\\-?[0-9]{2}\\-?[0-9]{2}$", Pattern.CASE_INSENSITIVE);
	Matcher m = p.matcher(TextFieldTelephone.getText());

	if (m.find() && m.group().equals(TextFieldTelephone.getText())) {
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
private boolean validerEmail() {

	Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	Matcher m = p.matcher(TextFieldCourriel.getText());

	if (m.find() && m.group().equals(TextFieldCourriel.getText())) {
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

//	CONTROLE CHIFFRE ENTREE DANS LE TEXFIELD - NUMERO TELEPHONE
	public void ControlChiffPhone() {
	TextFieldTelephone.setOnMouseExited(e -> {
		
		try {
			
		String ch = TextFieldTelephone.getText().substring(0, 2);
		
		if(!(ch.equals("77")||ch.equals("78")||ch.equals("76")||ch.equals("70")||ch.equals("33")||ch.equals("30")) || (!(TextFieldTelephone.getText().length() == 9))  ) {	
			Alert err = new Alert(AlertType.ERROR);
			err.setHeaderText("Error : Le numero saisi est  invalide !"); 
			err.setContentText("Le numéro doit commencer par : \n 77 ou 78 ou 76 ou 70 ou 33 ou 30 \n et s'arrête à 9 chiffres"); 
			err.showAndWait();
		} 
		} catch (Exception e2) {
			Alert err = new Alert(AlertType.ERROR);
			err.setHeaderText("Vous n'avez pas saisi le numéro"); 
			err.showAndWait();
		}
		
	});
	

	}

//-----------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------
		// REDIRECTION SUR ACCUEIL - IMAGE
		@FXML
		private void retourAuMenu() throws IOException {
			Parent pane = FXMLLoader.load(getClass().getResource("/directeurGeneral/Accueil.fxml"));
			paneFournisseur.getChildren().setAll(pane);

		}
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------
		public void exporterEnExcel() {
			
		}
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------
		
	final ObservableList<Fournisseur> data = FXCollections.observableArrayList();
	
	public void rechercheFiltrs(KeyEvent ke) {
		// RECHERCHE - RECHERCHE - RECHERCHE - RECHERCHE - RECHERCHE - RECHERCHE
		
			FilteredList<Fournisseur> filtrFournisseur = new FilteredList<>(data, e -> true);
			
//			recherch.setOnKeyReleased(e -> {
				recherch.textProperty().addListener((observableValue, oldValue, newValue) ->{
					filtrFournisseur.setPredicate((Predicate<? super Fournisseur>) fourniseurReserch->{
						if(newValue == null || newValue.isEmpty()) {
						return true;
						}
						String lowerCaseFiltrer = newValue.toLowerCase();
//						if(fourniseurReserch.getID().contains(newValue)){
//						} else
						if (fourniseurReserch.getRaisonSociale().toLowerCase().contains(lowerCaseFiltrer)) {
							return true;
						} else if( fourniseurReserch.getSigle().toLowerCase().contains(lowerCaseFiltrer) ) {
							return true;
						}
						return false;
					});
				});
				SortedList<Fournisseur>  sortData = new SortedList<>(filtrFournisseur);
				sortData.comparatorProperty().bind(tableViewFournisseur.comparatorProperty());
				tableViewFournisseur.setItems(sortData);
//			});		
	}
	
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------

//----------------------------------------------------------------
	public void genererMot() throws SQLException {  // AUTOCOMPLETE TEXTFIELD - MOTS DANS LA BASES DE DONNEES
		
		Connection connexion = ConnectionDB.maConnection();
		
		String sql = "SELECT raisonSociale from Fournisseur";

		PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(sql);

		ResultSet rs = pst.executeQuery();
		
		String logRole = null;
		
		if (rs.next()) {
			logRole = rs.getString("raisonSociale");
		}
	
		TextFields.bindAutoCompletion(textFieldRaisonSociale, logRole );
	}
	//--------------------------------------------------------------------
	//------------------------------------------------------------------------


	///////////////////////////////////////////////////
	// IMPRIMER
	public void imprimer() {
		
		String id = modifier.getId();
		
//			btValidModif.setVisible(true);
//			modifier.setVisible(false);
		
	
								// DESACTIVER LE BUTTON	
//		if(modifier) {
		this.modifier.disableProperty().bind(BooleanExpression.booleanExpression(this.tableViewFournisseur.getSelectionModel().selectedItemProperty().isNull()));
//		} else {
//		btValidModif.setVisible(false);
//		}
	}

	//-----------------------------------------------------------------

	public void selctionAuto() {
		final ObservableList<Fournisseur> select = tableViewFournisseur.getSelectionModel().getSelectedItems();
		 
		for(int i = 0 ; i < tableViewFournisseur.getItems().size() ; i++){
//		for(int i = 0 ; i < select.size() ; i++){
			tableViewFournisseur.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
//			
//			String p = tableViewFournisseur.getItems().get(i).getRaisonSociale();
//			System.out.println(p);
        }
	}
	//---------------------------------------------- CODE BARRE - Qr_CODE
	//////////////////////////////////////////////////////////////////
	// DEMARRER CODE BARRE AUTOMATIQUE - J'AI CREE UNE METHODE MAIN //
	//////////////////////////////////////////////////////////////////
	public static class codeBarreStart{
		public static void main(String[] args) throws SQLException {

			Connection connexion = ConnectionDB.maConnection();
			String query = "SELECT raisonSociale, sigle from Fournisseur";
			Statement stmt = null;
			stmt = (Statement) connexion.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				CodeBarreImage.createImage(rs.getString(1), rs.getString(1)); // CREE LE CODE IMAGE
				LireCodeBArre.generete_qr( rs.getString(2), rs.getString(1) ); // CREE LE Qr_Code
			}
		}
	}
		//--------------------------------------------------------/////////////////////////////////
		//--------------------------------------------------------/////////////////////////////////

	//AFFICHER DANS LES TEXFIELDS, UNE FOIS ON CLIQUE SUR LE TABLEAU - RECUEILLI LES INFO SUR LE TABLEAU
	private void afficheDetailsFouenisseurSiClikTableau(Fournisseur fournisseurr) {
        if (fournisseurr != null) {
            // ON REMPLACE LES INFOR SUR UNE LIGNE DU TABLEAU DANS LES TEXFIELDS
        	textFieldRaisonSociale.setText(fournisseurr.getRaisonSociale());
        	TextFieldSigle.setText(fournisseurr.getSigle());
        	TextFieldTelephone.setText(fournisseurr.getTelephone());
        	TextFieldAdresse.setText(fournisseurr.getAdresse());
        	TextFieldCourriel.setText(fournisseurr.getEmail());

        } else {
            // ON LES MET VIDE SI ON N'A PAS CLIQUER SUR UNE LIGNE DU TABLEAU
        	textFieldRaisonSociale.setText("");
        	TextFieldSigle.setText("");
        	TextFieldTelephone.setText("");
        	TextFieldAdresse.setText("");
        	TextFieldCourriel.setText("");
          
        }
    }
	

	
	//------------------------------------------------------------
	///////////////////////////////////////////////////////////////
	
	/*		
		@FXML
	    private void initializeFiltr() {
	        // 0. Initialize the columns.
//			colonneRaisonSociale.setCellValueFactory(cellData -> ((Object) cellData.getValue()).firstNameProperty());
//	        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
			
			
			colonneRaisonSociale.setCellValueFactory(new PropertyValueFactory<>("raisonSociale"));
			colonneSigle.setCellValueFactory(new PropertyValueFactory<>("sigle"));
			colonneTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
			colonneAdesse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			colonneCourriel.setCellValueFactory(new PropertyValueFactory<>("email"));
			tableViewFournisseur.setItems(fournisseurList);

	        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
	        FilteredList<Fournisseur> filteredData = new FilteredList<>(masterData, p -> true);

	        // 2. Set the filter Predicate whenever the filter changes.
	        recherch.textProperty().addListener((observable, oldValue, newValue) -> {
	            filteredData.setPredicate(person -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }

	                // Compare first name and last name of every person with filter text.
	                String lowerCaseFilter = newValue.toLowerCase();

	                if (person.getRaisonSociale().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches first name.
	                } else if (person.getSigle().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches last name.
	                }
	                return false; // Does not match.
	            });
	        });

	        // 3. Wrap the FilteredList in a SortedList. 
	        SortedList<Fournisseur> sortedData = new SortedList<>(filteredData);

	        // 4. Bind the SortedList comparator to the TableView comparator.
	        sortedData.comparatorProperty().bind(tableViewFournisseur.comparatorProperty());

	        // 5. Add sorted (and filtered) data to the table.
	        tableViewFournisseur.setItems(sortedData);
	    }
		
		*/
		
		//------------------------------------RECHERCHE
/*		
		ObservableList<Fournisseur> data = FXCollections.observableArrayList();
		ObservableList<Fournisseur> filterdList = FXCollections.observableArrayList();
		//---------------------------------------------------
		public void filterFournisseur(String oldvalue, String newValue) {
			
			
			if(recherch  == null || (newValue.length() < oldvalue.length() ) || newValue==null ) {
				tableViewFournisseur.setItems(data);
			}
			else {
				newValue = newValue.toUpperCase();
				for(Fournisseur founisr : tableViewFournisseur.getItems()) {
					String filterRaisonSociale = founisr.getRaisonSociale();
					String filterSigle= founisr.getSigle();
					if( (filterRaisonSociale.toUpperCase().contains(newValue) ) || ( filterSigle.toUpperCase().contains(newValue)  ) ) {
						filterdList.add(founisr);
					}
				}
				tableViewFournisseur.setItems(filterdList);
			} 
		}
		*/
	//-----------------------------------------------------------------------/////////////////////////////
	//-----------------------------------------------------------------------/////////////////////////////
/*	
    ObservableList<Fournisseur> data = FXCollections.observableArrayList();
	public void iniFilter() {
		
		recherch.textProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable o) {
				if(recherch.textProperty().get().isEmpty()) {
					tableViewFournisseur.setItems(data);
					return;
				}
				ObservableList<Fournisseur> tableItems = FXCollections.observableArrayList();
				ObservableList<TableColumn<Fournisseur, ?>> cols = tableViewFournisseur.getColumns();
				for(int i=0; i<data.size(); i++) {
					for(int j=0; j<cols.size(); j++) {
						TableColumn<Fournisseur, ?> col = cols.get(j);
						String cellValue = col.getCellData( data.get(i) ).toString();
						cellValue = cellValue.toLowerCase();
						if(cellValue.contains(recherch.textProperty().get().toLowerCase())) {
							tableItems.add(data.get(i));
							break;
						}
					}
				}
				tableViewFournisseur.setItems(tableItems);
			}
		});
	}
	
	*/
	//------------------------------------------------------------///////////////////////////////////
	//------------------------------------------------------------///////////////////////////////////
	
//	ObservableList<Fournisseur> masterData = FXCollections.observableArrayList();
	ObservableList<Fournisseur> masterData = FXCollections.observableArrayList();
	
	@FXML
    private void rechercheFournisseur(KeyEvent ke) {
        
        FilteredList<Fournisseur> filterData = new FilteredList<>(masterData,p->true);
        recherch.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(e -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (e.getRaisonSociale().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }
                if (e.getSigle().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (e.getAdresse().toLowerCase().indexOf(typedText) != -1) { 
                    return true;
                }

                return false;
            });
            SortedList<Fournisseur> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableViewFournisseur.comparatorProperty());
            tableViewFournisseur.setItems(sortedList);
            

        });
       
    }
}




















