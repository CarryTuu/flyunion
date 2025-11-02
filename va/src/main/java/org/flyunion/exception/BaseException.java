package org.flyunion.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 基本异常类
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@Getter
public class BaseException extends Exception {
	private final HttpStatus status;

	public BaseException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
