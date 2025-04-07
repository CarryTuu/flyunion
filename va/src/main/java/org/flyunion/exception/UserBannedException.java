package org.flyunion.exception;

import org.springframework.http.HttpStatus;

/**
 * 用户被封禁异常
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public class UserBannedException extends BaseException {
	public UserBannedException(String message, HttpStatus status) {
		super(message, status);
	}
}
