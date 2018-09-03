package caissier;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import baseDeDonnées.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class StatistiqueController implements Initializable {
	
	@FXML private AnchorPane rootStatistik;

	@FXML PieChart piechart;
	@FXML Label pourrcentage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			articleVendues();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void articleVendues() throws SQLException {
		
		ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
		
		Connection connexion = ConnectionDB.maConnection();
		String sql = "SELECT nomArticleNom,  qteStock FROM Article ";
		
		PreparedStatement pst =  (PreparedStatement) connexion.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			details.add(new PieChart.Data(rs.getString(1), rs.getDouble(2)));
		}
		
		piechart.setData(details);

		for (final PieChart.Data data : piechart.getData()) {

			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					pourrcentage.setText(String.valueOf(data.getPieValue()) +"%");
				}
			});

		}

	}

}
