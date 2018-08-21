package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthScrollBarUI;

import javaBeansClass.Article;
import javaBeansClass.Fournisseur;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import login.LoginController;

public class FactureController implements Initializable{
	
	@FXML private AnchorPane rootPane;
	
	@FXML private static TableView<Article> tbViewFacture;
	
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
	
	public TextField getLoginnFild() { return this.refNamCashier; };
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBoxQuantite(); // CHOIX DU NOMBRE DE PRODUIT QUE LE VEUX
		viderLesCambre();  //VIDE LES CHAMPS AVANT D'AJOUT
		
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

	final ObservableList<String> listPurchase = FXCollections.observableArrayList();
	final ObservableList<Object> listOfPrice = FXCollections.observableArrayList();
	
	static Double total = 0.0;
	
	public void ajouterArticle() {
		   String articleName;
		   Double price, amount;
		   @SuppressWarnings("unused")
		int numberOfArticlee = 0;
		   
		     String quantite = comboBoxQuatite.getSelectionModel().getSelectedItem().toString(); // RECUPERATION VALEUR COMBOBOX
		   			articleName  = nomArticle.getText() ; // RECUPERATION NOM ARTICLE
		   			price = Double.parseDouble(prixUnitairee.getText()); // RECUPERATION PRIX D'ARTICLE
		   
		   amount = price * Integer.parseInt(quantite);  // CALCUL MONTANT TOTAL
		   
		   listPurchase.add(articleName);
		   listOfPrice.add(amount); 
		   
		   //TEST S/O
		   System.out.println(quantite); System.out.println("Nom Article"+articleName);  System.out.println("Prix"+price); System.out.println("Montant"+ amount);
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
	   
	   @SuppressWarnings("unlikely-arg-type")
	public void annulerArticle() {
		   int index, nombre;
		   Double amount, price;
		 /*  
		   nombre = Integer.parseInt(qtite.getText());
		   index = listPurchase.getSelectedInde();
		   amount = Double.parseDouble(listOfPrice.getItem(index));
		   */
		   //
		   String quantite = comboBoxQuatite.getSelectionModel().getSelectedItem().toString(); // RECUPERATION VALEUR COMBOBOX
		   Article indexSelctedTab = tbViewFacture.getSelectionModel().getSelectedItem();
//		   amount = price * Integer.parseInt(quantite);  // CALCUL MONTANT TOTAL
		   //
//		  int quantitee = Integer.parseInt(quantite) - 1;
//		   nombre = nombre - 1;
//		   total = total - amount;
		   
		   listPurchase.remove(indexSelctedTab);
		   listOfPrice.remove(quantite);
		//--------------------------------------   
		   System.out.println("Article seletionner " + indexSelctedTab);  
//		   System.out.println("Total"+amount); System.out.println("Montant Ttal restant"+ total);
		   
//		   qtite.setText(String.valueOf(nombre));
		   refPrixTotal.setText(String.valueOf(total));   
		//----------------------------------------------------------------------------   
		   
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
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
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
		
		
		//-------------------------
		
		

		
		
		
	}	
		
		
		
	}
