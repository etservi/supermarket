/*
//CE CODE MEST FONCTIONNEL
package Qr_Code;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;



public class Create_QR {
	public static void main(String[] args) {
		try {
			
			String qtCodeData = "gayeFarba";
			String filePath = "src/Qr_Code/codeBarreImage/";
			String charset = "UTF-8";
			
			Map <EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			BitMatrix matrix = new MultiFormatWriter().encode(
					new String(qtCodeData.getBytes(charset), charset),
					BarcodeFormat.QR_CODE, 200, 200, hintMap);
			
			MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf(".") + 1), new File(filePath));
			System.out.println("Creation reussi");
			
					
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
*/