package org.flyunion.service.impl;

import org.flyunion.entity.FlightLog;
import org.flyunion.mapper.FlightLogMapper;
import org.flyunion.service.FlightLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 航班报告业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Service
public class FlightLogServiceImpl implements FlightLogService {

	private final FlightLogMapper flightLogMapper;

	public FlightLogServiceImpl(FlightLogMapper flightLogMapper) {
		this.flightLogMapper = flightLogMapper;
	}

	@Override
	public List<FlightLog> getAllLog() {
		return flightLogMapper.getAllLog();
	}

	@Override
	public List<FlightLog> getLogByPilot(int pilot) {
		return flightLogMapper.getLogByPilot(pilot);
	}

	@Override
	public FlightLog getLogById(String id) {
		return flightLogMapper.getLogById(id);
	}

	@Override
	public int newLog(FlightLog flightLog) {
		return flightLogMapper.newLog(flightLog);
	}
}
