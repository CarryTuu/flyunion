package org.flyunion.service.impl;

import org.flyunion.entity.Runway;
import org.flyunion.mapper.RunwayMapper;
import org.flyunion.service.RunwayService;

import java.util.List;

public class RunwayServiceImpl implements RunwayService {

	private final RunwayMapper runwayMapper;

	public RunwayServiceImpl(RunwayMapper runwayMapper) {
		this.runwayMapper = runwayMapper;
	}

	@Override
	public List<Runway> getRunwayId(Integer id) {
		return runwayMapper.getRunwayId(id);
	}
}
