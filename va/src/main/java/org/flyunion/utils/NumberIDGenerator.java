package org.flyunion.utils;

import java.util.Random;
/**
 * 纯数字ID生成器。15位
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
public class NumberIDGenerator {

	private static final String CHARACTERS = "1234567890";
	private static final int LENGTH = 15;

	public static String generateRandomString() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < LENGTH; i++) {
			// 从字符集中随机选择一个字符
			int index = random.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(index));
		}

		return sb.toString();
	}
}
