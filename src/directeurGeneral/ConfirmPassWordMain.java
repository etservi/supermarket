package directeurGeneral;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class ConfirmPassWordMain extends Application {
	@Override
	public void start(Stage StgRayon) {
		
		StgRayon.setResizable(false);
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ConfirmPassWord.fxml"));
			Scene scene = new Scene(root,370,250);
			scene.getStylesheets().add(getClass().getResource("ConfirmPassWord.css").toExternalForm());
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
