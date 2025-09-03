package org.flyunion.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

	private static final String REGEX = "yyyy/MM/dd HH:mm:ss";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(REGEX);

	public static String formatTime(LocalDateTime time){
		return time.format(FORMATTER);
	}
}
