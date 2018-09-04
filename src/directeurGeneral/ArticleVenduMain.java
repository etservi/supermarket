package directeurGeneral;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class ArticleVenduMain extends Application {
	@Override
	public void start(Stage StgDetailsArticleParDate) {

		StgDetailsArticleParDate.setTitle("ADMINISTRATEUR");
		StgDetailsArticleParDate.setResizable(false);

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("ArticleVendu.fxml"));
			Scene scene = new Scene(root, 950, 500);
			scene.getStylesheets().add(getClass().getResource("ArticleVendu.css").toExternalForm());
			StgDetailsArticleParDate.setScene(scene);
			StgDetailsArticleParDate.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
