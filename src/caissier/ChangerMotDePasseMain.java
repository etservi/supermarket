package caissier;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class ChangerMotDePasseMain extends Application {
	@Override
	public void start(Stage StgRayon) {
		
		StgRayon.setResizable(false);
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ChangerMotDePasse.fxml"));
			Scene scene = new Scene(root,377,330);
			scene.getStylesheets().add(getClass().getResource("ChangerMotDePasse.css").toExternalForm());
			StgRayon.setScene(scene);
			StgRayon.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
