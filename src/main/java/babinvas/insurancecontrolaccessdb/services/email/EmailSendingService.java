package babinvas.insurancecontrolaccessdb.services.email;

import javax.mail.Session;

public interface EmailSendingService {
	void setFrom(String from);
	void setTo(String to);
	void setCc(String cc);
	void setBcc(String bcc);
	void send(Session session, String subject, String text);
	Session getSession(final String username, final String password, String host, int port);
}
