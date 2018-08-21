package login;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.Bindings;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import directeurGeneral.AccueilController;
import directeurGeneral.FactureController;
import javaBeansClass.Utilisateur;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.BooleanExpression;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginController implements Initializable{
	
	public static String tfName = ""; 
	
	@FXML private AnchorPane paneLogin;
//	private static long temps = 0;
	@FXML private Label signError;
	@FXML public TextField loginnfild;
	@FXML private PasswordField psswFild;
	
	@FXML private Button btValidCon,effacerChp;
	
	int count = 0;
	
	
	public TextField getLoginnFild() { return this.loginnfild; };
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
// -----------------------------------
	
//	-------------------------------------------	
	
		// REDIRECTION SUR ACCUEIL ! CAISSIER / DIRECTEUR / RESPONSABLE DE STOCKS
		@FXML
		private void validerConnexion(ActionEvent event) throws IOException {
			String errorMessage = "";
			
			if(loginnfild.getText().isEmpty() && psswFild.getText().isEmpty()) {
				btValidCon.setVisible(false);
			
			} else {
				btValidCon.setVisible(true);
			try {
				
				Connection connexion = ConnectionDB.maConnection();
				String sql = "SELECT * from Utilisateur WHERE (login=? || telephone=?) AND password=?";

				PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(sql);
				pst.setString(1, loginnfild.getText().trim());
				pst.setString(2, loginnfild.getText().trim());
				pst.setString(3, psswFild.getText());
				ResultSet rs = pst.executeQuery();
				
				//----------------------------------------------

				//----------------------------------------------
				

				String logRole = null;
				
				if (rs.next()) {
					
//					// ------------------------------------
					logRole = rs.getString("role");
					//-------------------------------------
					if (logRole.equalsIgnoreCase("Administrateur")) {
						
						Parent pane = FXMLLoader.load(getClass().getResource("/directeurGeneral/Accueil.fxml"));
						paneLogin.getChildren().setAll(pane);
/*//						
//						new FactureController().myFunction(loginnfild.getText());
						
						FXMLLoader loggedWindow = null;
						loggedWindow = FXMLLoader.load(getClass().getResource("/directeurGeneral/Accueil.fxml")); // here crashes!
						Pane root = loggedWindow.load();

						FactureController controller = loggedWindow.getController();
						controller.myFunction(String.valueOf(loginnfild));

						Stage switchScene = (Stage)((Node)event.getSource()).getScene().getWindow();
						switchScene.setResizable(false);
//						switchScene.setTitle("Welcome " + customer.FirstName + " " + customer.LastName);
						switchScene.setScene(new Scene(root, 800, 500));

						switchScene.show();
						*/
					} else if (logRole.equalsIgnoreCase("Responsable de stock")) {
						
						Parent pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
						paneLogin.getChildren().setAll(pane);
					
						
					} else if (logRole.equalsIgnoreCase("Responsable commercial")) {
//						
						Parent pane = FXMLLoader.load(getClass().getResource("/responsableDeStocks/Accueil.fxml"));
						paneLogin.getChildren().setAll(pane);

					} else {
						
						
						
						System.out.println("Pas confome");
						AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
						paneLogin.getChildren().setAll(pane);
						effacer();
						
					} 
				} else {
					signError.setText("Veuillez vérifier vos identifiants.");
					effacer();
					// Show the error message.
		            Alert alert = new Alert(AlertType.ERROR);
		            alert.setTitle("Utilisateur n'existe pas!!");
		            alert.setHeaderText("Utilisateur incorrect");
		            alert.setContentText(errorMessage);
		            
		            alert.showAndWait();
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
			}	
//			else {
//				
//			}
			
}		
//////////////////////////////////////////
		//////////////////////////

public void effacer() {
	loginnfild.clear();
	psswFild.clear();
	
}		
//---------------------------------------------------
//---------------------------------------------------




	//-----------------------------------------------------------------------
	//-----------------------------------------------------------------------
	///////// RECUPERER LA MARQUE DE L'ORDINATEUR
		public static String marqueDeLordinateur() {
			String markDeLOrdinateur = null;
			try {
				final InetAddress addr = InetAddress.getLocalHost();
				markDeLOrdinateur = new String(addr.getHostName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return markDeLOrdinateur;
		}
	//-----------------------------------------------------------------------
	//----------------------------------------------------------------------

//--------------------------------------------------------------------
		  @FXML  // PERMET DE FERMET LE PROGRAM
		    private void closeProgram(ActionEvent event) throws IOException {
		        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        app_stage.close();
		    }
	
}

