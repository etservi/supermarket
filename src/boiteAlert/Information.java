package boiteAlert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Information {
	public static void alerter() {
		Alert alerte = new Alert(AlertType.INFORMATION);
		alerte.setTitle("Message Aterte");
		alerte.setHeaderText(null);
		alerte.setContentText("Les parametres de connexion fournis sont incorects");
		alerte.showAndWait();
	}
}