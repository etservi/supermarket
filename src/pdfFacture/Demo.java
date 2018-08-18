package pdfFacture;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Demo {

//	public static void main(String[] args) {
//		new Demo().createPDF();
//	}

	public void createPDF() {
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(document,
					new FileOutputStream("/BonsEntrant/pdfTest2.pdf"));
			document.open();
			document.addCreator("Binod by Demo.java");
			document.addAuthor("Binod Suman");
			document.addTitle("First PDF By Binod");
			Image image = Image.getInstance("/images/arrierePlan.jpeg");
			image.scaleAbsolute(50, 50);
			document.add(image);

			Paragraph paragraph = new Paragraph("Ok Test PDf");
			document.add(paragraph);
			document.add(Chunk.SPACETABBING);

			PdfPTable table = new PdfPTable(8);
			table.setWidthPercentage(100);
			float[] columnWidths = new float[] { 7, 20, 9, 9, 9, 9, 5, 3 };
			table.setWidths(columnWidths);

			PdfPCell cell = new PdfPCell();
			cell.setColspan(8);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			table.addCell(" ");
			table.addCell(" ");
			table.addCell("");
			table.addCell("");
			table.addCell("%");
			table.addCell("");
			table.addCell(" ");
			table.addCell(" ");
			document.add(table);
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("******** PDF Created ***************");
	}
}