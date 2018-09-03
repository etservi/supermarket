package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import baseDeDonnées.ConnectionDB;
import javaBeansClass.Rayon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.StaticInfo;

public class ConfirmPassWordController implements Initializable {
	
	@FXML private AnchorPane root;

	@FXML private TextField tfVeriePassWord;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	// --------------------------------------------------------------------------

	public void verifeMotDePasseToParameter(ActionEvent even) throws IOException {

		if (tfVeriePassWord.getText().equals(StaticInfo.PASSWORD)) {
			
			Alert alerte = new Alert(AlertType.INFORMATION);
			alerte.setHeaderText("Mot de passe correct");
			alerte.showAndWait();
			
		} else {
			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setHeaderText("Mot de passe incorrect");
			alerte.showAndWait();
		}
	}
	
	//-------------------------------------------------------------------------------
	
	public void quitterPlaform() {
		//CODE
	}

}
