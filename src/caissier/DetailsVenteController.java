package caissier;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import directeurGeneral.AjoutFournisseurController;
import javaBeansClass.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class DetailsVenteController implements Initializable {

		@FXML private AnchorPane rootPane;
	
		@FXML private TableView<Article> treeview;
		
	 	@FXML private TableColumn<Article, String> clNomArticle;
	    @FXML private TableColumn<Article, String> clCodeBarre;
	    @FXML private TableColumn<Article, Date> clDate;
	    @FXML private TableColumn<Article, Integer> clQuantite;
	    @FXML private TableColumn<Article, Double> clPrix;
	    @FXML private TableColumn<Article, Double> clMontantVerse;
	    @FXML private TableColumn<Article, Double> clMontantRendu;
	    
	    @FXML private JFXDatePicker dateA;

	    @FXML private JFXDatePicker dateB;
	    
	    @FXML private JFXTextField tfRecherche;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ActualiserDonneesFournisseurTableau();
	}

	// -----------------------------------------------------------------------------------
	// REDIRECTION SUR ACCUEIL - IMAGE
	@FXML 
	private void retourAuMenu() throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
		rootPane.getChildren().setAll(pane);

	}
//===========================================================================================================
//===========================================================================================================
	ObservableList<Article> ArticleList = FXCollections.observableArrayList();

	// ACTUALISER LES DONNEES SUR TABLEAU
	public void ActualiserDonneesFournisseurTableau() {
		Connection connexion = ConnectionDB.maConnection();
		
		String requetteIni = "SELECT nomArticleNom, codeBarre, dateVendu, qteStock, prixAvendre, montantVerse, montantRendu FROM Article WHERE Livrer0nonLivrer1=0 "; 
		
		try {
			PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteIni);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ArticleList.addAll(new Article(rs.getString(1),rs.getString(2),rs.getTimestamp(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7) ));
			}
			
			clNomArticle.setCellValueFactory(new PropertyValueFactory<>("nomArticleNom"));
			clCodeBarre.setCellValueFactory(new PropertyValueFactory<>("codeBarre"));
			clDate.setCellValueFactory(new PropertyValueFactory<>("dateVendu"));
			clQuantite.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
			clPrix.setCellValueFactory(new PropertyValueFactory<>("prixAvendre"));
			clMontantVerse.setCellValueFactory(new PropertyValueFactory<>("montantVerse"));
			clMontantRendu.setCellValueFactory(new PropertyValueFactory<>("montantRendu"));
			
			treeview.setItems(ArticleList);
			
		} catch (Exception exActualiserDonneesFournisseurTableau) {
			Logger.getLogger(DetailsVenteController.class.getName()).log(Level.SEVERE, null, exActualiserDonneesFournisseurTableau);
		}
	}

	// =====================================================================================

	// LISTER PAR DATE LES ARTICLES VENDUS
	
	public void listerArticleVenduCaissierParDate() {
		
		treeview.getItems().clear();  // EFFACER LE CONTENU DU TABLEAU AVANT AJOUT
		Connection connexion = ConnectionDB.maConnection();
		
		String requetteI = "SELECT nomArticleNom, codeBarre, dateVendu, qteStock, prixAvendre, montantVerse, montantRendu  FROM `Article` WHERE dateVendu BETWEEN '" + dateA.getValue()+"' AND '"+ dateB.getValue()+"' AND Livrer0nonLivrer1=0  "; 
		
		
		try {
			PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteI);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ArticleList.addAll(new Article(rs.getString(1),rs.getString(2),rs.getTimestamp(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7) ));
			}
			
			clNomArticle.setCellValueFactory(new PropertyValueFactory<>("nomArticleNom"));
			clCodeBarre.setCellValueFactory(new PropertyValueFactory<>("codeBarre"));
			clDate.setCellValueFactory(new PropertyValueFactory<>("dateVendu"));
			clQuantite.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
			clPrix.setCellValueFactory(new PropertyValueFactory<>("prixAvendre"));
			clMontantVerse.setCellValueFactory(new PropertyValueFactory<>("montantVerse"));
			clMontantRendu.setCellValueFactory(new PropertyValueFactory<>("montantRendu"));
			
			treeview.setItems(ArticleList);
			
			
		} catch (Exception exActualiserDonneesFournisseurTableau) {
			Logger.getLogger(DetailsVenteController.class.getName()).log(Level.SEVERE, null, exActualiserDonneesFournisseurTableau);
		} 
		} 
	
	
	
	
	
	
	
	
	
}
