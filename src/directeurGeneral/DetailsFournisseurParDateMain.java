package directeurGeneral;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class DetailsFournisseurParDateMain extends Application {
	@Override
	public void start(Stage StgDetailsFournisseurParDate) {

		StgDetailsFournisseurParDate.setTitle("ADMINISTRATEUR");
		StgDetailsFournisseurParDate.setResizable(false);

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("DetailsFournisseurParDate.fxml"));
			Scene scene = new Scene(root, 950, 500);
			scene.getStylesheets().add(getClass().getResource("DetailsFournisseurParDate.css").toExternalForm());
			StgDetailsFournisseurParDate.setScene(scene);
			StgDetailsFournisseurParDate.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
