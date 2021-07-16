package babinvas.insurancecontrolaccessdb.services.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.mail.*;

import java.io.IOException;

class TextEmailSendingServiceTest_ShouldSendEmails {
	@Test
	public void send() throws IOException, MessagingException {
		GreenMail greenMail = new GreenMail(ServerSetupTest.ALL);
		greenMail.start();
		TextEmailSendingService textEmailSendingService = new TextEmailSendingService();

		String[] recipientAddresses = new String[] {"to1@test.com", "to2@test.com", "cc1@test.com", "cc2@test.com", "bcc1@test.com", "bcc2@test.com"};

		String from = "from@test.com";
		String to = recipientAddresses[0] + ", " + recipientAddresses[1];
		String cc = recipientAddresses[2] + ", " + recipientAddresses[3];
		String bcc = recipientAddresses[4] + ", " + recipientAddresses[5];

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

		// Create session
		Session imapSession = greenMail.getPop3s().createSession();

		for (String user : recipientAddresses) {
			// Create user
			greenMail.setUser(user, user, "password");

			// Create store
			Store store = imapSession.getStore("pop3s");
			store.connect(user, "password");

			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			Message messageReceived = inbox.getMessage(1);

			Assertions.assertEquals(messages[0].getSubject(), messageReceived.getSubject());
			Assertions.assertEquals(messages[0].getContent().toString().trim(), messageReceived.getContent().toString().trim());
			Assertions.assertEquals(messages[0].getFrom()[0].toString(), messageReceived.getFrom()[0].toString());
		}

		greenMail.stop();
	}
}