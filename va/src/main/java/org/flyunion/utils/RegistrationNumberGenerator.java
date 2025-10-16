package org.flyunion.utils;

import java.util.Random;

/**
 * 飞机注册号随机器
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public class RegistrationNumberGenerator {
	private static final String CHARACTERS = "ABCDEFGHJKLMNPQRSTUVWXYZ0123456789";
	private static final int RANDOM_STRING_LENGTH = 4;
	private static final String PREFIX = "B-";

	public static String generateRegistrationNumber() {
		StringBuilder sb = new StringBuilder(PREFIX);
		Random random = new Random();

		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			// 从字符集中随机选择一个字符
			int index = random.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(index));
		}

		return sb.toString();
	}
}
