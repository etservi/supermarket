package Qr_Code;

// MARCHE AVEC LE LIBRAIRIE ---->>>>>  zxing-javase.jar

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.mysql.jdbc.Statement;

import baseDeDonnées.ConnectionDB;

public class LireCodeBArre {
	/*
	public static void main(String[] args) throws SQLException {
		
		Connection connexion = ConnectionDB.maConnection();
		
		String requette = "SELECT raisonSociale, sigle from Fournisseur";
		
		Statement stmt = null;
		stmt =(Statement) connexion.createStatement();
		ResultSet rs = stmt.executeQuery(requette);
		
		while(rs.next()) {
			LireCodeBArre.generete_qr( rs.getString(2), rs.getString(1) );
			
			
		}	
	}
	*/
	//---------------
	
	public static void generete_qr(String image_name, String qrCodeData) {
		
		try {
			String filePath = "Qr_Code/" + image_name + ".png";
			String charset = "UTF-8";
			
			Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			
			BitMatrix matrix = new MultiFormatWriter().encode( new String(qrCodeData.getBytes(charset), charset), BarcodeFormat.QR_CODE, 200, 200, hintMap );
			
			MatrixToImageWriter.writeToFile( matrix, filePath.substring( filePath.lastIndexOf(".") + 1 ), new File(filePath) );
			
			System.out.println("Creation reussi");
			
		} catch (Exception e) {
			
		}
		
	} 
	
	
	
}











