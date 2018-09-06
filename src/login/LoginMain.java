package login;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jdi.request.DuplicateRequestException;
import com.sun.source.tree.Tree;

import baseDeDonnées.ConnectionDB;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;

public class LoginMain extends Application {
	
	Stage StgLogin;
	@FXML private AnchorPane anchorePane;
	
	@Override
	public void start(Stage StgLogin) throws IOException, InterruptedException {

//		StgLogin.setTitle("ATHENTIFICATION");
		StgLogin.setResizable(false);
		VericationConnexionBaseDeDonnees();
		//----------------------------------------------------------------------------------------
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root, 950, 500);
			scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			StgLogin.setScene(scene);
			StgLogin.show();
			
			FadeTransition transition = new FadeTransition(Duration.seconds(1), root); 
			transition.setFromValue(0);
			transition.setToValue(1);
			transition.setCycleCount(1);
			transition.play();
			
//			new closeOpen().start();	
	}
//=============================================================================================
	public void SplashTest(Stage maimStage) {
		try {
			
			Stage splashStage = new Stage(StageStyle.TRANSPARENT);
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Splash.fxml"));
			Scene scene = new Scene(root, 950, 500);
//			scene.getStylesheets().add(getClass().getResource("Splash.css").toExternalForm());
			maimStage.setScene(scene);
			maimStage.show();
			
			PauseTransition transition = new PauseTransition(Duration.millis(5000));
			transition.setOnFinished(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					splashStage.close();
					maimStage.show();
					
				}
			});
			
		} catch (Exception e) {
			Logger.getLogger(LoginMain.class.getName()).log(Level.SEVERE, null, e);
		}
	}
//=============================================================================================

	public static void main(String[] args) {
		launch(args);
	}
//==============================================================================================
	// CONNEXION DANS LA BASE DE DONNEES
	public void VericationConnexionBaseDeDonnees() {

		Connection connexion = ConnectionDB.maConnection();
		if (connexion == null) {
			System.out.println("Connexion de la base de données n'est pas réussie");
			System.exit(1);
		} else {
			System.out.println("Connexion de la base de données réussie");
		}
	}
//==============================================================================================
	
	
	
	
	
	
	
//==============================================================================================
	
	//===================  THREAD FERMER SCENE
	
	class closeOpen extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(5000);
				System.exit(0);
				
/*				Platform.runLater(new Runnable() {
					@Override
					public void run() {

						Parent root = null;
						try {
							root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
							Scene scene = new Scene(root, 950, 500);
							scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
							StgLogin.setScene(scene);
							StgLogin.show();

							anchorePane.getScene().getWindow().hide();
						} catch (Exception e) {
							Logger.getLogger(LoginMain.class.getName()).log(Level.SEVERE, null, e);
						}

						
					}
				});*/
				
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
//==============================================================================================
}