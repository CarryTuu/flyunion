package org.flyunion.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期格式化工具类
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
public class DateFormatter {

	private static final String REGEX = "yyyy/MM/dd HH:mm:ss";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(REGEX);

	public static String formatTime(LocalDateTime time){
		return time.format(FORMATTER);
	}
}
