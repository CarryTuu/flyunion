package org.flyunion.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 权限不足异常
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Getter
public class InsufficientLevelException extends BaseException {


	public InsufficientLevelException(String message, HttpStatus status) {
		super(message, status);
	}

}
