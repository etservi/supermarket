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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CategorieController implements Initializable{
	
	@FXML private TextField tfIdCategorie;
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
	@FXML ComboBox<Integer> comboxRayncat;
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
			comboxRayncat.getItems().addAll(optionsComboboxRayon);
			rs.close();
			connexion.close();
		} catch (SQLException er_rs) {
			er_rs.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------
	
	public void ajouterCategorie() {
		
		Connection connexion = ConnectionDB.maConnection();
		
		String requette = "INSERT INTO `Categorie`( `idRayon`, `libCategorie`) VALUES  ( "+ comboxRayncat.getValue() +", '"+ TextFieldDomaine.getText() +"' ) ";
		
		if(TextFieldDomaine.getText().isEmpty()) {
			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setHeaderText("Veuillez remplir tout les champs SVP !!!");
			alerte.showAndWait();
		} else {
		
		try {
			int status = connexion.createStatement().executeUpdate(requette);
			
			if( status != 0 ) {
				Alert alerte = new Alert(AlertType.INFORMATION);
				alerte.setHeaderText(TextFieldDomaine.getText()+" a été bien ajouté");
				alerte.showAndWait();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alerte = new Alert(AlertType.WARNING);
			alerte.setHeaderText("Erreur Ajout de Catégorie "+TextFieldDomaine.getText()+"");
			alerte.showAndWait();
		}
	}
	}
}
