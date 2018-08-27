package directeurGeneral;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MessageController implements Initializable{
	
	@FXML private TextField tfVotrMail, tfObject, votrPassword, tfDestinataire ;
	
	@FXML private TextArea tareaMesaz;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
	}
	
	public void envoieMessage() {
		 final String username = tfVotrMail.getText() ;  // VOTRE EMAIL
			final String password = votrPassword.getText(); // VOTRE MOT DE PASSE

		    Properties props = new Properties();
		    props.put("mail.smtp.auth", true);
		    props.put("mail.smtp.starttls.enable", true);
		    props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
//			props.put("mail.smtp.port", "25");
//			props.put("mail.smtp.port", "465");
		    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		                protected PasswordAuthentication getPasswordAuthentication() {
		                    return new PasswordAuthentication(username, password);
		                }
		            });

		    try {

		        Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress("bayenisss@gmail.com"));
		        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tfDestinataire.getText()));  // DESTINATAIRE
		        message.setSubject(tfObject.getText());  // OBJET DU MESSAGE
		        message.setText(tareaMesaz.getText()); // TEXTE MESSAGE

		        Transport.send(message);

		        System.out.println("Réussi");

		    } catch (MessagingException e) {
		    	throw new RuntimeException(e);
	      } 
	}
	
}
