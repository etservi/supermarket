package directeurGeneral;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class AjoutArticleMain extends Application {
	@Override
	public void start(Stage StgAjoutArticle) {
		
		StgAjoutArticle.setTitle("ADMINISTRATEUR");
		StgAjoutArticle.setResizable(false);
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AjoutArticle.fxml"));
			Scene scene = new Scene(root,950,500);
			scene.getStylesheets().add(getClass().getResource("AjoutArticle.css").toExternalForm());
			StgAjoutArticle.setScene(scene);
			StgAjoutArticle.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
