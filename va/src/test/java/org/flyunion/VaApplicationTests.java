package org.flyunion;

import org.flyunion.mapper.FlightLogMapper;
import org.flyunion.service.service.CaptchaService;
import org.flyunion.service.service.MetarService;
import org.flyunion.utils.JwtUtil;
import org.flyunion.utils.UUIDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VaApplicationTests {

	@Autowired
	CaptchaService captchaService;

	@Autowired
	FlightLogMapper mapper;

	@Autowired
	MetarService metarService;

	@Test
	void contextLoad() throws IOException, InterruptedException {

		System.out.println("UUIDGenerator.getId() = " + UUIDGenerator.getId());

	}

	@Test
	void uuID() throws IOException, InterruptedException {
		System.out.println("JwtUtil.generateTokenByCID(\"100013\") = " + JwtUtil.generateTokenByCID("100013"));
	}

}
