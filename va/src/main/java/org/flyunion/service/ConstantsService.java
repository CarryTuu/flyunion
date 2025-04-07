package org.flyunion.service;

import org.flyunion.entity.Constants;

import java.util.List;

/**
 * 抽象的常量业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public interface ConstantsService {

	int newConstants(Constants constants);

	Constants getConstantsByKey(String key);

	int updateConstants(Constants constants);

	List<Constants> getAllConstants();

}
