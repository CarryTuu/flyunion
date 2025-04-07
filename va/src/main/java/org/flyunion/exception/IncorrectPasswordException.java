package org.flyunion.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 密码错误异常
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Getter
public class IncorrectPasswordException extends BaseException {
	public IncorrectPasswordException(String message, HttpStatus status) {
		super(message, status);
	}
}
