package org.flyunion.utils;

import java.util.UUID;

/**
 * UUID生成器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public class UUIDGenerator {

	public static String getId() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
