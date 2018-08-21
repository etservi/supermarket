package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthScrollBarUI;

import javaBeansClass.Article;
import javaBeansClass.Fournisseur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
	
	@FXML private TableView<Article> tbViewFacture;
	
	@FXML private TableColumn<Article, String> tcIdArticle;
	@FXML private TableColumn<Article, String> tcNom;
	@FXML private TableColumn<Article, String> tcQuantite;
	@FXML private TableColumn<Article, String> tcPrixUnitaire;
	
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
	
	Double total = 0.0;
	
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
		   
		    tcIdArticle.setCellValueFactory( new PropertyValueFactory<>("idArticle") );
			tcNom.setCellValueFactory( new PropertyValueFactory<>("nomArticle") );
			tcQuantite.setCellValueFactory( new PropertyValueFactory<>( "qteStock" ));
			tcPrixUnitaire.setCellValueFactory( new PropertyValueFactory<>( "prixUnitaire" ) );
			//-----------------------------------------------------------------------------------------------------------------------

			int idA = Integer.parseInt( idArticl.getText() );
	        String momArtcle =  nomArticle.getText();
	        int qtte = Integer.parseInt(quantite );
	        Double prixUnit = Double.valueOf( prixUnitairee.getText() );
	        
	        Article model = new Article(idA, momArtcle, qtte, prixUnit);
	        tbViewFacture.getItems().addAll(model);
	        
/*				
	tbViewFacture.setItems( listPurchase );
	tbViewFacture.setItems(String.valueOf(listOfPrice));

	numberOfArticlee = (listPurchase.get(listPurchase.indexOf(numberOfArticlee))).length();		   
		   
	numberOfArticlee = listOfPrice.getItemCount();
	numberOfArticlee = listPurchase.get(String.valueOf(nombreProduit));
	qtite.setText(String.valueOf(numberOfArticlee));
*/    		   
	  
		   total += amount;
		   refPrixTotal.setText( String.valueOf(total) + "  F CFA " ); // DISPALY TOTAL AMOUNT IN TEXTTFIELD
		   
			
		  
	   }
	   
	   public void annulerArticle() {
		   int index, nombre;
		   Double amount, price;
		  /* 
		   nombre = Integer.parseInt(qtite.getText());
		   index = listPurchase.getSelectedInde();
		   amount = Double.parseDouble(listOfPrice.getItem(index));
		   
		   nombre = nombre - 1;
		   total = total - amount;
		   
		   listPurchase.remove(index);
		   listOfPrice.remove(index);
		   qtite.setText(String.valueOf(nombre));
		   refPrixTotal.setText(String.valueOf(total));   
		   */
	   }

	//------------------------------------------
	// --------------------CHOIX DU NOMBRE DE PRODUIT QUE L'ON VEUT
	public void comboBoxQuantite() {
		for (int i = 1; i < 100; i++) {
			comboBoxQuatite.getItems().addAll(String.valueOf(i));
		}
	}
	
	//----------- AJOUTER ARTICLE DANS LE TABLEAU
	
	
	
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
	
}
