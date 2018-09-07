package caissier;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javaBeansClass.Article;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class prixArticleController implements Initializable{
	
	@FXML private AnchorPane rootPane;
	
	// LABEL SET
	@FXML private Label labIdRayon;
	@FXML private Label LabIdCat;
	@FXML private Label labNomAr;
	@FXML private Label labQtRestante;
	@FXML private Label labPrxUnitaire;
	@FXML private BorderPane labCodBarr;
	@FXML private BorderPane labQrCod;
	
	// LABEL AFFICHE ARTICLE
	
	@FXML private TextField tfReserch;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//---------------------- AUTOCOMPLETE WORD --------------------------
		try {
			autoCopleteWords();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//-------------------------------------------------------------------	
	}
	
	//-----------------------------------------------------------------------------------
	// REDIRECTION SUR ACCUEIL - IMAGE
	@FXML
	private void retourAuMenu() throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("/caissier/Accueil.fxml"));
		rootPane.getChildren().setAll(pane);

	}
//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------
	@FXML
	private void voirPrixArticle() {
		
		Connection connexion = ConnectionDB.maConnection();
		
		String sql = "SELECT * FROM Article ";
		String sql2 = "SELECT * from Categorie";
		
		System.out.println(sql);
		
		try {
			PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			int idRyn = 0;
			String nomProd = null;
			int qtReste = 0;
			double prxUt = 0.0;
			String verifieCodeBarre = null;
			
//			String tab[] = null;
			
			if(rs.next()) {
				
				System.out.println("existe");
				
				nomProd = rs.getString("nomArticleNom");  System.out.println(nomProd);
				idRyn = rs.getInt("idRayon");			  System.out.println(idRyn);
				qtReste = rs.getInt("qteStock");		  System.out.println(qtReste);
				prxUt = rs.getDouble("prixAvendre");	  System.out.println(prxUt);
				verifieCodeBarre = rs.getString("codeBarre"); System.out.println(verifieCodeBarre); // NOM ARTICLE OU L'ON FAIT LE TEST 
				
				if(nomProd.equalsIgnoreCase(tfReserch.getText())) {
			
					labIdRayon.setText(rs.getString("idRayon"));		 
					LabIdCat.setText(rs.getString("idCategoriee"));		
					labNomAr.setText(rs.getString("nomArticleNom"));		
					labQtRestante.setText(rs.getString("qteStock"));	
					labPrxUnitaire.setText(rs.getString("prixAvendre"));
					
				} else {
					labIdRayon.setText("");
		        	LabIdCat.setText("");
		        	labNomAr.setText("");
		        	labQtRestante.setText("");
		        	labPrxUnitaire.setText("");
				}
			}
//			 else {
//				System.out.println("CONNEXION BASE DE DONNEE NON REUSSI");
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
/*		
        if (article != null) {
        	
            // REMPLIR LES INFORMATIONS DES ARTICLES
        	labIdRayon.setText(Integer.toString(article.getId()));
        	LabIdCat.setText(Integer.toString(article.getIdCategorie()));
//        	labNomAr.setText(article.getNomArticle());
//        	labQtRestante.setText(article.getNomArticle());
        	labPrxUnitaire.setText(Double.toString(article.getPrixUnitaire()));
        	
//        	labCodBarr.setCenter(imageView);  // REMPLIR L'IMAGE
//        	labQrCod.setCenter(imageView);
        } else {
           // VOIRE PRIX EST NULL, SUPPRIMER TOUT LE CONTENU
        	labIdRayon.setText("");
        	LabIdCat.setText("");
        	labNomAr.setText("");
        	labQtRestante.setText("");
        	labPrxUnitaire.setText("");
        
        }
        
        */
    }
	
	//===============================================================
	
	
	// COMPLETER LES MOTS QUAND ON COMEMECE A ECRIRE UN MOT QUI EXISTE DANS LA BASE DE DONNEE
	public void autoCopleteWords() throws SQLException {
		Connection connexion = ConnectionDB.maConnection();
		String rekett = "SELECT nomArticleNom FROM Article "; 
		
		PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(rekett);
		ResultSet rs = pst.executeQuery();
		
		String artclMam = null;
		while (rs.next()) {
			artclMam = rs.getString("nomArticleNom");
			TextFields.bindAutoCompletion(tfReserch, artclMam);  // AUTOCOMPLETE WORDS
		}
	}
	
	
	
	//===============================================================
}
