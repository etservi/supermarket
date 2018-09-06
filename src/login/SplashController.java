/*package login;


import java.io.IOException;

import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SplashController extends Preloader {
	
	Stage stage;

	@FXML private AnchorPane anchorePane;
	
	private Scene createPreloaderScene() throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Splash.fxml"));
		return new Scene(root);
	}

	Stage StgLogin;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		stage.setScene(createPreloaderScene());
		stage.show();
	}
	
	

	

	class SplashScreen extends Thread {

		@Override
		public void run() {
			try {
				Thread.sleep(5000);

				Platform.runLater(new Runnable() {
					@Override
					public void run() {

						Parent root = null;
						try {
							root = (AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
						} catch (Exception e) {
							Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, e);
						}

						Scene scene = new Scene(root, 950, 500);
						scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
						StgLogin.setScene(scene);
						StgLogin.show();

						anchorePane.getScene().getWindow().hide();
					}
				});

			} catch (InterruptedException e) {
				Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	

}
}*/