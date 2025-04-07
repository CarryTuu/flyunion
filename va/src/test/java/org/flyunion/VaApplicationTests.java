package org.flyunion;

import org.flyunion.service.service.CaptchaService;
import org.flyunion.utils.NumberIDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VaApplicationTests {

	@Autowired
	CaptchaService captchaService;

	@Test
	void contextLoad() {
		System.out.println("NumberIDGenerator.generateRandomString() = " + NumberIDGenerator.generateRandomString());
	}
}
