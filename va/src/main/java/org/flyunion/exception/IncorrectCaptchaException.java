package org.flyunion.exception;

import org.springframework.http.HttpStatus;

/**
 * 验证码错误异常
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public class IncorrectCaptchaException extends BaseException {
	public IncorrectCaptchaException(String message, HttpStatus status) {
		super(message, status);
	}
}
