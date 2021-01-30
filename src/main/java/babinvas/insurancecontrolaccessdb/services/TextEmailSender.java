package babinvas.insurancecontrolaccessdb.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class TextEmailSender implements EmailSender {
	// to - the email recipient,
	// cc - the recipient of the email copy,
	// bcc - the hidden email recipient,
	// from - the sender of the email

	String from;
	String to;
	String cc;
	String bcc;

	String host;
	//String port;

	Properties properties;
	Session session;

	public TextEmailSender(String host) {
		this.host = host;

		properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);

		session = Session.getDefaultInstance(properties);
	}

	public void send(String from, String to, String cc, String bcc) {
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;

		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));

			message.setSubject("Заражение Вашего компьютера вирусом COVID-19");

			message.setText("Уважаемая Георгий Вячеславович!\n" +
					"\n" +
					"Ваш компьютер заразился вирусом COVID-19.\n" +
					"Для продолжения работы с данным компьютером Вам необходимо:\n" +
					"- либо изолировать его от общества на 14 дней и если он не умрёт продолжить работу на нём;\n" +
					"- либо изолироваться вместе с ним, надеть маску на себя и на компьютер и работать с ним на расстоянии 2 метров от него. Для этого надо либо растянуть свои руки до 2-х метров или использовать подручные средства (к примеру - швабру). Также надо попросить дядю Касперсково привить себя и его для дальнейшего нераспространения вируса COVID-19.\n" +
					"\n" +
					"С уважением,\n" +
					"Вам сумасшедший мэйлсендрер Бабин Вас\n" +
					"Ухахахахаааааа!\n");

			Transport.send(message);
			System.out.println("Успешная отправка сообщения.....");
		} catch (MessagingException e) {
			System.out.println("Ошибка. Возникли проблемы с отправкой сообщения.....\n");
			e.printStackTrace();
		}
	}
}
