package directeurGeneral;
	
import java.io.IOException;

import javaBeansClass.Fournisseur;
import javaBeansClass.Utilisateur;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class AjoutUtilisateurMain extends Application {
	
	private Stage primaryStage;
	
	public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	@Override
	public void start(Stage StgAjoutUtilisateur) {
		
		StgAjoutUtilisateur.setTitle("ADMINISTRATEUR");
		StgAjoutUtilisateur.setResizable(false);
		
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AjoutUtilisateur.fxml"));
			Scene scene = new Scene(root,950,500);
			scene.getStylesheets().add(getClass().getResource("AjoutUtilisateur.css").toExternalForm());
			StgAjoutUtilisateur.setScene(scene);
			StgAjoutUtilisateur.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//--------------------------
	public boolean editerFournisseurDialogue(Utilisateur editFrsrr) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AjoutFournisseurMain.class.getResource("directeurGeneral/UtilisateurModification.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            UtilisateurModificationController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUtilisateur(editFrsrr);

            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
