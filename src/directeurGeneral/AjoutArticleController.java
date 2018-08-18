package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import baseDeDonnées.ConnectionDB;
import javaBeansClass.DomaineCategorie;
import javaBeansClass.Fournisseur;
import javaBeansClass.Rayon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AjoutArticleController implements Initializable {
	
	@FXML private AnchorPane rootAjoutArt;
	Stage stage = null;
	
	@FXML Button ajoutRy;
	@FXML Button AjtCAt;
	@FXML Button AjtFrnsseur;
	//-----------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	//--------------------------------------------------------------------------
	@FXML ComboBox<String> comboRaisonSociale;
	final ObservableList<String> optionsComboboxRaisonSociale = FXCollections.observableArrayList();
	
public void comboBoxRaisonSociale() {   // NOM DE LA METHODE
		
		try {
			Connection connexion = ConnectionDB.maConnection();
			String sql = "SELECT DISTINCT raisonSociale FROM Fournisseur";

			PreparedStatement pst = connexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				optionsComboboxRaisonSociale.add(new Fournisseur  (   rs.getString(1)).getRaisonSociale()  );
			}
			comboRaisonSociale.getItems().addAll(optionsComboboxRaisonSociale);
			rs.close();
			connexion.close();
		} catch (SQLException er_rs) {
			er_rs.printStackTrace();
		}
	}
	
	//--------------------------------------------------------------------------
	
	@FXML ComboBox<Integer> comboxRayn;
	final ObservableList<Integer> optionsComboboxRayon = FXCollections.observableArrayList();

	public void comboBoxrayon() {   // NOM DE LA METHODE
		
		try {
			Connection connexion = ConnectionDB.maConnection();
			String sql = "SELECT idRayon FROM Rayon";

			PreparedStatement pst = connexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				optionsComboboxRayon.add(new Rayon  (   rs.getInt(1)).getIdRayon()  );
			}
			comboxRayn.getItems().addAll(optionsComboboxRayon);
			rs.close();
			connexion.close();
		} catch (SQLException er_rs) {
			er_rs.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------
	@FXML ComboBox<Integer> comboCatg ;
	final ObservableList<Integer> listCat  = FXCollections.observableArrayList();
	
	public void comboBoxCategorie() {
		try {
			Connection connexion = ConnectionDB.maConnection();
			String sql = "SELECT DISTINCT idCategoriee FROM DomaineCategorie";

			java.sql.Statement statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				listCat.add(new DomaineCategorie( rs.getInt(1) ).getIdCategoriee() );
			}
			comboCatg.getItems().addAll(listCat);
			rs.close();
			connexion.close();
		} catch (SQLException er_rs) {
			er_rs.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------
	
	public void ouvreRayon() throws IOException {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/directeurGeneral/Rayon.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,370,500);
			scene.getStylesheets().add(getClass().getResource("Rayon.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();
	}
	//-------------------------------------------------------------------------
	public void ouvreCategirie() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/directeurGeneral/Categorie.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,370,500);
		scene.getStylesheets().add(getClass().getResource("Categorie.css").toExternalForm());
		stage.setScene(scene);
		stage.showAndWait();
	}
	//-------------------------------------------------------------------------
	public void ouvreFournisseur() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/directeurGeneral/AjoutFournisseur.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,950, 500);
		scene.getStylesheets().add(getClass().getResource("AjoutFournisseur.css").toExternalForm());
		stage.setScene(scene);
		stage.showAndWait();
	}
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
			// REDIRECTION SUR ACCUEIL - IMAGE
			@FXML
			private void retourAuMenu() throws IOException {
				Parent pane = FXMLLoader.load(getClass().getResource("/directeurGeneral/Accueil.fxml"));
				rootAjoutArt.getChildren().setAll(pane);

			}
	//----------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------
	

}
