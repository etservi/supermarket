package responsableDeStocks;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.StaticInfo;

public class AccueilController implements Initializable{

	@FXML private AnchorPane rootPane;
	Stage stage;
	
	@FXML private Label afficheLogin;
	
	@FXML Circle cercle1, cercle2, cercle3, cercle4, cercle5;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		affichLogin(); // RECUPERER UTILISATEUR CONNECTER
		//====================================================================================================		
				setRotae(cercle1, true, 360, 10);
				setRotae(cercle2, true, 180, 18);
				setRotae(cercle3, true, 360, 10);
				setRotae(cercle4, true, 180, 18);
				setRotae(cercle5, true, 360, 10);
//				setRotae(cercle5, true, 145, 24);
		
	}
	
	//====================================================
	public void affichLogin() {
			
			Connection connexion = ConnectionDB.maConnection();
			String sqll = "SELECT prenom, nom FROM Utilisateur WHERE telephone =" +StaticInfo.USERNAME +" OR login = "+ StaticInfo.USERNAME + " ";
			
				PreparedStatement pst;
				try {
					pst = (PreparedStatement) connexion.prepareStatement(sqll);
					ResultSet rs = pst.executeQuery();
					if(rs.next()) {
						afficheLogin.setText(" Bienvenue "+ rs.getString(1) +" "+ rs.getString(2));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
					
		}
	//====================================================
	
	// REDIRECTION LOGIN
		@FXML
		private void deconnexion(ActionEvent event) throws IOException {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));
			rootPane.getChildren().setAll(pane);
		}
		

	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
		
		// REDIRECTION SUR ACCUEIL - IMAGE
		@FXML
		private void ajouterCategorie() throws IOException { //OUVERTURE DANS UNE AUTRE FENETRE
		
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsableDeStocks/Categorie.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,370,500);
			scene.getStylesheets().add(getClass().getResource("Categorie.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();

		}
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
		// REDIRECTION SUR ACCUEIL - IMAGE
		@FXML
		private void ajouterRayon() throws IOException {  //OUVERTURE DANS UNE AUTRE FENETRE
			
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsableDeStocks/Rayon.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,370,500);
			scene.getStylesheets().add(getClass().getResource("Rayon.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();

		}
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
		// REDIRECTION 
		@FXML
		private void listeRayon() throws IOException {  //OUVERTURE DANS MEME FENETRE
			Parent pane = FXMLLoader.load(getClass().getResource("/responsableDeStocks/Accueil.fxml"));
			rootPane.getChildren().setAll(pane);

		}
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
		// REDIRECTION 
		@FXML
		private void listeCategorie() throws IOException {
			Parent pane = FXMLLoader.load(getClass().getResource("/responsableDeStocks/Accueil.fxml"));
			rootPane.getChildren().setAll(pane);

		}
		
		//-------------------------------------------------------------------------
		//-------------------------------------------------------------------------
			// REDIRECTION 
			@FXML
			private void ouvrireListeCategorieRayon() throws IOException {  //OUVERTURE DANS UNE AUTRE FENETRE
				
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsableDeStocks/ListeCategorieRayon.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root,950,500);
				scene.getStylesheets().add(getClass().getResource("ListeCategorieRayon.css").toExternalForm());
				stage.setScene(scene);
				stage.showAndWait();

			}
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------	
					@FXML
					public void ajouterFournisseur() throws IOException {  
						AnchorPane fxml = FXMLLoader.load(getClass().getResource("/responsableDeStocks/AjoutFournisseur.fxml"));
						rootPane.getChildren().removeAll();
						rootPane.getChildren().setAll(fxml);
				}		
// ----------------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------
					@FXML
					public void statistique() throws IOException {  
						Stage stage = new Stage();
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsableDeStocks/Statistique.fxml"));
						Parent root = loader.load();
						Scene scene = new Scene(root,950, 500);
						scene.getStylesheets().add(getClass().getResource("Statistique.css").toExternalForm());
						stage.setScene(scene);
						stage.showAndWait();
				}
// ----------------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------
					@FXML
					public void notification() throws IOException {  
						AnchorPane fxml = FXMLLoader.load(getClass().getResource("/responsableDeStocks/"));
						rootPane.getChildren().removeAll();
						rootPane.getChildren().setAll(fxml);
				}		
// ----------------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------
					@FXML
					public void listeArticle() throws IOException {  
						
						Stage stage = new Stage();
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsableDeStocks/DetailsArticleParDate.fxml"));
						Parent root = loader.load();
						Scene scene = new Scene(root,950, 500);
						scene.getStylesheets().add(getClass().getResource("DetailsArticleParDate.css").toExternalForm());
						stage.setScene(scene);
						stage.showAndWait();
				}		
// ----------------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------
					
					@FXML
					public void changerMotDePasse() throws IOException {  
											
						Stage stage = new Stage();
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsableDeStocks/ChangerMotDePasse.fxml"));
						Parent root = loader.load();
						Scene scene = new Scene(root,377,330);
						scene.getStylesheets().add(getClass().getResource("ChangerMotDePasse.css").toExternalForm());
						stage.setScene(scene);
						stage.showAndWait();
									}		
					// ----------------------------------------------------------------------------------	
					//-----------------------------------------------------------------------------------
					@FXML
					public void ajouterArticle() throws IOException {  
						AnchorPane fxml = FXMLLoader.load(getClass().getResource("/responsableDeStocks/AjoutArticle.fxml"));
						rootPane.getChildren().removeAll();
						rootPane.getChildren().setAll(fxml);
				}		
// ----------------------------------------------------------------------------------	
//-----------------------------------------------------------------------------------
					//====================================================================================================
					// TRANSITON ROTATION -  ROTATION - TRANSITON ROTATION -  ROTATION -TRANSITON ROTATION -  ROTATION -
					int rotate = 0;
					 // TRANSITION ------------------------------------------ TRANSITION //
					public void setRotae(Circle c, boolean reverse, int angle, int duration) {
						RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration), c);
						rotateTransition.setAutoReverse(reverse);
						rotateTransition.setByAngle(angle);
						rotateTransition.setDelay(Duration.seconds(0));
						rotateTransition.setRate(3);
						rotateTransition.setCycleCount(18);
						rotateTransition.play();
						
					}
					
				//====================================================================================================
}
