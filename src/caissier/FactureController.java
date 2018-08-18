package caissier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javaBeansClass.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FactureController implements Initializable{
	
	@FXML private AnchorPane rootP;
	
	@FXML private TableView<Article> tbViewFacture;
	@FXML private TextField idArticl;
	@FXML private TextField nomArticle;
	@FXML private TextField qtite;
	@FXML private TextField prixUnitairee;
	@FXML private TextField codeBarr;
	@FXML private TextField refPrixTotal;
	
	@FXML private TextField montantverser;
	@FXML private TextField montantReduu;
	@FXML private TextField refNamCashier;
	
	@FXML ImageView imgrtr;  // RETOUR SUR LE MENU
	@FXML ComboBox<Integer> comboBoxQuatite;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBoxQuantite(); // CHOIX DU NOMBRE DE PRODUIT QUE LE VEUX
		
	}
// ---------------------------------------------------
//---------------------------------------
	
	
	public void adf() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
		rootP.getChildren().setAll(pane);
	}
	//------------------------------------------
	// --------------------------------------------
	public void viderLesCambre(){
//		nomArticle.setText("");
//		qtite.setText("");
//		prixUnitairee.setText("");
//		codeBarr.setText("");
//		refPrixTotal.setText("");
//		montantverser.setText("");
//		montantReduu.setText("");
	}

	final ObservableList<String> listPurchase = FXCollections.observableArrayList();
	final ObservableList<Double> listOfPrice = FXCollections.observableArrayList();
	Double total = 0.0;
	
	public void ajouterArticle() {
		 
	   }
	   
	   public void annulerArticle() {
		 
	   }

	//------------------------------------------
	// --------------------CHOIX DU NOMBRE DE PRODUIT QUE L'ON VEUT
	public void comboBoxQuantite() {
		comboBoxQuatite.getItems().addAll(1,2,3,4,5,6,7);
	
	}
//-------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------
				// REDIRECTION SUR ACCUEIL - 
				@FXML
				private void retourAuMenu() throws IOException {
					Parent pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
					rootP.getChildren().setAll(pane);

				}
		//----------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------
}
