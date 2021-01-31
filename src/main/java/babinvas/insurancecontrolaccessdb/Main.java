package babinvas.insurancecontrolaccessdb;

import babinvas.insurancecontrolaccessdb.services.EmailSender;
import babinvas.insurancecontrolaccessdb.services.TextEmailSender;

public class Main {
	private static final EmailSender emailSender = new TextEmailSender("username", "password", "host", 465);

	public static void main(String[] args) {
		emailSender.setFrom("from@from.ru");
		emailSender.setTo("to@to.ru");
		emailSender.setCc("cc@cc.ru");
		emailSender.setBcc("bcc@bcc.ru");
		emailSender.send();
	}
}
