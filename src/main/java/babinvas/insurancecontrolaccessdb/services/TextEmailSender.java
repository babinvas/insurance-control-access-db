package babinvas.insurancecontrolaccessdb.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class TextEmailSender implements EmailSender {
	// to - the email recipient,
	// cc - the recipient of the email copy,
	// bcc - the hidden email recipient,
	// from - the sender of the email
	private String from;
	private String to;
	private String cc;
	private String bcc;

	private final Session session;

	public TextEmailSender(final String username, final String password, String host, int port) {
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

	public void send() {
		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));

			message.setSubject("Заражение Вашего компьютера вирусом COVID-19");

			message.setSentDate(new Date());

			message.setText("Уважаемая Ольга Алексеевна!\n" +
					"\n" +
					"Ваш компьютер заразился вирусом COVID-19.\n" +
					"Для продолжения работы с данным компьютером Вам необходимо:\n" +
					"- либо изолировать его от общества на 14 дней и если он не умрёт продолжить работу на нём;\n" +
					"- либо изолироваться вместе с ним, надеть маску на себя и на компьютер и работать с ним на расстоянии 2 метров от него. Для этого надо либо растянуть свои руки до 2-х метров или использовать подручные средства (к примеру - швабру). Также надо попросить дядю Касперсково привить себя и его для дальнейшего нераспространения вируса COVID-19.\n" +
					"\n" +
					"С уважением,\n" +
					"Ваш сумасшедший отправитель электронных писем\n" +
					"Бабин Вас\n" +
					"Ухахахахаааааа!\n");

			Transport.send(message);
			System.out.println("Успешная отправка сообщения.....");
		} catch (MessagingException e) {
			System.out.println("Ошибка. Возникли проблемы с отправкой сообщения.....\n");
			e.printStackTrace();
		}
	}
}
