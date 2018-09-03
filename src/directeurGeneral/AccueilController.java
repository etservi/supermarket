package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.StaticInfo;


public class AccueilController implements Initializable {

	@FXML private AnchorPane rootPane;
	 Stage stage = null;
	@FXML TextField tfFirstName, tfLastName, tfEmail;
	@FXML Label affichUser;
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {

			affichLogin(); // AFFICHE UTILISATEUR
		
		
	}
	
//=============================================================================
	
	public void affichLogin() {
		
		Connection connexion = ConnectionDB.maConnection();
		String sqll = "SELECT prenom, nom FROM Utilisateur WHERE telephone =" +StaticInfo.USERNAME +" OR login = "+ StaticInfo.USERNAME + " ";
		
			PreparedStatement pst;
			try {
				pst = (PreparedStatement) connexion.prepareStatement(sqll);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					affichUser.setText(" Bienvenue " + rs.getString(1) +" "+ rs.getString(2));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
	}
	
//=============================================================================
	// REDIRECTION AJOUTER RAYON
	@FXML
	private void ouvrirRayon() throws IOException {			
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/directeurGeneral/Rayon.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,370,500);
			scene.getStylesheets().add(getClass().getResource("Rayon.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();
	}
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
	// REDIRECTION AJOUTER CATEGORIE
	@FXML
	private void ouvrirCategorie() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/directeurGeneral/Categorie.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,370,500);
		scene.getStylesheets().add(getClass().getResource("Categorie.css").toExternalForm());
		stage.setScene(scene);
		stage.showAndWait();
	}
//------------------------------------------------------------------------------
	// REDIRECTION SUR ACCUEIL
	@FXML
	private void retourAuMenu() throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
		rootPane.getChildren().removeAll();
		rootPane.getChildren().setAll(fxml);
		
		

	}
//------------------------------------------------------------------------------	
	// REDIRECTION SUR ACCUEIL
	@FXML
	private void parametre() throws IOException {
		
		AnchorPane fxml = FXMLLoader.load(getClass().getResource("/directeurGeneral/Parametre.fxml"));
		rootPane.getChildren().removeAll();
		rootPane.getChildren().setAll(fxml);
		
		/*
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/directeurGeneral/ConfirmPassWord.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,370,250);
		scene.getStylesheets().add(getClass().getResource("ConfirmPassWord.css").toExternalForm());
		stage.setScene(scene);
		stage.showAndWait();*/
	}
//------------------------------------------------------------------------------	
	// REDIRECTION LOGIN
	@FXML
	private void deconnexion() throws IOException {
		AnchorPane fxml = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));
		rootPane.getChildren().removeAll();
		rootPane.getChildren().setAll(fxml);	
	}
	//------------------------------------------------------------------------------	
	// REDIRECTION NOUVEAU CLIENT
		@FXML
		private void NouveauClient() throws IOException {
			
			AnchorPane fxml = FXMLLoader.load(getClass().getResource("/directeurGeneral/Facture.fxml"));
			rootPane.getChildren().removeAll();
			rootPane.getChildren().setAll(fxml);
			
		}
		//-----------------------------------------------------------------------------------	
		//-----------------------------------------------------------------------------------
	@FXML
	public void ajouterUtilsateur() throws IOException {  
		AnchorPane fxml = FXMLLoader.load(getClass().getResource("/directeurGeneral/AjoutUtilisateur.fxml"));
		rootPane.getChildren().removeAll();
		rootPane.getChildren().setAll(fxml);
	}			
	//-----------------------------------------------------------------------------------	
	//-----------------------------------------------------------------------------------
	@FXML
	public void ajouterArticle() throws IOException {  
		AnchorPane fxml = FXMLLoader.load(getClass().getResource("/directeurGeneral/AjoutArticle.fxml"));
		rootPane.getChildren().removeAll();
		rootPane.getChildren().setAll(fxml);
}			
// ----------------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------
	@FXML
	public void ajouterFournisseur() throws IOException {  
		AnchorPane fxml = FXMLLoader.load(getClass().getResource("/directeurGeneral/AjoutFournisseur.fxml"));
		rootPane.getChildren().removeAll();
		rootPane.getChildren().setAll(fxml);
}			
	// ----------------------------------------------------------------------------------	
	//-----------------------------------------------------------------------------------
		@FXML
		public void statistique() throws IOException {  
			AnchorPane fxml = FXMLLoader.load(getClass().getResource("/directeurGeneral/Statistique.fxml"));
			rootPane.getChildren().removeAll();
			rootPane.getChildren().setAll(fxml);
	}
		// ----------------------------------------------------------------------------------	
		//-----------------------------------------------------------------------------------
		//------------------------------------------------------------------------------
		// REDIRECTION AJOUTER CATEGORIE
		@FXML
		private void voireDetailsStocks() throws IOException {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/directeurGeneral/DetailsArticleParDate.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,950,500);
			scene.getStylesheets().add(getClass().getResource("DetailsArticleParDate.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();
		}
	
			// ----------------------------------------------------------------------------------	
			//-----------------------------------------------------------------------------------
		@FXML
		private void voireDetailsUtilsateur() throws IOException {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/directeurGeneral/ConfirmPassWord.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,370,250);
			scene.getStylesheets().add(getClass().getResource("ConfirmPassWord.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();
		}
		
// ---------------------------------------------------
		

}