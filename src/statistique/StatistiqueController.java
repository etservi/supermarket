package statistique;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javaBeansClass.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class StatistiqueController implements Initializable {

	
	@FXML private PieChart pieChart;
	@FXML Label pourcentz;
	
	
	
	@FXML private BarChart<String, Integer> barChart;
	@FXML private CategoryAxis xAxis;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// PIECHART ---------------------
		try {
			articleVendu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//-------------------------------------------------------------------------------------
	//PIECHART
	
	
	public void articleVendu() throws SQLException {
		
		Connection connexion = ConnectionDB.maConnection();
		String rekett = "SELECT nomProduit, dateVendu FROM Article "; 
		
		PreparedStatement pst = (PreparedStatement) connexion.prepareStatement(rekett);
		ResultSet rs = pst.executeQuery();
		
		String artclMam = null;
		while (rs.next()) {
			artclMam = rs.getString("nomProduit");
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

	
	
	
	
	//--------------------------------------------------------------------------------------
	// BARCHAR - STATISTIQUE
/*
	@SuppressWarnings({ "unchecked", "unused" })
	private ObservableList<Series<String, Double>> getChartes() {
		double javaFxValue = 17.56;
		double ReactJsValue = 17.06;
		double phpValue = 08.25;

		ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();

		Series<String, Double> javaFx = new Series<>();
		Series<String, Double> ReactJs = new Series<>();
		Series<String, Double> PHP = new Series<>();

		javaFx.setName("JavaFx");
		ReactJs.setName("ReactJs");
		PHP.setName("PHP");

		for (int i = 2011; i < 2021; i++) {
			javaFx.getData().add(new XYChart.Data<>(Integer.toString(i), javaFxValue));
			javaFxValue = javaFxValue + 4 * Math.random() - 0.2;

			ReactJs.getData().add(new XYChart.Data<>(Integer.toString(i), ReactJsValue));
			ReactJsValue = ReactJsValue + 4 * Math.random() - 2;

			PHP.getData().add(new XYChart.Data<>(Integer.toString(i), phpValue));
			phpValue = phpValue + 4 * Math.random() - 0.2;
		}
		data.addAll(javaFx, ReactJs, PHP);
		return data;
	}
*/
}
