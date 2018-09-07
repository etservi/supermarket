package responsableDeStocks;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;

import Qr_Code.LireCodeBArre;
import baseDeDonnées.ConnectionDB;
import codeBarre.CodeBarreImage;
import javaBeansClass.DomaineCategorie;
import javaBeansClass.Fournisseur;
import javaBeansClass.Rayon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.StaticInfo;

public class AjoutArticleController implements Initializable {
	
	@FXML private AnchorPane rootAjoutArt;
	Stage stage = null;
	
	@FXML Button ajoutRy;
	@FXML Button AjtCAt;
	@FXML Button AjtFrnsseur;
	
	@FXML private Label nomUser;
	
	@FXML private TextField tdCodeBarre, tfNomArticl;
    @FXML private TextField tfQt;
    @FXML private TextField tfPrixUnitaire;
    @FXML private TextField tfPrixDeVente;

	//-----------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		affichLogin();
		
		// APPELLE METHODE - CREER CODE BARRE ========== VOIR LIGNE 245
		try {
			codeBarreStart.main(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//--------------------------------------------------------------------------
	
public void affichLogin() {
		
		Connection connexion = ConnectionDB.maConnection();
		String sqll = "SELECT prenom, nom FROM Utilisateur WHERE telephone =" +StaticInfo.USERNAME +" OR login = "+ StaticInfo.USERNAME + " ";
		
			PreparedStatement pst;
			try {
				pst = (PreparedStatement) connexion.prepareStatement(sqll);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					nomUser.setText( rs.getString(1) +" "+ rs.getString(2) + ", Vous pouvez ajouter vos artciles. " );
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
	}
	
	
	//-----------------------------------------------------------------
	@FXML ComboBox<String> comboRaisonSociale;
	final ObservableList<String> optionsComboboxRaisonSociale = FXCollections.observableArrayList();
	
public void comboBoxRaisonSociale() {   // NOM DE LA METHODE
		
		try {
			Connection connexion = ConnectionDB.maConnection();
			String sql = "SELECT DISTINCT raisonSociale FROM Fournisseur";

			PreparedStatement pst = connexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				optionsComboboxRaisonSociale.add(new Fournisseur  (   rs.getString(1)).getRaisonSociale()  );
			}
			comboRaisonSociale.getItems().addAll(optionsComboboxRaisonSociale);
			rs.close();
			connexion.close();
		} catch (SQLException er_rs) {
			er_rs.printStackTrace();
		}
	}
	
	//--------------------------------------------------------------------------
	
	@FXML ComboBox<Integer> comboxRayn;
	final ObservableList<Integer> optionsComboboxRayon = FXCollections.observableArrayList();

	public void comboBoxrayon() {   // NOM DE LA METHODE
		
		try {
			Connection connexion = ConnectionDB.maConnection();
			String sql = "SELECT idRayon FROM Rayon";

			PreparedStatement pst = connexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				optionsComboboxRayon.add(new Rayon  (   rs.getInt(1)).getIdRayon()  );
			}
			comboxRayn.getItems().addAll(optionsComboboxRayon);
			rs.close();
			connexion.close();
		} catch (SQLException er_rs) {
			er_rs.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------
	@FXML ComboBox<Integer> comboCatg ;
	final ObservableList<Integer> listCat  = FXCollections.observableArrayList();
	
	public void comboBoxCategorie() {
		try {
			Connection connexion = ConnectionDB.maConnection();
			String sql = "SELECT DISTINCT idCategoriee FROM Categorie";

			java.sql.Statement statement = connexion.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				listCat.add(new DomaineCategorie( rs.getInt(1) ).getIdCategoriee() );
			}
			comboCatg.getItems().addAll(listCat);
			rs.close();
			connexion.close();
		} catch (SQLException er_rs) {
			er_rs.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------
	
	public void ouvreRayon() throws IOException {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsableDeStocks/Rayon.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,370,500);
			scene.getStylesheets().add(getClass().getResource("Rayon.css").toExternalForm());
			stage.setScene(scene);
			stage.showAndWait();
	}
	//-------------------------------------------------------------------------
	public void ouvreCategirie() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsableDeStocks/Categorie.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,370,500);
		scene.getStylesheets().add(getClass().getResource("Categorie.css").toExternalForm());
		stage.setScene(scene);
		stage.showAndWait();
	}
	//-------------------------------------------------------------------------
	public void ouvreFournisseur() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsableDeStocks/AjoutFournisseur.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,950, 500);
		scene.getStylesheets().add(getClass().getResource("AjoutFournisseur.css").toExternalForm());
		stage.setScene(scene);
		stage.showAndWait();
	}
	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
			// REDIRECTION SUR ACCUEIL - IMAGE
			@FXML
			private void retourAuMenu() throws IOException {
				Parent pane = FXMLLoader.load(getClass().getResource("/responsableDeStocks/Accueil.fxml"));
				rootAjoutArt.getChildren().setAll(pane);

			}
	//----------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------
			
			public void ajouterArticle() throws SQLException {
				
				Connection con = ConnectionDB.maConnection();
				
				String sqlFirs = "SELECT id FROM Utilisateur WHERE telephone =" + StaticInfo.USERNAME +" OR login = "+ StaticInfo.USERNAME + " ";
				
				PreparedStatement pst = con.prepareStatement(sqlFirs);
				ResultSet rs = pst.executeQuery();
				
				Integer idInsert = null;
				
				if(rs.next()) {
					idInsert = rs.getInt("id");
				}
				

				String sqlAjoutArticle = "INSERT into Article (idRayon, idCategoriee, raisonSociale, id,codeBarre,nomArticleNom, qteStock, prixUnitaire, prixAvendre, Livrer0nonLivrer1  ) VALUES ('" + comboxRayn.getValue() + "','" + comboCatg.getValue() + "','" + comboRaisonSociale.getValue() + "','" + idInsert + "', '" + tdCodeBarre.getText() + "', '"+tfNomArticl.getText() + "', '" + tfQt.getText() + "', '" + tfPrixUnitaire.getText() + "', '" + tfPrixDeVente.getText() + "', 1)";
				System.out.println(sqlAjoutArticle);
				int statut;
				try {
					statut = con.createStatement().executeUpdate(sqlAjoutArticle);
					
					if (statut != 0) {
						
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Article ajouté");
						alert.setHeaderText(tfNomArticl.getText() + " a été bien ajouté");
						alert.showAndWait();
					} else {
						System.out.println("NOPE");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
	//-----------------------------------------------------------------------------------
//=====================================================================================================
			//---------------------------------------------- CODE BARRE - Qr_CODE
			//////////////////////////////////////////////////////////////////
			// DEMARRER CODE BARRE AUTOMATIQUE - J'AI CREE UNE METHODE MAIN //
			//////////////////////////////////////////////////////////////////
			public static class codeBarreStart{
				public static void main(String[] args) throws SQLException {

					Connection connexion = ConnectionDB.maConnection();
					String query = "SELECT nomArticleNom, codeBarre from Article";
					Statement stmt = null;
					stmt = (Statement) connexion.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					while (rs.next()) {
						CodeBarreImage.createImage(rs.getString(1), rs.getString(2)); // CREE LE CODE IMAGE
						LireCodeBArre.generete_qr( rs.getString(1), rs.getString(2) ); // CREE LE Qr_Code
					}
				}
			}
//=======================================================================================================
			
			
}
