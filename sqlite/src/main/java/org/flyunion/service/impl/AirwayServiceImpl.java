package org.flyunion.service.impl;

import org.flyunion.entity.Airway;
import org.flyunion.mapper.AirwayMapper;
import org.flyunion.service.AirwayService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirwayServiceImpl implements AirwayService {

	private final AirwayMapper airwayMapper;

	public AirwayServiceImpl(AirwayMapper airwayMapper) {
		this.airwayMapper = airwayMapper;
	}

	@Override
	public List<Airway> getAirwayByName(String name) {
		return airwayMapper.getAirwayByName(name);
	}
}
