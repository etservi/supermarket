package pdfFacture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javaBeansClass.Article;


public class BonArticle {
	
	public static void main(String[] args) {
		fournisseurBon("Fournisseur Entrant", "GAYE", "Farba", "01", "02", "03");
	}
	
	
	public static void fournisseurBon(String header, String a, String b, String c, String d, String e) {
		
		String chemin="Bon/Entrant/pdfTest.pdf";				
		Document document = new Document();
	        
		try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(chemin)));
            document.open();

            Paragraph p = new Paragraph();
            p.add("GESTION COMMERCIALE SUPERMARCHÉ \n ");
            p.add("----------------- \n");
            p.add("DAKAR - 772774465 / 773667724");
            p.add("\n ******************************************************* \n");
            
            Image image = Image.getInstance("src/images/market-basket-.png");
			image.scaleAbsolute(50, 50);
			p.add(image);
			
//            -------------------------------
            for (int i = 0; i < 5; i++) {
            	p.add("\n");
			}
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            // ------------------------------------ENTETE TABLEAU
            
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
         //   table.getDefaultCell().setBorder(Rectangle.NO_BORDER); // ELIMIME LE BORDER
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.getDefaultCell().setFixedHeight(30);
            
            PdfPCell enteteTatb = new PdfPCell(new Paragraph(header));
            enteteTatb.setColspan(5);
            
//            enteteTatb.setBorderColor(new Color(0xC0, 0xC0, 0xC0, 0xC0));
            enteteTatb.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(enteteTatb);
            //--------------------------------------- 
            
            table.addCell(new Phrase(new Chunk("RAISON SOCIALE", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD))));
		    table.addCell(new Phrase(new Chunk("SIGLE", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD))));
		    table.addCell(new Phrase(new Chunk("ADRESSE", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD))));
		    table.addCell(new Phrase(new Chunk("TÉLEPHONE", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD))));
		    table.addCell(new Phrase(new Chunk("E-mail", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD))));
	
		    //	    
		    
            table.addCell(new Phrase(new Chunk(a)));
		    table.addCell(new Phrase(new Chunk(b)));
		    table.addCell(new Phrase(new Chunk(c)));
		    table.addCell(new Phrase(new Chunk(d)));
		    table.addCell(new Phrase(new Chunk(e)));
		    document.add(table);
            
            //-------------------------------------------------------
            Paragraph paragraphList = new Paragraph("A to E:");
            List list = new List(false, 10);
            list.add("A");
            list.add("B");
            list.add("C");
            list.add("D");
            list.add("E");
            paragraphList.add(list);
            document.add(paragraphList);
            
            //--------------------------------------------------------
            Paragraph espaceVide = new Paragraph(" \n");
            for (int i = 0; i < 7; i++) {
            	p.add(espaceVide);
			}
           //------------------------------------------------------
            String tabulation = null;
            for (int i = 0; i < 20; i++) {
				 tabulation ="\t";
			}
            Paragraph piedDePageGauche = new Paragraph("Directeur " + tabulation + " Client");
            piedDePageGauche.setAlignment(Element.ALIGN_LEFT);
            document.add(piedDePageGauche);
            //-------------------------------------------------------
            //-------------------------------------------------------
            
            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);
//            document.add(new Paragraph("Fournisseur Ajouter ", f));
            
            document.close();
            System.err.println("Reussi");
         
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        
        }
	}
//================================================================================================	
}