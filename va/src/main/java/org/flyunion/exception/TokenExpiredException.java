package org.flyunion.exception;

/**
 * Token过期
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public class TokenExpiredException extends Exception {
	public TokenExpiredException(String message) {
		super(message);
	}
}
