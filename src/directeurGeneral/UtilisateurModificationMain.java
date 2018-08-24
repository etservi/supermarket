package directeurGeneral;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class UtilisateurModificationMain extends Application {
	
	private Stage primaryStage;
	
	@Override
	public void start(Stage StgUserUpdate) {
		
//		StgUserUpdate.setTitle("ADMINISTRATEUR");
		StgUserUpdate.setResizable(false);
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("UtilisateurModification.fxml"));
			Scene scene = new Scene(root,800,500);
			scene.getStylesheets().add(getClass().getResource("UtilisateurModification.css").toExternalForm());
			StgUserUpdate.setScene(scene);
			StgUserUpdate.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	 public Stage getPrimaryStage() {
	        return primaryStage;
	    }
}
