package pdfFacture;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javaBeansClass.Article;
import javafx.scene.image.Image;

public class BonArticleDG {
	
	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		retour(null);
	}

	static Calendar calendar = Calendar.getInstance();
	static Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());

	public static void sortie(Article m) throws FileNotFoundException, DocumentException {

		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream("sortie.pdf"));
			doc.open();
			doc.add(new Paragraph("BON DE SORTIE" + currentTimestamp));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			Image img = new Image("/images/logo.png");
			doc.add((Element) img);
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			PdfPTable table = new PdfPTable(new float[] { 1, 1, 1, 1 });
			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase("Reference"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Libelle"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Service"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Discription"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("m.getReference"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("m.getLibelle"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("m.getNomService"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("m.getDescription"));
			table.addCell(cell);
			doc.add(table);
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));

			doc.add(new Paragraph("le gerant receveur  "));
			doc.close();

		} catch (BadElementException | IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void retour(Article m) throws FileNotFoundException, DocumentException {

		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream("retour.pdf"));
//			PdfPTable pt = new PdfPTable(3);
			doc.open();
			doc.add(new Paragraph("BON DE RETOUR" + currentTimestamp));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			Image img = new Image("/images/logo.png");
			doc.add((Element) img);
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(new float[] { 1, 1, 1, 1, 1, 1 });
			PdfPCell cell = new PdfPCell();
			cell.setPhrase(new Phrase("id Article"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("id Rayon"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("id Catégorie"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Raison sociale"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Nom Article"));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Qt. Stock"));
			table.addCell(cell);
			cell.setPhrase(new Phrase(m.getIdArticle()));
			table.addCell(cell);
			cell.setPhrase(new Phrase(m.getIdRayon()));
			table.addCell(cell);
			cell.setPhrase(new Phrase(m.getIdCategorie()));
			table.addCell(cell);
			cell.setPhrase(new Phrase(m.getRaisonSociale()));
			table.addCell(cell);
			cell.setPhrase(new Phrase(m.getNomArticle()));
			table.addCell(cell);
			cell.setPhrase(new Phrase(m.getQteStock()));
			table.addCell(cell);
			doc.add(table);

			for (int i = 0; i < 16; i++) {
				doc.add(new Paragraph(" "));
			}

			doc.add(new Paragraph("le GERANT DONNEUR "));
			doc.close();

		} catch (BadElementException | IOException ex) {
			ex.printStackTrace();
		}
	}
}