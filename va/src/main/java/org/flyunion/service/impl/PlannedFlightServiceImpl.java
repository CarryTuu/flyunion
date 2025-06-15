package org.flyunion.service.impl;

import org.flyunion.entity.PlannedFlight;
import org.flyunion.mapper.PlannedFlightMapper;
import org.flyunion.service.PlannedFlightService;
import org.flyunion.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

@Service
public class PlannedFlightServiceImpl implements PlannedFlightService {

	private final PlannedFlightMapper plannedFlightMapper;

	public PlannedFlightServiceImpl(PlannedFlightMapper plannedFlightMapper) {
		this.plannedFlightMapper = plannedFlightMapper;
	}

	@Override
	public int newPlan(PlannedFlight plannedFlight) {
		String id = UUIDGenerator.getId();
		plannedFlight.setId(id);
		return plannedFlightMapper.newPlan(plannedFlight);
	}

	@Override
	public int changeStatus(String status, String id) {
		return plannedFlightMapper.changeStatus(status, id);
	}

	@Override
	public int deletePlan(String id) {
		return plannedFlightMapper.deletePlan(id);
	}
}
