package org.flyunion.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于塞到ResponseEntity中的结果返回对象
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

	private int code;
	private String message;
	private T data;

}
