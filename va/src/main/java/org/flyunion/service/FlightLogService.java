package org.flyunion.service;

import org.flyunion.entity.FlightLog;

import java.util.List;

/**
 * 抽象的航班报告业务方法
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public interface FlightLogService {
	List<FlightLog> getAllLog();

	List<FlightLog> getLogByPilot(int pilot);

	FlightLog getLogById(String id);

	int newLog(FlightLog flightLog);

	List<FlightLog> getLogByPlane(String plane);

	int getLogNumber(String iata);

	List<FlightLog> getVerifyLog(String iata);

	int acceptLog(String id);

	int rejectLog(String id);

	int deleteLog(String id);
}
