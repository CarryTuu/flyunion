package org.flyunion.exception;

import org.springframework.http.HttpStatus;

public class CaptchaExistException extends BaseException {

	public CaptchaExistException(String message){
		super(message, HttpStatus.NOT_FOUND);
	}

}
