package babinvas.insurancecontrolaccessdb.services;

public interface EmailSender {
	void setFrom(String from);
	void setTo(String to);
	void setCc(String cc);
	void setBcc(String bcc);
	void send();
}
