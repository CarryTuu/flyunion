package org.flyunion.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 用户不存在异常
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Getter
public class UserNotFoundException extends BaseException {
	public UserNotFoundException(String message, HttpStatus status) {
		super(message, status);
	}
}
