package babinvas.insurancecontrolaccessdb.services.email;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.mail.*;

import static org.junit.jupiter.api.Assertions.*;

class TextEmailSendingServiceTest {
	private TextEmailSendingService textEmailSendingService;
	private GreenMail greenMail;

	@BeforeEach
	private void startGreenMail() {
		textEmailSendingService = new TextEmailSendingService();
		greenMail = new GreenMail(ServerSetupTest.ALL);
		greenMail.start();
	}

	@AfterEach
	private void stopGreenMail() {
		greenMail.stop();
	}

	@Test
	public void send() throws MessagingException {
		String from = "from@test.com";
		String to = "to1@test.com, to2@test.com";
		String cc = "cc1@test.com, cc2@test.com";
		String bcc = "bcc1@test.com, bcc2@test.com";

		textEmailSendingService.setFrom(from);
		textEmailSendingService.setTo(to);
		textEmailSendingService.setCc(cc);
		textEmailSendingService.setBcc(bcc);

		String subject = GreenMailUtil.random();
		String text = GreenMailUtil.random();

		Session session = greenMail.getSmtp().createSession();

		textEmailSendingService.send(session, subject, text);

		Assertions.assertTrue(greenMail.waitForIncomingEmail(5000, 6));

		Message[] messages = greenMail.getReceivedMessages();
		Assertions.assertEquals(6, messages.length);

		for (Message message : messages) {
			Assertions.assertEquals(subject, message.getSubject());
			Assertions.assertEquals(text, GreenMailUtil.getBody(message).trim());
		}

		// Create user
		greenMail.setUser("to1@test.com", "to1@test.com", "password");

		// Create session and store
		Session imapSession = greenMail.getPop3s().createSession();

		Store store = imapSession.getStore("pop3s");
		store.connect("to1@test.com", "password");

		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);

		Message messageReceived = inbox.getMessage(1);

		Assertions.assertEquals(messages[1].getSubject(), messageReceived.getSubject());
	}

	@Test
	void getSession() {
	}
}