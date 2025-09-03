package org.flyunion.service.impl;

import org.flyunion.entity.FlightLog;
import org.flyunion.mapper.FlightLogMapper;
import org.flyunion.service.FlightLogService;
import org.flyunion.utils.DateFormatter;
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

	private List<FlightLog> formatTime(List<FlightLog> list){
		for(FlightLog flightLog : list){
			flightLog.setFormattedTime(DateFormatter.formatTime(flightLog.getDate()));
		}
		return list;
	}

	@Override
	public List<FlightLog> getAllLog() {
		List<FlightLog> allLog = flightLogMapper.getAllLog();
		return formatTime(allLog);
	}

	@Override
	public List<FlightLog> getLogByPilot(int pilot) {
		List<FlightLog> logByPilot = flightLogMapper.getLogByPilot(pilot);
		return formatTime(logByPilot);
	}

	@Override
	public FlightLog getLogById(String id) {
		FlightLog logById = flightLogMapper.getLogById(id);
		logById.setFormattedTime(DateFormatter.formatTime(logById.getDate()));
		return logById;
	}

	@Override
	public int newLog(FlightLog flightLog) {
		return flightLogMapper.newLog(flightLog);
	}

	@Override
	public List<FlightLog> getLogByPlane(String plane) {
		List<FlightLog> logByPlane = flightLogMapper.getLogByPlane(plane);
		return formatTime(logByPlane);
	}
}
