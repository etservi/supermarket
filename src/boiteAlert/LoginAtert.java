package boiteAlert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginAtert {
	public static void alertLogin() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Boite Alert");
		alert.setHeaderText(null);

		alert.setContentText("Veuillez entrez votre Nom Utilisateur SVP");
		alert.showAndWait();
	}

	public static void alertPassword() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Boite Alert");
		alert.setHeaderText(null);

		alert.setContentText("Veillez entrez votre mot de passe SVT");
		alert.showAndWait();
	}

	public static void alertChampsSaisi() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Boite Alert");
		alert.setHeaderText(null);

		alert.setContentText("Veillez remplir tout les champs SVT");
		alert.showAndWait();
	}
}
