package org.flyunion.exception;

import org.springframework.http.HttpStatus;

/**
 * 飞机存在异常
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public class PlaneExistException extends BaseException {
	public PlaneExistException(String message, HttpStatus status) {
		super(message, status);
	}
}
