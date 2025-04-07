package org.flyunion.config;

import lombok.extern.slf4j.Slf4j;
import org.flyunion.exception.BaseException;
import org.flyunion.exception.TokenExpiredException;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;


/**
 * 全局异常处理器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<Result<?>> handleTokenExpiredException(TokenExpiredException e) {
		log.error("出现异常: " + e);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Result<>(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null));
	}

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<Result<?>> handleBaseException(BaseException e) {
		log.error("出现异常", e);
		return ResponseEntity.status(e.getStatus()).body(new Result<>(e.getStatus().value(), e.getMessage(), null));
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Result<?>> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
		log.error("出现异常", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result<>(500, e.getMessage(), null));
	}

}
