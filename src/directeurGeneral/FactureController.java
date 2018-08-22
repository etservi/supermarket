package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthScrollBarUI;

import javaBeansClass.Article;
import javaBeansClass.Fournisseur;
import javafx.beans.binding.BooleanExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import login.LoginController;

public class FactureController implements Initializable{
	
	@FXML private AnchorPane rootPane;
	
	@FXML private TableView<Article> tbViewFacture;
	
	@FXML private TableColumn<Article, Integer> tcIdArticle;
	@FXML private TableColumn<Article, String> tcNom;
	@FXML private TableColumn<Article, Integer> tcQuantite;
	@FXML private TableColumn<Article, Double> tcPrixUnitaire;
	
	@FXML private TextField idArticl;
	@FXML private TextField nomArticle;
//	@FXML private TextField qtite;
	@FXML private TextField prixUnitairee;
	@FXML private TextField codeBarr;
	@FXML private TextField refPrixTotal;
	
	@FXML private TextField montantverser;
	@FXML private TextField montantReduu;
	@FXML private TextField refNamCashier;
	
	@FXML ImageView imgrtr;  // RETOUR SUR LE MENU
	@FXML ComboBox<String> comboBoxQuatite;

	@FXML private Label dateduJour;
	
	@FXML Button btAnnulArticle;
	
	public TextField getLoginnFild() { return this.refNamCashier; };
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBoxQuantite(); // BOUCLE CHOIX DU NOMBRE DE PRODUIT QUE LE VEUX
		viderLesCambre();  //VIDE LES CHAMPS AVANT D'AJOUT
		dateDuJourMethode(); // AFFICHE DATE AUTOMATIQUE
		
		//------------------------------------------------
		valideCombox(); 			// VERIFICATION CHIFFRE
		validPrixUnitMtd();			// VERIFICATION CHIFFRE
		valideMontantVerse();		// VERIFICATION CHIFFRE
		valideMontantRenduMtd();	// VERIFICATION CHIFFRE
		validePrixTotalMtd();		// VERIFICATION CHIFFRE
		//-------------------------------------------------
		
		// DESACTIVER LE BOUTTON TANT QU'UN ARTICLE N'EST SELECTIONNE
		this.btAnnulArticle.disableProperty().bind(BooleanExpression.booleanExpression(this.tbViewFacture.getSelectionModel().selectedItemProperty().isNull()));
	}
// ---------------------------------------------------
//----------------------------------------------------
	
	public void adf() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/directeurGeneral/Accueil.fxml"));
		rootPane.getChildren().setAll(pane);

		dateDuJourMethode(); // DATE AUTOMATIQUE
	}
	//---------------------------------------------
	// -------------------------------------------- VIDER LES CHAMPS AVANT D'AJOUT ARTICLE
	public void viderLesCambre(){
		
		idArticl.setText("");
		nomArticle.setText("");
		prixUnitairee.setText("");
		codeBarr.setText("");
		refPrixTotal.setText("");
		montantverser.setText("");
		montantReduu.setText("");
		
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
		   
		   //TEST 
		   System.out.println(quantite); System.out.println("Nom Article"+articleName);  
		   System.out.println("Prix"+price); System.out.println("Montant"+ amount);
		   //------------------------------------------------------------------------------------------------------------------------
		   // LES ARGUMENT DE CES 4 PREMIERS LIGNES AU DESSOUS ET LES ATTRIBUTS DANS LA CLAASSE BEANS DOIVENT CORRESPONDRE
		    tcIdArticle.setCellValueFactory( new PropertyValueFactory<>("idProduit") );
			tcNom.setCellValueFactory( new PropertyValueFactory<>("nomProduit") );
			tcQuantite.setCellValueFactory( new PropertyValueFactory<>( "qteStock" ));
			tcPrixUnitaire.setCellValueFactory( new PropertyValueFactory<>( "prixUnitaire" ) );
			//-----------------------------------------------------------------------------------------------------------------------
			// REMPLIR LES DONNEES RECUPERER DANS LE TEXTFIELD SUR MON TABLEVIEW
			int idA = Integer.parseInt( idArticl.getText() );
	        String momArtcle =  nomArticle.getText();
	        int qtte = Integer.parseInt(quantite );
	        Double prixUnit = Double.valueOf( prixUnitairee.getText() );
	        
	        Article model = new Article(idA, momArtcle, qtte, prixUnit);
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
	            alert.setContentText("Selectionnez un article dans la tablle SVP.");
	            
	            alert.showAndWait();
	        }   
	   }

	   //-------------------------------------
	   
	   public void moneyRendu() {
		   
		   int selectedIndex = tbViewFacture.getSelectionModel().getSelectedIndex();
	        
	        	
		double  p = Double.parseDouble( refPrixTotal.getText() ) - Double.parseDouble( montantverser.getText() );
	        if (selectedIndex == 0) {
	        	System.out.println("nop");
	        } else
//		montantReduu.setText( String.valueOf( p ));
	        if (selectedIndex >= 0) {
	        	System.out.println(p);
	        }
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
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		dateduJour.setText("Date : " + reportDate);
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
	//------------------------------// RECUPERER USER CONNECTER
	public void myFunction(String userConnect) {
		refNamCashier.setText(userConnect);
	}
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

			Pattern p = Pattern.compile("[a-zA-Z]+", Pattern.CASE_INSENSITIVE);
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
}
