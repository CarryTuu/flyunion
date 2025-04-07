package org.flyunion.exception;


import org.springframework.http.HttpStatus;

/**
 * 用户已经存在异常
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */

public class UserExistException extends BaseException {
	public UserExistException(String message, HttpStatus status) {
		super(message, status);
	}
}
