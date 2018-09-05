package directeurGeneral;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class AjoutFournisseurMain extends Application {
	
	@Override
	public void start(Stage StgAjoutFournisseur) {
		
		StgAjoutFournisseur.setTitle("ADMINISTRATEUR");
		StgAjoutFournisseur.setResizable(false);
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AjoutFournisseur.fxml"));
			Scene scene = new Scene(root,950,500);
			scene.getStylesheets().add(getClass().getResource("AjoutFournisseur.css").toExternalForm());
			StgAjoutFournisseur.setScene(scene);
			StgAjoutFournisseur.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
//		tableViewFournisseur.setItems(ActualiserTableauFournisseur());
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
