package directeurGeneral;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javaBeansClass.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class DetailsArticleParDateController implements Initializable{
	
	@FXML private AnchorPane root;
	
	@FXML private TableView<Article> tableViewDetails;

    @FXML private TableColumn<Article, String> colonneNom;
    @FXML private TableColumn<Article, String> colonneIdRayon;
    @FXML private TableColumn<Article, String> colonneIdCqtegorie;
    @FXML private TableColumn<Article, String> colonneRaisonSociale;
    @FXML private TableColumn<Article, String> colonneCodeBarre;
    @FXML private TableColumn<Article, String> colonneQuantite;
    @FXML private TableColumn<Article, String> colonnePrixQchat;
    @FXML private TableColumn<Article, String> colonnePrixVendu;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ActualiserDonneesQrticleDetailsTableau();
		
	}
	
	// ACTUALISER LES DONNEES SUR TABLEAU
	
	ObservableList<Article> articleList = FXCollections.observableArrayList();
	
		public void ActualiserDonneesQrticleDetailsTableau() {
			
			Connection connexion = ConnectionDB.maConnection();
			
			String requetteIni = "SELECT `nomArticleNom`, `idRayon`, `idCategoriee`, `raisonSociale`, `codeBarre`, qteStock, prixUnitaire, prixAvendre  FROM `Article` "; 
			
			try {
				PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteIni);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					articleList.addAll(new Article(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getDouble(7),rs.getDouble(8)));
				}
				
				colonneNom.setCellValueFactory(new PropertyValueFactory<>("nomArticleNom"));
				colonneIdRayon.setCellValueFactory(new PropertyValueFactory<>("idRayon"));
				colonneIdCqtegorie.setCellValueFactory(new PropertyValueFactory<>("idCategoriee"));
				colonneRaisonSociale.setCellValueFactory(new PropertyValueFactory<>("raisonSociale"));
				colonneCodeBarre.setCellValueFactory(new PropertyValueFactory<>("codeBarre"));
				colonneQuantite.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
				colonnePrixQchat.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
				colonnePrixVendu.setCellValueFactory(new PropertyValueFactory<>("prixAvendre"));
				
				tableViewDetails.setItems(articleList);
				
				
			} catch (Exception exActualiserDonneesFournisseurTableau) {
				Logger.getLogger(DetailsArticleParDateController.class.getName()).log(Level.SEVERE, null, exActualiserDonneesFournisseurTableau);
			}
		}
		
	//----------------------------------------------------------------------------
	//----------------------------------------------------------------------------
	
}


