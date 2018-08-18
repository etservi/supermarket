package boiteAlert;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Confirmation {
	
	public static void alerter() {
		Alert alertt = new Alert(Alert.AlertType.CONFIRMATION);
		alertt.setTitle("CONFIRMATION");
		alertt.setHeaderText(null);
		alertt.setContentText("Voulez vous effectuer cette requette");
		Optional<ButtonType> action = alertt.showAndWait();
		if (action.get() == ButtonType.OK) {
			
		}
	}
}