package caissier;


import javafx.application.Application;
import javafx.stage.Stage;
import login.LoginController;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class FactureMain extends Application {

	
	public static String tfName = ""; 
	@Override
	public void start(Stage StgFacture) {

		StgFacture.setTitle("ADMINISTRATEUR");
		StgFacture.setResizable(false);
		
		// /home/gaye/eclipse-workspace/Gestion commerciale supermarché/src/DirecteurGeneral

		try {
			FXMLLoader fxml = new FXMLLoader();
			fxml.setLocation(new java.io.File("src/login/Login.fxml").toURI().toURL());
			AnchorPane root = (AnchorPane) fxml.load(); 
			LoginController controller = fxml.getController();
			TextField tfLF = controller.getLoginnFild();
			tfLF.setText("GAYE"); 
			
			tfLF.setOnAction( (e) -> tfName = tfLF.getText());
						
			Scene scene = new Scene(root, 950, 500);
			scene.getStylesheets().add(getClass().getResource("Facture.css").toExternalForm());
			StgFacture.setScene(scene);
			StgFacture.show();
			StgFacture.getIcons().add(new Image("/images/YES.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	public static void main(String[] args) {
		launch(args);
	}
}
