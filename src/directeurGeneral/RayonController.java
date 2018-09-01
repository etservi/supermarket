package directeurGeneral;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import baseDeDonnées.ConnectionDB;
import javaBeansClass.Rayon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RayonController implements Initializable{
	
	@FXML private TextField textFielidRayon;
	@FXML private TextField TextFieldDomaine;
	
	@FXML private TableView<Rayon> tableViewRayon;
	
	@FXML private TableColumn<Rayon, String> colonneIdRayon;
	@FXML private TableColumn<Rayon, String> colonneDomaine;
	
	
	
	ObservableList<Rayon> fournisseurList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	//--------------------------------------------------------------------------
	
	@FXML ComboBox<Integer> comboxUser;
	final ObservableList<Integer> optionsComboboxcomboxUser = FXCollections.observableArrayList();

	public void comboBoxUser() {   // NOM DE LA METHODE
		
		try {
			Connection connexion = ConnectionDB.maConnection();
			String sql = "SELECT id FROM Utilisateur";

			PreparedStatement pst = connexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				optionsComboboxcomboxUser.add(new Rayon  (   rs.getInt(1)).getIdRayon()  );
			}
			comboxUser.getItems().addAll(optionsComboboxcomboxUser);
			rs.close();
			connexion.close();
		} catch (SQLException er_rs) {
			er_rs.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------
	
	public void insertrayon() {
		Connection connexion = ConnectionDB.maConnection();
		
		String requette = "INSERT INTO `Rayon`(`idRayon`, `idAdmin`, `domaine`) VALUES ( "+ textFielidRayon.getText() +" , "+ comboxUser.getValue() +", '"+ TextFieldDomaine.getText() +"') ";
		
		try {
			int status = connexion.createStatement().executeUpdate(requette);
			
			if(status != 0) {
				System.out.println("Reussi");
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NOPE");
		}
	}
	
	
	
//	comboxUser, TextFieldDomaine, textFielidRayon
	
	
}
