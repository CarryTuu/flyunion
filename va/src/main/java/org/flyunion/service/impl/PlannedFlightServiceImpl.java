package org.flyunion.service.impl;

import org.flyunion.entity.PlannedFlight;
import org.flyunion.mapper.PlannedFlightMapper;
import org.flyunion.service.PlannedFlightService;
import org.flyunion.utils.DateFormatter;
import org.flyunion.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Service
public class PlannedFlightServiceImpl implements PlannedFlightService {

	private final PlannedFlightMapper plannedFlightMapper;
	private static final Map<String, String> statusMap = new HashMap<>();

	static{
		statusMap.put("preparing", "准备中");
		statusMap.put("taxing", "滑行中");
		statusMap.put("climbing", "爬升高度中");
		statusMap.put("cruising", "高空巡航中");
		statusMap.put("descending", "下降高度中");
		statusMap.put("approaching", "进近中");
		statusMap.put("landing", "即将着陆");
		statusMap.put("landed", "已经着陆");
		statusMap.put("parked", "在停机位中");
	}

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

	@Override
	public List<PlannedFlight> getPlannedFlightByUser(String cid) {
		List<PlannedFlight> list = plannedFlightMapper.getPlannedFlightByUser(cid);
		for(PlannedFlight flight : list){
			flight.setStatus(this.statusMapper(flight.getStatus()));
			flight.setFormattedTime(DateFormatter.formatTime(flight.getPlannedTime()));
		}
		return list;
	}

	private String statusMapper(String status){
		if(statusMap.containsKey(status)){
			return statusMap.get(status);
		}
		return null;
	}
}
