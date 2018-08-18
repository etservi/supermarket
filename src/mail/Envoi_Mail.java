package mail;

import javaBeansClass.Mail;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

import org.krysalis.barcode4j.cli.Main;

public class Envoi_Mail {

	public Envoi_Mail(Mail m, String object) throws Exception {

		final String hote = m.getIp_server(); // @IP
		final String fromaddress = m.getAdress_envoi(); // bayenisss@gmail.com
		final String pass = m.getPassword(); // *******************
		final String toaddress = m.getAdresse_reception(); // farba.gaye@uadb.edu.sn
		final String message = m.getMessage();

		/* CONFIGURATION DES PROPRIETES DE CONNEXION AU SERVER SMTP */
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", hote);
		prop.put("mail.smtp.port", "25");
		prop.put("mail.smtp.auth", "true");

		/* CREATION DE LAUTHENTIFICATION AU SERVER */
		Authenticator auth = new Authenticator() {
			@SuppressWarnings("unused")
			protected PasswordAuthentication getPasswordAuthetication() {
				return new PasswordAuthentication(fromaddress, pass);
			}
		};

		/* CREATION DUNE SESSION DE MESSAGERIE */
		Session session = Session.getDefaultInstance(prop, auth);

		/* CREATION DUN MESSAGE MAIL SUR LA SESSION */
		MimeMessage msg = new MimeMessage(session);

		/* ATTRIBUTION DES ATTRIBUT DESTINATAIRE/EXPEDITEUR AU MESSAGE */
		msg.setFrom(new InternetAddress(fromaddress));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toaddress));
		msg.setSubject(object);
		msg.setText(message);

		/* ENVOI DU MESSAGE */
		Transport.send(msg);
		JOptionPane.showMessageDialog(null, "Message Envoyé :-) !");
//      System.out.printf(null, "Message Envoyé :-) !");
	}
}
