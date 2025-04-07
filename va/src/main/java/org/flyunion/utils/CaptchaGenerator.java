package org.flyunion.utils;

import java.security.SecureRandom;

/**
 * 验证码生成器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public class CaptchaGenerator {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();

	public static String generateCaptcha(int length) {
		StringBuilder captcha = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			captcha.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
		}
		return captcha.toString();
	}
}