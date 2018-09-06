/*package login;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class SplashMain extends Application {
	@Override
	public void start(Stage StgAccueil) {
		
		StgAccueil.setTitle("ADMINISTRATEUR");
		StgAccueil.setResizable(false);
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root,950,500);
//			scene.getStylesheets().add(getClass().getResource("Accueil.css").toExternalForm());
			StgAccueil.setScene(scene);
			StgAccueil.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

//add comment*/