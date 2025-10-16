package org.flyunion.service.service;

import lombok.extern.slf4j.Slf4j;
import org.flyunion.exception.CaptchaExistException;
import org.flyunion.exception.IncorrectCaptchaException;
import org.flyunion.utils.CaptchaGenerator;
import org.flyunion.utils.RedisUtil;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 邮件与验证码业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Slf4j
@Service
public class CaptchaService {
	private static final int CAPTCHA_LENGTH = 5;

	private final RedisUtil redisUtil;
	private final JavaMailSender mailSender;

	public CaptchaService(RedisUtil redisUtil, JavaMailSender mailSender) {
		this.redisUtil = redisUtil;
		this.mailSender = mailSender;
	}

	public void generateAndSendCaptcha(String email) {
		log.info("开始生成长度为{}的验证码", CAPTCHA_LENGTH);
		String captcha = CaptchaGenerator.generateCaptcha(CAPTCHA_LENGTH);
		log.info("生成的验证码为：{}", captcha);

		redisUtil.storeCaptcha(email, captcha);

		log.info("验证码存入完毕，准备发送邮件！");
		sendCaptchaEmail(email, captcha);
	}

	private void sendCaptchaEmail(String email, String captcha) {
		SimpleMailMessage message = new SimpleMailMessage();
		log.info("邮件将被发送至{}", email);
		message.setTo(email);
		message.setSubject("您的FlyUnion VA系统验证码");
		message.setText("我们已经收到了您的请求，以下是您的验证码。验证码在五分钟之内有效\n您的验证码为" + captcha);
		message.setFrom("hinakongqi303@gmail.com");
		mailSender.send(message);
		log.info("邮件已经由hinakongqi303@gmail.com发送至{}", email);
	}

	public boolean verifyCaptcha(String email, String inputCaptcha) throws IncorrectCaptchaException, CaptchaExistException {
		log.info("开始比对输入的验证码与存储的验证码是否一致");
		String captcha = (String) redisUtil.getCaptcha(email);
		if(inputCaptcha.equals(captcha)){
			redisUtil.deleteCaptcha(email);
			return true;
		}else{
			throw new IncorrectCaptchaException("验证码输入错误！请重新输入！", HttpStatus.BAD_REQUEST);
		}
	}
}
