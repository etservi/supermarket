package codeBarre;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class CodeBarreImage {
	
	public static void createImage(String imageName, String myString) {
		try {
			
			Code128Bean code128 = new Code128Bean();
			code128.setHeight(15f);   // SI ON L'AUGMENTE SA S'INCLINE
			code128.setModuleWidth(0.3);
			code128.setQuietZone(10);
			code128.doQuietZone(true);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
			code128.generateBarcode(canvas, myString);
			canvas.finish();
		
			// ECIRE FICHIER EN PNG
			FileOutputStream fos = new FileOutputStream("codeBarreImage/"+imageName);
			fos.write(baos.toByteArray());
			fos.flush();
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
