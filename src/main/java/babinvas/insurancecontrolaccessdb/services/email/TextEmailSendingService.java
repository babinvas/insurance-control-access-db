package babinvas.insurancecontrolaccessdb.services.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class TextEmailSendingService implements EmailSendingService {
	// to - the email recipient,
	// cc - the recipient of the email copy,
	// bcc - the hidden email recipient,
	// from - the sender of the email
	private String from;
	private String to;
	private String cc;
	private String bcc;

	private final Session session;

	public TextEmailSendingService(final String username, final String password, String host, int port) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");

		session = Session.getDefaultInstance(properties,
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public void send(String subject, String text) {
		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			/*
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
			 */

			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));

			message.setSubject(subject);

			message.setSentDate(new Date());

			message.setText(text);

			Transport.send(message);
			System.out.println("Успешная отправка сообщения.....");
		} catch (MessagingException e) {
			System.out.println("Ошибка. Возникли проблемы с отправкой сообщения.....\n");
			e.printStackTrace();
		}
	}

	public Session getSession(final String username, final String password, String host, int port) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "true");

		return Session.getDefaultInstance(properties,
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
	}
}
