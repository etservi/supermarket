package codeBarre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import baseDeDonnées.ConnectionDB;

public class GenereDynamicCodeBarre {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		PreparedStatement ps = null;
		Connection connection = null;
		ConnectionDB objConnexion = new ConnectionDB();

		connection = objConnexion.maConnection();
		ResultSet rs = null;
		try {
			String query = "SELECT * from Article";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				CodeBarreImage.createImage(rs.getString("codeBarre") + ".png", rs.getString("codeBarre"));
				CodeBarrePDF.createPDF(rs.getString("codeBarre") + ".pdf", rs.getString("codeBarre"));

				System.out.println("Code Barre crée pour " + rs.getString("codeBarre"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
