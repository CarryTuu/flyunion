package org.flyunion.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends Exception {
	private final HttpStatus status;

	public BaseException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
