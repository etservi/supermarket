package serverMailSendMesaz;

import java.util.*;

// DANS GMAIL ON DOIT LA VERIFICATION - ON DONNE 'ACCORD LES APP SECURE
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
	
	public static void main(String [] args) throws MessagingException{  
	 final String username = "bayenisss@gmail.com";
		final String password = "*********";

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", true);
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
//		props.put("mail.smtp.port", "25");
//		props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });

	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("bayenisss@gmail.com"));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("farba.gaye@uadb.edu.sn"));
	        message.setSubject("Testing Subject");  // OBJET DU MESSAGE
	        message.setText("Mail GAYE Farba," + "\n\n pas de spam avec mon mail SVP"); // TEXTE MESSAGE

	        Transport.send(message);

	        System.out.println("Réussi");

	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
      }  
	}
}
