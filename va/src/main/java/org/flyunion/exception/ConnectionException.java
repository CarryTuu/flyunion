package org.flyunion.exception;

import org.springframework.http.HttpStatus;

public class ConnectionException extends BaseException{
	public ConnectionException(String message, HttpStatus status) {
		super(message, status);
	}
}
