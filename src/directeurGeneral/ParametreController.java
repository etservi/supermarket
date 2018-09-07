package directeurGeneral;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import baseDeDonnées.ConnectionDB;
import javaBeansClass.Admin;
import javaBeansClass.Article;
import javaBeansClass.Caissier;
import javaBeansClass.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class ParametreController implements Initializable{
	
	@FXML private AnchorPane rootPane;
	
	@FXML private TableView<Utilisateur> tableViewUtilisateur;
	
	@FXML private TableColumn<Utilisateur, String> colonnePrenom;
	@FXML private TableColumn<Utilisateur, String> colonneNom;
	@FXML private TableColumn<Utilisateur, String> colonneRole;
	@FXML private TableColumn<Utilisateur, String> colonneLogin;
	
	
	@FXML private TextField refUtilisateur;
	@FXML private PasswordField refOldPssd,refNewPssd, refConfPssd;

	@FXML private PieChart pieChart;
	@FXML Label pourcentz;
	
	@FXML private TableView<Admin> tdAdmin;
    @FXML private TableView<Caissier> tdCaissier;
	
	
//    @FXML private JFXComboBox<?> comboxCaissier;
	
	// BARCHART ======================================
	@FXML BarChart<Article, String> barrChart;
	@FXML CategoryAxis x;
	@FXML NumberAxis y;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
//		PasswordField textFd = TextFields.createClearablePasswordField();
//		rootPane.getChildren().add(textFd);
//		AnchorPane.setBottomAnchor(textFd, 20d);
//		AnchorPane.setLeftAnchor(textFd, 500d);	
		
		//---------------------------------------
		
		// STATISTIQUE
		try {
			articleVendu();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//---------------------------------------
		ActualiserDonneesUtilisateurTableau();
		//------------------------------------------------------------------------------
//		
	}
	
// -------------------------------------------------------------------
// -------------------------------------------------------------------
	public void changerPassd() {
		Connection connexion = ConnectionDB.maConnection();
	
			try {
				String requetteChange = "SELECT * FROM Utilisateur WHERE (login = '" + refUtilisateur.getText( ) + "' || telephone  = '" + refUtilisateur.getText() + "') AND password= '" + refOldPssd.getText() + "' ";
				
				PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteChange);
				ResultSet rs = pst.executeQuery();

				String logDataBase = null;
				String numPhone = null;
				String motDePass = null;

				if (rs.next()) {
					logDataBase = rs.getString("login");	
					numPhone = rs.getString("telephone");	
					motDePass = rs.getString("password");	
				}

				if ( (refUtilisateur.getText().equals(numPhone) && refOldPssd.getText().equals(motDePass) ) && refNewPssd.getText().equals(refConfPssd.getText())) {
					try {
						String sqlChange = "UPDATE Utilisateur SET password = '" + refNewPssd.getText() + "' WHERE (telephone)  ='" + refUtilisateur.getText() + "'  ";
						System.err.println(sqlChange);
						
						PreparedStatement pst2 = (PreparedStatement) connexion.prepareStatement(sqlChange);
						int rs2 = pst2.executeUpdate();
						
						if (rs2 != 0) {
							Alert alerte = new Alert(AlertType.INFORMATION);
							alerte.setContentText("Votre mot de passe a bien été changé, \n merci de vous reconnecter !");
							alerte.showAndWait();	
						}
						} catch(Exception e) {
							e.printStackTrace();
						
						}
						//-------------------------
				} else if (refUtilisateur.getText() != logDataBase && refOldPssd.getText() != motDePass){
					Alert alerte = new Alert(AlertType.WARNING);
					alerte.setTitle("Evertissement");
					alerte.setContentText("Veuillez utiliser votre numéro de téléphone comme login.");
					alerte.showAndWait();
				}
			} catch (Exception e) {
				Alert alerte = new Alert(AlertType.WARNING);
				alerte.setTitle("Evertissement");
				alerte.setContentText("Veuillez Entrer vos informations.");
				alerte.showAndWait();
				
			}
} 

	
// ----------------------------------------------------------------
// ----------------------------------------------------------------
	public void effacerChamps() {
		refOldPssd.clear();
		refNewPssd.clear();
		refConfPssd.clear();
	}
	
// ----------------------------------------------------------------
// ----------------------------------------------------------------
	// REDIRECTION AJOUTER RAYON
	@FXML
		private void ouvrirRayon(ActionEvent event) throws IOException {
			AnchorPane root = FXMLLoader.load(getClass().getResource("Rayon.fxml"));
			 rootPane.getChildren().setAll(root);
		}
// ----------------------------------------------------------------
// ----------------------------------------------------------------
	// REDIRECTION AJOUTER CATEGORIE
	@FXML
		private void ouvrirCategorie(ActionEvent event) throws IOException {
			AnchorPane paneCategorie = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
			 rootPane.getChildren().setAll(paneCategorie);
		}
// ----------------------------------------------------------------
// ----------------------------------------------------------------
	// REDIRECTION AJOUTER CATEGORIE
	@FXML
		private void retourAuMenu(ActionEvent event) throws IOException {
			AnchorPane paneExit = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
			 rootPane.getChildren().setAll(paneExit);
		}	
// ----------------------------------------------------------------
// ----------------------------------------------------------------
	//-------------------------------------------------------------------------------------
	//PIECHART
	
	
	public void articleVendu() throws SQLException {
		
		Connection connexion = ConnectionDB.maConnection();
		String rekett = "SELECT nomArticleNom, dateVendu FROM Article "; 
		
		PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(rekett);
		ResultSet rs = pst.executeQuery();
		
		String artclMam = null;
		while (rs.next()) {
			artclMam = rs.getString("nomArticleNom");
		}
		
		ObservableList<Data> list = FXCollections.observableArrayList(
//				new PieChart.Data("JavaFX", 70),
//				new PieChart.Data("ReactJs", 50),
//				new PieChart.Data("PHP", 10)
				new PieChart.Data(artclMam, 10)
				
				);
		pieChart.setData(list);
		
		for (final PieChart.Data data : pieChart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					pourcentz.setText(String.valueOf(data.getPieValue() / ( (70+50+10) / 100) ) + "%");
					
				}
			});
		}
	}

	
	//---------------------------------------------------------
	
	
	//=============================================================
	//----------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------
	
	ObservableList<Utilisateur> UtilisateurList = FXCollections.observableArrayList();
		// ACTUALISER LES DONNEES SUR TABLEAU
		public void ActualiserDonneesUtilisateurTableau() {
			UtilisateurList.clear(); // EFFACE REPERTION DONNEES TABLEAU
			Connection connexion = ConnectionDB.maConnection();
			
			String requetteIni = "SELECT nom, prenom, role, login FROM Utilisateur"; 
			
			try {
				PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(requetteIni);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					UtilisateurList.addAll(new Utilisateur(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4) ));
				}
				tableViewUtilisateur.setItems(UtilisateurList);
				
				colonnePrenom.setCellValueFactory(new PropertyValueFactory<>("nom"));
				colonneNom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
				colonneRole.setCellValueFactory(new PropertyValueFactory<>("role"));
				colonneLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
				
			} catch (Exception exActualiserDonneesFournisseurTableau) {
				Logger.getLogger(ParametreController.class.getName()).log(Level.SEVERE, null, exActualiserDonneesFournisseurTableau);
			}
		}

	//=============================================================
		@FXML ComboBox<Integer> comboxAdmin;
		final ObservableList<Integer> optionsComboboxcomboxAdmin = FXCollections.observableArrayList();
		
		public void comboBoxAdmin() {   // NOM DE LA METHODE
			
			try {
				Connection connexion = ConnectionDB.maConnection();
				String sql = "SELECT idAdmin FROM Admin";

				PreparedStatement pst = connexion.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					optionsComboboxcomboxAdmin.add(new Admin ( rs.getInt(1)).getIdAdmin());
				}
				comboxAdmin.getItems().addAll(optionsComboboxcomboxAdmin);
				
				rs.close();
				connexion.close();
			} catch (SQLException er_rs) {
				er_rs.printStackTrace();
			}
		}
		//================================================================
		
		//=============================================================
				@FXML ComboBox<Integer> comboxCaissier;
					final ObservableList<Integer> optionsComboboxcomboxCaissier = FXCollections.observableArrayList();

				public void comboBoxCaissier() {   // NOM DE LA METHODE
					
					try {
						Connection connexion = ConnectionDB.maConnection();
						String sql = "SELECT idCaissier FROM Caissier";

						PreparedStatement pst = connexion.prepareStatement(sql);
						ResultSet rs = pst.executeQuery();

						while (rs.next()) {
							optionsComboboxcomboxCaissier.add(new Caissier ( rs.getInt(1)).getIdCaissier()  );
						}
						comboxCaissier.getItems().addAll(optionsComboboxcomboxCaissier);

						rs.close();
						connexion.close();
					} catch (SQLException er_rs) {
						er_rs.printStackTrace();
					}
				}
				//================================================================
		
	public void validerAdminId() {
		Connection con = ConnectionDB.maConnection();
		
		String sql = "INSERT into Admin WHERE idAdmin = '" + comboxAdmin.getValue() + "' ";
		
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			int rs = pst.executeUpdate();

			if (rs != 0) {
				Alert alertt = new Alert(Alert.AlertType.CONFIRMATION);
				alertt.setHeaderText("Utilisateur Valider");
				alertt.show();
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
			Alert alertt = new Alert(Alert.AlertType.WARNING);
			alertt.setHeaderText(" Validation echec ou \\n Utilisateur deja valider");
			alertt.show();
		}	
	}	
		
	public void validerCaissierId() {
		Connection con = ConnectionDB.maConnection();
		
		String sql = "INSERT into Caissier WHERE idCaissier = '" + comboxCaissier.getValue() + "' ";
		
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			int rs = pst.executeUpdate();

			if (rs != 0) {
				Alert alertt = new Alert(Alert.AlertType.CONFIRMATION);
				alertt.setHeaderText("Utilisateur Valider");
				alertt.show();
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
			Alert alertt = new Alert(Alert.AlertType.WARNING);
			alertt.setHeaderText(" Validation echec ou \n Utilisateur deja valider");
			alertt.show();
		}	
	}	
//=================================================================================================
//=================================================================================================
		
		
}