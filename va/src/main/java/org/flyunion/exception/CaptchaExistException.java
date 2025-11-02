package org.flyunion.exception;

import org.springframework.http.HttpStatus;


/**
 * 验证码已存在异常
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
public class CaptchaExistException extends BaseException {

	public CaptchaExistException(String message){
		super(message, HttpStatus.NOT_FOUND);
	}

}
