package org.flyunion.service.impl;

import org.flyunion.entity.Constants;
import org.flyunion.mapper.ConstantsMapper;
import org.flyunion.service.ConstantsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 常量业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Service
public class ConstantsServiceImpl implements ConstantsService {

	private final ConstantsMapper constantsMapper;

	public ConstantsServiceImpl(ConstantsMapper constantsMapper) {
		this.constantsMapper = constantsMapper;
	}

	@Override
	public int newConstants(Constants constants) {
		return constantsMapper.newConstants(constants);
	}

	@Override
	public Constants getConstantsByKey(String key) {
		return constantsMapper.getConstantsByKey(key);
	}

	@Override
	public int updateConstants(Constants constants) {
		return constantsMapper.updateConstants(constants);
	}

	@Override
	public List<Constants> getAllConstants() {
		return constantsMapper.getAllConstants();
	}
}
