package caissier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.itextpdf.text.pdf.TextField;

import javaBeansClass.Article;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class prixArticleController implements Initializable{
	
	@FXML private AnchorPane rootPane;
	
	// LABEL SET
	@FXML Label labIdRayon;
	@FXML Label LabIdCat;
	@FXML Label labNomAr;
	@FXML Label labQtRestante;
	@FXML Label labPrxUnitaire;
	@FXML BorderPane labCodBarr;
	@FXML BorderPane labQrCod;
	
	// LABEL AFFICHE ARTICLE
	
	@FXML TextField tfReserch;
	
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
	@SuppressWarnings("unused")
	private void voirPrixArticle(Article article) {
        if (article != null) {
        	
            // REMPLIR LES INFORMATIONS DES ARTICLES
        	labIdRayon.setText(Integer.toString(article.getId()));
        	LabIdCat.setText(Integer.toString(article.getIdCategorie()));
        	labNomAr.setText(article.getNomArticle());
        	labQtRestante.setText(article.getNomArticle());
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
    }
}
