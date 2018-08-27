package directeurGeneral;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class MessageMain extends Application {
	@Override
	public void start(Stage StgVoireDetailsFournisseur) {

//		StgVoireDetailsFournisseur.setTitle("ADMINISTRATEUR");
		StgVoireDetailsFournisseur.setResizable(false);

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Message.fxml"));
			Scene scene = new Scene(root, 950, 500);
			scene.getStylesheets().add(getClass().getResource("Message.css").toExternalForm());
			StgVoireDetailsFournisseur.setScene(scene);
			StgVoireDetailsFournisseur.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
