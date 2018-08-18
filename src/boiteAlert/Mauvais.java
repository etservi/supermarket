package boiteAlert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Mauvais {
	public static void alerter() {
		Alert alerte = new Alert(AlertType.WARNING);
		alerte.setTitle("Message Aterte");
		alerte.setHeaderText(null);
		alerte.setContentText("Les parametres de connexion fournis sont incorects");
		alerte.showAndWait();
	}
}