package caissier;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class StatistiqueMain extends Application {
	@Override
	public void start(Stage StgStatistique) {

		StgStatistique.setTitle("ADMINISTRATEUR");
		StgStatistique.setResizable(false);

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Statistique.fxml"));
			Scene scene = new Scene(root, 950, 500);
			scene.getStylesheets().add(getClass().getResource("Statistique.css").toExternalForm());
			StgStatistique.setScene(scene);
			StgStatistique.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
