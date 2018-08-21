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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FactureController implements Initializable{
	
	@FXML private AnchorPane rootP;
	
	@FXML private TableView<Article> tbViewFacture;
	
	@FXML private TableColumn<Article, Integer> tcIdArticle;
	@FXML private TableColumn<Article, String> tcNom;
	@FXML private TableColumn<Article, Integer> tcQuantite;
	@FXML private TableColumn<Article, Double> tcPrixUnitaire;
	
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

		
		
	}
// ---------------------------------------------------
//---------------------------------------
	
	
	public void adf() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
		rootP.getChildren().setAll(pane);
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



	   
	   public void annulerArticle() {
		 
	   }

		//------------------------------------------
	
//-----------------------------------------------
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
