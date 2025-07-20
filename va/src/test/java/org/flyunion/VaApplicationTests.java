package org.flyunion;

import org.flyunion.service.service.CaptchaService;
import org.flyunion.service.service.MetarService;
import org.flyunion.utils.UUIDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class VaApplicationTests {

	@Autowired
	CaptchaService captchaService;

	@Test
	void contextLoad() throws IOException, InterruptedException {

		MetarService metarService = new MetarService();
		System.err.println(metarService.getMetarData("ZGGG"));

	}

	@Test
	void uuID(){
		System.out.println(UUIDGenerator.getId());
	}
}
