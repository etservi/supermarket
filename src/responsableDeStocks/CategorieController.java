package responsableDeStocks;

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

public class CategorieController implements Initializable{
	
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
	
	public void ajouterFournsseur() {
		
//		String RaisonSocial = textFielidRayon.getText().trim();
//		String Sigl = TextFieldSigle.getText().trim();
//		String Telephon = TextFieldDomaine.getText();
		
		Connection connexion = ConnectionDB.maConnection();
		
	}
}
