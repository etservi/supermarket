package serverMailSendMesaz;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoieMail {

	public static void main(String[] args) {
		try {
			String host = "smtp.gmail.com";
			String user = "bayenisss@gmail.com";
			String pass = "*********";
			String to = "farba.gaye@uadb.edu.sn"; // receiver email
			String from = "bayenisss@gmail.com";
			String subject = "Voila la confirmation ";
			String messageText = "Ceci est un teste Mail";
			boolean sessionDebug = false;

			Properties props = System.getProperties();
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.port", "25");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

//			java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("Message Bien envoyé");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
