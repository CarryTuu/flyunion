package org.flyunion.service.service;

import lombok.extern.slf4j.Slf4j;
import org.flyunion.exception.IncorrectCaptchaException;
import org.flyunion.utils.CaptchaGenerator;
import org.flyunion.utils.UUIDGenerator;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
	private static final String CAPTCHA_CACHE_NAME = "captchaCache";

	private final CacheManager cacheManager;

	private final JavaMailSender mailSender;

	public CaptchaService(CacheManager cacheManager, JavaMailSender mailSender) {
		this.cacheManager = cacheManager;
		this.mailSender = mailSender;
	}

	public String generateAndSendCaptcha(String email) {
		log.info("开始生成长度为{}的验证码", CAPTCHA_LENGTH);
		String captcha = CaptchaGenerator.generateCaptcha(CAPTCHA_LENGTH);
		log.info("生成的验证码为：{}， 开始生成验证码对应的key", captcha);
		String captchaKey = UUIDGenerator.getId();
		log.info("生成验证码对应的key：{}，开始将验证码key与验证码在内存中进行暂存", captchaKey);

		Cache cache = cacheManager.getCache(CAPTCHA_CACHE_NAME);
		if (cache != null) {
			cache.put(captchaKey, captcha);
			log.info("存入验证码组合：key: {}，captcha: {}", captchaKey, captcha);
		}
		log.info("验证码存入完毕，准备发送邮件！");
		sendCaptchaEmail(email, captcha);
		return captchaKey;
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

	public boolean verifyCaptcha(String captchaKey, String inputCaptcha) throws IncorrectCaptchaException {
		log.info("开始比对输入的验证码与存储的验证码是否一致，验证码key: {}", captchaKey);
		Cache cache = cacheManager.getCache(CAPTCHA_CACHE_NAME);
		Cache.ValueWrapper valueWrapper;
		String storedCaptcha;
		if (cache != null) {
			valueWrapper = Objects.requireNonNull(cache.get(captchaKey));
			storedCaptcha = (String) valueWrapper.get();
			log.info("获得缓存中存储的验证码：{}，开始比较", storedCaptcha);
		} else {
			log.error("缓存中不存在名为{}的数据", CAPTCHA_CACHE_NAME);
			return false;
		}

		if (storedCaptcha != null && storedCaptcha.equals(inputCaptcha)) {
			log.info("比较完毕，验证码正确！从缓存中移除该验证码！");
			cache.evict(captchaKey);
			return true;
		} else {
			log.error("验证码填写不正确！");
			throw new IncorrectCaptchaException("验证码不正确！", HttpStatus.BAD_REQUEST);
		}
	}
}
