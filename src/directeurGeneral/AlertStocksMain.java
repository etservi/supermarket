package directeurGeneral;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class AlertStocksMain extends Application {
	@Override
	public void start(Stage StgAlertStocks) {
		
		StgAlertStocks.setTitle("ADMINISTRATEUR");
		StgAlertStocks.setResizable(false);
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AlertStocks.fxml"));
			Scene scene = new Scene(root,950,500);
			scene.getStylesheets().add(getClass().getResource("AlertStocks.css").toExternalForm());
			StgAlertStocks.setScene(scene);
			StgAlertStocks.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
