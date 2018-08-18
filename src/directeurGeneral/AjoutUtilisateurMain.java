package directeurGeneral;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class AjoutUtilisateurMain extends Application {
	@Override
	public void start(Stage StgAjoutUtilisateur) {
		
		StgAjoutUtilisateur.setTitle("ADMINISTRATEUR");
		StgAjoutUtilisateur.setResizable(false);
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AjoutUtilisateur.fxml"));
			Scene scene = new Scene(root,950,500);
			scene.getStylesheets().add(getClass().getResource("AjoutUtilisateur.css").toExternalForm());
			StgAjoutUtilisateur.setScene(scene);
			StgAjoutUtilisateur.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
