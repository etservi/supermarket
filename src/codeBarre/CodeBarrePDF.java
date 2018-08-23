/*
//CE CODE MEST FONCTIONNEL

package codeBarre;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class CodeBarrePDF {

	public static void main(String[] args) {
		CodeBarrePDF.createPDF("test.pdf", "testtt");
		System.out.println("Finished");
	}

	public static void createPDF(String ntp, String myString) {
		Document doc = new Document();
		PdfWriter docWritter = null;
		try {
			docWritter = PdfWriter.getInstance(doc, new FileOutputStream("codeBarreImage/" + ntp));
			doc.addAuthor("GAYE");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("service.sn");
			doc.addTitle("Barre Code test");
			doc.setPageSize(PageSize.LETTER);
//			doc.setPageSize(PageSize.A4);
			doc.open();
			
			PdfContentByte cb = docWritter.getDirectContent();

			Barcode128 code128 = new Barcode128();
			code128.setCode(myString.trim());
			code128.setCodeType(Barcode128.CODE128);
			Image code128Image = code128.createImageWithBarcode(cb, null, null);
			code128Image.setAbsolutePosition(10, 700);
			code128Image.scalePercent(125);
			doc.add(code128Image);
			
			BarcodeEAN codeEAN = new BarcodeEAN();
			codeEAN.setCode(myString.trim());
			code128.setCodeType(BarcodeEAN.EAN13);
			Image codeEANImage = code128.createImageWithBarcode(cb, null, null);
			code128Image.setAbsolutePosition(10, 600);
			code128Image.scalePercent(125);
			doc.add(codeEANImage);

			doc.close();
			docWritter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}


*/