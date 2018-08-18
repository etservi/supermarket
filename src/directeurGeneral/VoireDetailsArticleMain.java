package directeurGeneral;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class VoireDetailsArticleMain extends Application {
	@Override
	public void start(Stage StgVoireDetailsArticle) {

		StgVoireDetailsArticle.setTitle("ADMINISTRATEUR");
		StgVoireDetailsArticle.setResizable(false);

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("VoireDetailsArticle.fxml"));
			Scene scene = new Scene(root, 950, 500);
			scene.getStylesheets().add(getClass().getResource("VoireDetailsArticle.css").toExternalForm());
			StgVoireDetailsArticle.setScene(scene);
			StgVoireDetailsArticle.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
