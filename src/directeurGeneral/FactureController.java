package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.controlsfx.control.textfield.TextFields;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javaBeansClass.Article;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import login.StaticInfo;

public class FactureController implements Initializable{
	
	@FXML private AnchorPane rootPane;
	
	@FXML private TableView<Article> tbViewFacture;
	
//	@FXML private TableColumn<Article, Integer> tcIdArticle;
	@FXML private TableColumn<Article, String> tcNom;
	@FXML private TableColumn<Article, Integer> tcCodeBarre;
	@FXML private TableColumn<Article, Integer> tcQuantite;
	@FXML private TableColumn<Article, Double> tcPrixUnitaire;
	
	@FXML private TextField idArticl;
	@FXML private TextField nomArticle;
//	@FXML private TextField qtite;
	@FXML private TextField prixUnitairee;
	@FXML private TextField codeBarr;
	@FXML private TextField refPrixTotal ;
	
	@FXML private TextField montantverser;
	@FXML private TextField montantReduu;
	@FXML private TextField refNamCashier;
	
	@FXML ImageView imgrtr;  // RETOUR SUR LE MENU
	@FXML ComboBox<String> comboBoxQuatite;

	@FXML private Label dateduJour;
	
	@FXML Button btAnnulArticle;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBoxQuantite(); // BOUCLE CHOIX DU NOMBRE DE PRODUIT QUE LE VEUX
		viderLesCambre();  //VIDE LES CHAMPS AVANT D'AJOUT
		dateDuJourMethode(); // AFFICHE DATE AUTOMATIQUE
		//................................................
		affichLogin(); // RECUPERATION NOM LOGIN
		autoCompletePrix();
		//------------------------------------------------
		valideCombox(); 			// VERIFICATION CHIFFRE
		validPrixUnitMtd();			// VERIFICATION CHIFFRE
		valideMontantVerse();		// VERIFICATION CHIFFRE
		valideMontantRenduMtd();	// VERIFICATION CHIFFRE
		validePrixTotalMtd();		// VERIFICATION CHIFFRE
		//-------------------------------------------------
		
		// DESACTIVER LE BOUTTON TANT QU'UN ARTICLE N'EST SELECTIONNE
		this.btAnnulArticle.disableProperty().bind(BooleanExpression.booleanExpression(this.tbViewFacture.getSelectionModel().selectedItemProperty().isNull()));
		//-------------------------------------------------
		
		// COMPLETER LES MOTS AUTOMATIQUES
		try {
			autoCopleteWords();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//--------------------------
		refPrixTotal.setStyle("-fx-text-inner-color: red;");
		montantReduu.setStyle("-fx-text-inner-color: red;");
		//--------------------------------------------------
		tbViewFacture.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tbViewFacture.getSelectionModel().setCellSelectionEnabled(false);
	}
// ---------------------------------------------------
//----------------------------------------------------
	
	//====================================================
	public void affichLogin() {
			
			Connection connexion = ConnectionDB.maConnection();
			String sqll = "SELECT prenom, nom FROM Utilisateur WHERE telephone =" +StaticInfo.USERNAME +" OR login = "+ StaticInfo.USERNAME + " ";
			
				PreparedStatement pst;
				try {
					pst = (PreparedStatement) connexion.prepareStatement(sqll);
					ResultSet rs = pst.executeQuery();
					if(rs.next()) {
						refNamCashier.setText( rs.getString(1) +" "+ rs.getString(2));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
					
	}
	//====================================================
		
	
	public void retourMenu() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
		rootPane.getChildren().removeAll();
		rootPane.getChildren().setAll(pane);

		dateDuJourMethode(); // DATE AUTOMATIQUE
	}
	//---------------------------------------------
	// -------------------------------------------- VIDER LES CHAMPS AVANT D'AJOUT ARTICLE
	public void viderLesCambre(){
		
//		idArticl.setText("");
		nomArticle.setText("");
		prixUnitairee.setText("");
		codeBarr.setText("");
		refPrixTotal.setText("");
		montantverser.setText("");
		montantReduu.setText("");
		prixUnitairee.setText("");
		
		// VIDER LE COMBOBOX
		comboBoxQuatite.getSelectionModel().clearSelection();
		comboBoxQuatite.setValue(null);
		
	}
	
	// AJOUT ARTICLE DANS LE TABLEAU
	final ObservableList<String> listPurchase = FXCollections.observableArrayList();
	final ObservableList<Object> listOfPrice = FXCollections.observableArrayList();
	
	static Double total = 0.0;
	
	public void ajouterArticle() {
				
		   String articleName;
		   Double price, amount;
		   		@SuppressWarnings("unused")
		   int numberOfArticlee = 0;
		   
		   if( valideNom()) {
		   
		     String quantite = comboBoxQuatite.getSelectionModel().getSelectedItem().toString(); // RECUPERATION VALEUR COMBOBOX
		   			articleName  = nomArticle.getText() ; // RECUPERATION NOM ARTICLE
		   			price = Double.parseDouble(prixUnitairee.getText()); // RECUPERATION PRIX D'ARTICLE
		   
		   amount = price * Integer.parseInt(quantite);  // CALCUL MONTANT TOTAL
		   
		   listPurchase.add(articleName); listOfPrice.add(amount); 
		 
		   //------------------------------------------------------------------------------------------------------------------------
		   // LES ARGUMENT DE CES 4 PREMIERS LIGNES AU DESSOUS ET LES ATTRIBUTS DANS LA CLAASSE BEANS DOIVENT CORRESPONDRE 
			tcNom.setCellValueFactory( new PropertyValueFactory<>("nomArticleNom") );
			tcCodeBarre.setCellValueFactory( new PropertyValueFactory<>("codeBarre") );
			tcQuantite.setCellValueFactory( new PropertyValueFactory<>( "qteStock" ));
			tcPrixUnitaire.setCellValueFactory( new PropertyValueFactory<>( "prixUnitaire" ) );
			//-----------------------------------------------------------------------------------------------------------------------
			// REMPLIR LES DONNEES RECUPERER DANS LE TEXTFIELD SUR MON TABLEVIEW
	        String momArtcle =  nomArticle.getText();
	        String barrCod =  codeBarr.getText();
	        
	        int qtte = Integer.parseInt(quantite );
	        Double prixUnit = Double.valueOf( prixUnitairee.getText() );
	        
	        Article model = new Article(momArtcle,barrCod, qtte, prixUnit);
	        tbViewFacture.getItems().addAll(model);	   
	  
	total += amount;
	refPrixTotal.setText( String.valueOf(total) + "  F CFA " ); // DISPALY TOTAL AMOUNT IN TEXTTFIELD
	 } 	     
}
	   
	   public void annulerArticle() throws SQLException {
		   
//		   String articlCancel;
		   Double priceArticlCancel;
		   Double amountarticlCancel;
//		   
		   Article indexSelctedTab = tbViewFacture.getSelectionModel().getSelectedItem();  // index
		   
		   Article priceSelectedtab = tbViewFacture.getSelectionModel().getSelectedItem();  // index
		   Article qtSelecTab = tbViewFacture.getSelectionModel().getSelectedItem();  // index
		   
		   priceArticlCancel = Double.parseDouble(prixUnitairee.getText()); // RECUPERATION PRIX D'ARTICLE
		   
		   amountarticlCancel = priceSelectedtab.getPrixUnitaire() * qtSelecTab.getQteStock() ;   //  CALCUL MONTANT TOTAL
		   
		   total -= amountarticlCancel; //DECREMENTATION PRI LISTE D'ARTICLE
		   //-------------------------
		   int selectedIndex = tbViewFacture.getSelectionModel().getSelectedIndex();
	        if (selectedIndex >= 0) {
	        	
	        	listPurchase.remove(String.valueOf( indexSelctedTab) );
				   listOfPrice.remove(amountarticlCancel); 
				   
				   refPrixTotal.setText( String.valueOf(total) + "  F CFA " ); // DISPALY TOTAL AMOUNT IN TEXTTFIELD
				   
				   System.out.println("Reamining price" + total);
				   
				  tbViewFacture.getItems().remove(selectedIndex); // ENLEVE L'ARTICLE SELECTIONNER DANS LE TABLEAU
	        } else {
	        	Alert alert = new Alert(AlertType.WARNING);
	            alert.setHeaderText("Aucune selection d'Article");
	            alert.setContentText("Selectionnez un Utilisateur dans la tableau SVP.");
	            
	            alert.showAndWait();
	        }   
	   }

	   //===========================VOIRE MONTANT RENDU
	   
	   public void moneyRendu() {
	        	Double  p = ( Double.parseDouble( montantverser.getText() ) ) - total ;
	        	    montantReduu.setText( String.valueOf(p));
	        	    	
	   }
	   
	//------------------------------------------
	// --------------------CHOIX DU NOMBRE DE PRODUIT QUE L'ON VEUT
	public void comboBoxQuantite() {
		for (int i = 1; i < 100; i++) {
			comboBoxQuatite.getItems().addAll(String.valueOf(i));
		}
	}
	
	//-----------------------------------------------
	
	public void dateDuJourMethode() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		dateduJour.setText(reportDate);  // AFFICHER LADATE DU JOUR
 }		
	
	
	
//-------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------
/*// REDIRECTION
	public void test() {
		Parent modify_part_parent = FXMLLoader.load(getClass().getResource("modifyPart.fxml"));
        Scene modify_part_scene = new Scene(modify_part_parent);
        modify_part_scene.getStylesheets().add("style.css");
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modify_part_scene);
        app_stage.show();
	}
	
}*/
	
	//--------------------------------- VERIFICATION CHAMPS VIDE
	
	public void verifivationChampVide() {
		
		String idArt = idArticl.getText();
		String namArt = nomArticle.getText();
		String codBar = codeBarr.getText();
		String prixU = refPrixTotal.getText();
		
		if(idArt.isEmpty() && namArt.isEmpty() && codBar.isEmpty() && prixU.isEmpty() ){
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Veuillez saisir l'id d'Article !!!");
			alert.showAndWait();
		}
				
	}	
		
	//----------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------
		public void validePrixTotalMtd() {  // CE GENRE DE METHODE ON LES APPELLE DIRECTEMENT DANS LA METHODE QUI RECHARGE LES DONNEES AUTOMATIQUE
			refPrixTotal.setOnKeyTyped(e -> {
				String ch = e.getCharacter();
				if (!(ch.equals("0") || ch.equals("1") || ch.equals("2") | ch.equals("3") || ch.equals("4")
						|| ch.equals("5") || ch.equals("6") || ch.equals("7") || ch.equals("8") || ch.equals("9")
						|| ch.equals("BACK_SPACE")) || (!(refPrixTotal.getText().length() < 9))) {
					e.consume();
					java.awt.Toolkit.getDefaultToolkit().beep();
				}
			});
		}
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------	
		public void validPrixUnitMtd() {  // CE GENRE DE METHODE ON LES APPELLE DIRECTEMENT DANS LA METHODE QUI RECHARGE LES DONNEES AUTOMATIQUE
			prixUnitairee.setOnKeyTyped(e -> {
				String ch = e.getCharacter();
				if (!(ch.equals("0") || ch.equals("1") || ch.equals("2") | ch.equals("3") || ch.equals("4")
						|| ch.equals("5") || ch.equals("6") || ch.equals("7") || ch.equals("8") || ch.equals("9")
						|| ch.equals("BACK_SPACE")) || (!(prixUnitairee.getText().length() < 9))) {
					e.consume();
					java.awt.Toolkit.getDefaultToolkit().beep();
				}
			});
		}
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------	
		public void valideMontantRenduMtd() {  // CE GENRE DE METHODE ON LES APPELLE DIRECTEMENT DANS LA METHODE QUI RECHARGE LES DONNEES AUTOMATIQUE
			montantReduu.setOnKeyTyped(e -> {
				String ch = e.getCharacter();
				if (!(ch.equals("0") || ch.equals("1") || ch.equals("2") | ch.equals("3") || ch.equals("4")
						|| ch.equals("5") || ch.equals("6") || ch.equals("7") || ch.equals("8") || ch.equals("9")
						|| ch.equals("BACK_SPACE")) || (!(montantReduu.getText().length() < 9))) {
					e.consume();
					java.awt.Toolkit.getDefaultToolkit().beep();
				}
			});
		}
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------	
		public void valideMontantVerse() {  // CE GENRE DE METHODE ON LES APPELLE DIRECTEMENT DANS LA METHODE QUI RECHARGE LES DONNEES AUTOMATIQUE
			montantverser.setOnKeyTyped(e -> {
				String ch = e.getCharacter();
				if (!(ch.equals("0") || ch.equals("1") || ch.equals("2") | ch.equals("3") || ch.equals("4")
						|| ch.equals("5") || ch.equals("6") || ch.equals("7") || ch.equals("8") || ch.equals("9")
						|| ch.equals("BACK_SPACE")) || (!(montantverser.getText().length() < 9))) {
					e.consume();
					java.awt.Toolkit.getDefaultToolkit().beep();
				}
			});
		}
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
		public void valideCombox() {  // CE GENRE DE METHODE ON LES APPELLE DIRECTEMENT DANS LA METHODE QUI RECHARGE LES DONNEES AUTOMATIQUE
			comboBoxQuatite.setOnKeyTyped(e -> {
				String ch = e.getCharacter();
				if (!(ch.equals("0") || ch.equals("1") || ch.equals("2") | ch.equals("3") || ch.equals("4")
						|| ch.equals("5") || ch.equals("6") || ch.equals("7") || ch.equals("8") || ch.equals("9")
						|| ch.equals("BACK_SPACE")) || (!(comboBoxQuatite.getValue().length() < 9))) {
					e.consume();
					java.awt.Toolkit.getDefaultToolkit().beep();
				}
			});
		}
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
		// METHODE VALIDER NOM
		private boolean valideNom() {

			Pattern p = Pattern.compile("[a-zA-Z ]+", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(nomArticle.getText());

			if (m.find() && m.group().equals(nomArticle.getText())) {
				return true;

			} else {
				Alert alerte = new Alert(AlertType.WARNING);
				alerte.setHeaderText("Entrer un Nom de l'Article SVT!!");
				alerte.showAndWait();
			}
			return false;
		}
		//-------------------------------
		
		// COMPLETER LES MOTS QUAND ON COMEMECE A ECRIRE UN MOT QUI EXISTE DANS LA BASE DE DONNEE
		public void autoCopleteWords() throws SQLException {
			Connection connexion = ConnectionDB.maConnection();
			String rekett = "SELECT nomArticleNom FROM Article "; 
			
			PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(rekett);
			ResultSet rs = pst.executeQuery();
			
			String artclMam = null;
			while (rs.next()) {
				artclMam = rs.getString("nomArticleNom");
				TextFields.bindAutoCompletion(nomArticle, artclMam);  // AUTOCOMPLETE WORDS
			}
		}
		
		//----------------------------------
		
		public void validerVenteArticle() {
			
//			UPDATE `Article` SET `dateAjoutee` = '2014-08-30 21:05:00' WHERE `Article`.`idArticle` = 1;
		}
		
	//
		public void autoCompletePrix()  {
			Connection con = ConnectionDB.maConnection();
			String sqlT = "SELECT * FROM Article";
			
			PreparedStatement pst;
			try {
				pst = (PreparedStatement) con.prepareStatement(sqlT);
				
				ResultSet rs = pst.executeQuery();
				
				String nomComplete = null;
				
				if(rs.next()) {
					nomComplete = rs.getString("nombreArticle");
						
					if(nomArticle.getText().equalsIgnoreCase(nomComplete)) {
						prixUnitairee.setText(rs.getString("prixUnitaire"));
//						nomArticle.setText("nombreArticle");
					} else {
						prixUnitairee.setText("");
//						nomArticle.setText("");
					}
					
				}
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			
		}
//==============================================================================
//==============================================================================
		
		@SuppressWarnings("unchecked")
		public void livrerArticle() throws SQLException {
			Connection con  = ConnectionDB.maConnection(); 
			
			int idRecup = 0;
			String sql1 ="SELECT id FROM Utilisateur WHERE telephone= '"+StaticInfo.USERNAME+"' OR login='"+StaticInfo.USERNAME+"' ";

				PreparedStatement pst1 = (PreparedStatement) con.prepareStatement(sql1);
				ResultSet rs1 = pst1.executeQuery();
				
				if(rs1.next()) {
					idRecup = rs1.getInt("id");
					System.out.println("RECUP id Reussi "+idRecup);
				}
				
			//--------------------------------------------------------------------------------------------------------------------
			//====================================================================================================================
		//// DATE ET TEMPS DU JOUR
			java.util.Date today = new java.util.Date();
			Timestamp dateTimeAutomatique = new java.sql.Timestamp(today.getTime());
			//------------------------------------------------------------------------------------------
			for(TablePosition<Article, ?> pos : tbViewFacture.getSelectionModel().getSelectedCells() ) {
				TableColumn<Article, ?> colum = pos.getTableColumn();
				ObservableValue<?> obs = colum.getCellObservableValue(pos.getRow());
				Object value = obs.getValue();
				System.out.println(value);
				
			//------------------------------------------------------------------------------------------	
				
				int selectedIndex = tbViewFacture.getSelectionModel().getSelectedIndex();
				
				if (selectedIndex >= 0) {
					
					Article mat = tbViewFacture.getSelectionModel().getSelectedItem();
				    
				String sql = "UPDATE `Article` SET `id` = '" + idRecup+"', qteStock='"+comboBoxQuatite.getValue()+"',`montantVerse` = '"+montantverser.getText()+"', `montantRendu` = '"+montantReduu.getText()+"', `dateVendu` = '"+dateTimeAutomatique+"', `Livrer0nonLivrer1` = '0' WHERE nomArticleNom= '"+mat.getNomArticleNom()+"'   ";
				System.out.println(sql);
				
				try {
					PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
					
					int rs = pst.executeUpdate();
					
					if (rs != 0) {
						System.out.println("Reussi");
						tbViewFacture.getItems().remove(selectedIndex); // ENLEVE L'ARTICLE SELECTIONNER DANS LE TABLEAU
						
						Alert alertt = new Alert(Alert.AlertType.INFORMATION);
						alertt.setHeaderText(" Livraison réussie ");
						alertt.show();
						
						pst.close();
						con.close();
					}
					
				} catch (SQLException e) {
//					e.printStackTrace();
					System.out.println("Non Reussi");
					
					Alert alertt = new Alert(Alert.AlertType.WARNING);
					alertt.setHeaderText("Livraision incorrect - Veuillez mettre \n les meme informations existant dans la base de donnees");
					alertt.showAndWait();
				}
				} else {
					System.out.println("Veuillez ajouter et selectionner des  articles"); 
				}
			}
		}	
//===============================================================================
//===============================================================================
		
		@SuppressWarnings("unchecked")
		public void annulerLivraisonArticle() {
			
			for(TablePosition<Article, ?> pos : tbViewFacture.getSelectionModel().getSelectedCells()) {
				TableColumn<Article, ?> colum = pos.getTableColumn();
				ObservableValue<?> obs = colum.getCellObservableValue(pos.getRow());
				Object value = obs.getValue();
				
				System.out.println(value);
			}
		}
		
//================================================================================
//================================================================================
	/*	public void setAerticle() {
			
			
			Connection con = ConnectionDB.maConnection();
			
			String sql = " SELECT * FROM Article    " ;  //  codeBarre LIKE '%_' 
			System.out.println(sql);
			String codeBarrView = null;
			try {
				PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					codeBarrView = rs.getString("codeBarre");
					System.out.println(codeBarrView);
				}	 
				
				if( codeBarrView.equalsIgnoreCase(codeBarr.getText() ) ) {
					System.out.println("Existe");
				}
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		*/
		
//		nomArticle.setText("");
//		prixUnitairee.setText("");
//		codeBarr.setText("");
//		montantverser.setText("");
//		montantReduu.setText("");
//		codeBarr.getText();
		
		
		
		
		
		
		
		
		
		
}
