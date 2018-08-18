package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AccueilController implements Initializable {

	@FXML private AnchorPane rootPane;
	 Stage stage = null;
	@FXML TextField tfFirstName, tfLastName, tfEmail;
	@FXML Label affichUser;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
//		Utilisateur nre = new Utilisateur();
//		nre.getLogin();
//		
//		System.out.println(nre.getLogin());
//		new LoginController().setText(loginnfild);
//		affichUser.setText(new LoginController());
		
		
	}
//------------------------------------------------------------------------------	
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
		Parent pane = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
		rootPane.getChildren().setAll(pane);
		
		

	}
//------------------------------------------------------------------------------	
	// REDIRECTION SUR ACCUEIL
	@FXML
	private void parametre() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/directeurGeneral/Parametre.fxml"));
		rootPane.getChildren().setAll(pane);
	}
//------------------------------------------------------------------------------	
	// REDIRECTION LOGIN
	@FXML
	private void deconnexion() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));
		rootPane.getChildren().setAll(pane);
		
	}
	//------------------------------------------------------------------------------	
	// REDIRECTION NOUVEAU CLIENT
		@FXML
		private void NouveauClient() throws IOException {
			FXMLLoader loader = new FXMLLoader();
			Parent pane = loader.load(new java.io.File("src/directeurGeneral/Facture.fxml").toURI().toURL());
			rootPane.getChildren().setAll(pane);
			
//			javafx.scene.layout.BorderPane bp
//			System.out.println(  ( ( (  (AnchorPane) (rootPane.getChildren().get(0) ) ).getChildren().get(0)).getClass() )  ); 
//			.forEach( (e) -> System.out.println(e + " :" + e.getId() ) );
		}
		//-----------------------------------------------------------------------------------	
		//-----------------------------------------------------------------------------------
	@FXML
	public void ajouterUtilsateur() throws IOException {  
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/directeurGeneral/AjoutUtilisateur.fxml"));
		rootPane.getChildren().setAll(pane);
	}			
	//-----------------------------------------------------------------------------------	
	//-----------------------------------------------------------------------------------
	@FXML
	public void ajouterArticle() throws IOException {  
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/directeurGeneral/AjoutArticle.fxml"));
		rootPane.getChildren().setAll(pane);
}			
// ----------------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------
	@FXML
	public void ajouterFournisseur() throws IOException {  
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/directeurGeneral/AjoutFournisseur.fxml"));
		rootPane.getChildren().setAll(pane);
}			
	// ----------------------------------------------------------------------------------	
	//-----------------------------------------------------------------------------------
		@FXML
		public void statistique() throws IOException {  
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/directeurGeneral/Statistique.fxml"));
			rootPane.getChildren().setAll(pane);
	}
		// ----------------------------------------------------------------------------------	
		//-----------------------------------------------------------------------------------
		
	
			// ----------------------------------------------------------------------------------	
			//-----------------------------------------------------------------------------------
		
// ---------------------------------------------------

}