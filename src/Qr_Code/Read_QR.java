/*
//CE CODE MEST FONCTIONNEL
package Qr_Code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Read_QR {
	
	private static BinaryBitmap binaryBimap;


	public static void main(String[] args) {
		try {
				
			String filePath = "Qr_Code/qrcode.jpg";  // dans ce dossier on copie une image QR_Code pour lire l'image
			String charset = "UTF-8";
			
			Map <EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			
			System.out.println("Donnee lu par par le QR code est :" +readCode( filePath, charset, hintMap ));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@SuppressWarnings("unchecked")
	public static String readCode(String filePath, String charset, Map hintMap ) throws FileNotFoundException, IOException, NotFoundException {
		binaryBimap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath) ))));
		Result qrCoderesult = new MultiFormatReader().decode(binaryBimap, hintMap);
	
		return qrCoderesult.getText();
	}

}





*/