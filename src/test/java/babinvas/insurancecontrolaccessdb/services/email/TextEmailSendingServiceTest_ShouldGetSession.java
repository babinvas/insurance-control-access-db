package babinvas.insurancecontrolaccessdb.services.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.mail.*;

import java.io.IOException;

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
}