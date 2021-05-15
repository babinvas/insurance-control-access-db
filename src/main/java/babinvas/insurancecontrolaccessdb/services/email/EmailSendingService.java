package babinvas.insurancecontrolaccessdb.services.email;

public interface EmailSendingService {
	void setFrom(String from);
	void setTo(String to);
	void setCc(String cc);
	void setBcc(String bcc);
	void send(String subject, String text);
}
