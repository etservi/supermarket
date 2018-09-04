package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javaBeansClass.DomaineCategorie;
import javaBeansClass.Rayon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ListeCategorieRayonController implements Initializable {

		@FXML private AnchorPane rootPane;
	
		@FXML private TableView<Rayon> treeviewRayon;
		@FXML private TableView<DomaineCategorie> treeviewCategorie;
		
		@FXML private TableColumn<DomaineCategorie, String> clLibelleCategorie;
		@FXML private TableColumn<DomaineCategorie, Integer> clRayonCat;
		
		@FXML private TableColumn<Rayon, Integer> clRayon;
		@FXML private TableColumn<Rayon, String> clDomaineRayon;
		
		@FXML private JFXTextField tfRechercheRayon, tfRechercheCategorie;
	    
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ActualiserDonneesRayonTableau();
		ActualiserDonneesCategorieTableau();
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
	ObservableList<Rayon> ListeRayon = FXCollections.observableArrayList();

	// ACTUALISER LES DONNEES SUR TABLEAU
	public void ActualiserDonneesRayonTableau() {
		Connection connexion = ConnectionDB.maConnection();
		
		String requetteIni = "SELECT idRayon, domaine FROM Rayon "; 
		
		try {
			PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteIni);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ListeRayon.addAll(new Rayon( rs.getInt(1),rs.getString(2) ));
			}
			
			clRayon.setCellValueFactory(new PropertyValueFactory<>("idRayon"));
			clDomaineRayon.setCellValueFactory(new PropertyValueFactory<>("domaine"));
			
			treeviewRayon.setItems(ListeRayon);
			
		} catch (Exception exActualiserDonneesFournisseurTableau) {
			Logger.getLogger(ListeCategorieRayonController.class.getName()).log(Level.SEVERE, null, exActualiserDonneesFournisseurTableau);
		}
	}
	
	//===========================================================================================================
	//===========================================================================================================
		ObservableList<DomaineCategorie> ListeCategorie = FXCollections.observableArrayList();

		// ACTUALISER LES DONNEES SUR TABLEAU
		public void ActualiserDonneesCategorieTableau() {
			Connection connexion = ConnectionDB.maConnection();
			
			String requetteIni = "SELECT libCategorie, idRayon FROM Categorie "; 
			
			try {
				PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteIni);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					ListeCategorie.addAll(new DomaineCategorie( rs.getString(1),rs.getInt(2) ));
				}
				
				clLibelleCategorie.setCellValueFactory(new PropertyValueFactory<>("libCategorie"));
				clRayonCat.setCellValueFactory(new PropertyValueFactory<>("idRayon"));
				
				treeviewCategorie.setItems(ListeCategorie);
				
			} catch (Exception exActualiserDonneesFournisseurTableau) {
				Logger.getLogger(ListeCategorieRayonController.class.getName()).log(Level.SEVERE, null, exActualiserDonneesFournisseurTableau);
			}
		}

	// =====================================================================================

	
	
	
	
	
	
	
	
	
}
