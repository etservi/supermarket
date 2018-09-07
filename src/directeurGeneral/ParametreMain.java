package directeurGeneral;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class ParametreMain extends Application {
	@Override
	public void start(Stage StgParametre) {
		
//		StgParametre.setTitle("ADMINISTRATEUR");
		StgParametre.setResizable(false);
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Parametre.fxml"));
			Scene scene = new Scene(root,950,500);
			scene.getStylesheets().add(getClass().getResource("Parametre.css").toExternalForm());
			StgParametre.setScene(scene);
			StgParametre.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
