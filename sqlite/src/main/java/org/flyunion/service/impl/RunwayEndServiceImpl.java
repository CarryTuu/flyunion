package org.flyunion.service.impl;

import org.flyunion.entity.Airport;
import org.flyunion.entity.Runway;
import org.flyunion.entity.RunwayEnd;
import org.flyunion.mapper.AirportMapper;
import org.flyunion.mapper.RunwayEndMapper;
import org.flyunion.mapper.RunwayMapper;
import org.flyunion.service.RunwayEndService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RunwayEndServiceImpl implements RunwayEndService {

	private final RunwayEndMapper runwayEndMapper;
	private final AirportMapper airportMapper;
	private final RunwayMapper runwayMapper;

	public RunwayEndServiceImpl(RunwayEndMapper runwayEndMapper, AirportMapper airportMapper, RunwayMapper runwayMapper) {
		this.runwayEndMapper = runwayEndMapper;
		this.airportMapper = airportMapper;
		this.runwayMapper = runwayMapper;
	}

	@Override
	public RunwayEnd getRunwayEnd(Integer runwayEndId) {
		return runwayEndMapper.getRunwayEnd(runwayEndId);
	}

	@Override
	public Map<String, Object> getRunwayNameAndHeading(String ident){
		Airport airport = airportMapper.getAirportId(ident);
		List<Runway> runwayId = runwayMapper.getRunwayId(airport.getAirportId());
		Map<String, Object> map = new HashMap<>();
		map.put("primaryRunway", this.getRunwayEnd(runwayId.get(0).getPrimaryEndId()).getName());
		map.put("secondaryRunway", this.getRunwayEnd(runwayId.get(0).getSecondaryEndId()).getName());
		map.put("heading", this.getRunwayEnd(runwayId.get(0).getPrimaryEndId()).getHeading());
		return map;
	}
}
