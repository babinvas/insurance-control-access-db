package babinvas.insurancecontrolaccessdb.services.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.mail.*;
import java.util.Properties;

class TextEmailSendingServiceTest_ShouldGetSession {
	@Test
	public void getSession() {
		GreenMail greenMail = new GreenMail(ServerSetupTest.ALL);
		greenMail.start();
		TextEmailSendingService textEmailSendingService = new TextEmailSendingService();

		final String expectedUsername = "from@test.com";
		final String expectedPassword = "password";
		String expectedHost = "127.0.0.1";
		int expectedPort = 3025;

		Session session = textEmailSendingService.getSession(expectedUsername, expectedPassword, expectedHost, expectedPort);

		Properties properties = session.getProperties();

		String actualHost = properties.getProperty("mail.smtp.host");
		String actualSsl = properties.getProperty("mail.smtp.ssl.enable");
		String actualAuth = properties.getProperty("mail.smtp.auth");
		String actualDebug = properties.getProperty("mail.debug");

		Assertions.assertEquals(expectedHost, actualHost);
		Assertions.assertEquals("true", actualSsl);
		Assertions.assertEquals("true", actualAuth);
		Assertions.assertEquals("true", actualDebug);

		greenMail.stop();
	}
}