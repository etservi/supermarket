package responsableDeStocks;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class CategorieMain extends Application {
	@Override
	public void start(Stage StgCategorie) {
		
		StgCategorie.setTitle("ADMINISTRATEUR");
		StgCategorie.setResizable(false);
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Categorie.fxml"));
			Scene scene = new Scene(root,370,500);
			scene.getStylesheets().add(getClass().getResource("Categorie.css").toExternalForm());
			StgCategorie.setScene(scene);
			StgCategorie.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
