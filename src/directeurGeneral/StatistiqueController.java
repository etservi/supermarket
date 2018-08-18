package directeurGeneral;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javaBeansClass.Fournisseur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

public class StatistiqueController implements Initializable{
	
	@FXML PieChart sarArtt;
	
	@FXML private AnchorPane rootStatistik;
	
	
	final ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		analytic();
		
	}
	
	public void analytic() {
		details.addAll(new PieChart.Data("",20),
				new PieChart.Data("bjbkj",20),
				new PieChart.Data(" lnln",20),
				new PieChart.Data("",20),
				new PieChart.Data("",20),
				new PieChart.Data("",20) 
		);
		sarArtt = new PieChart();
		sarArtt.setData(details);
		sarArtt.setTitle("Fournisseur");
		sarArtt.setLegendSide(Side.BOTTOM);
		sarArtt.setLabelsVisible(true);
	}
	
}
