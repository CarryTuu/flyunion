package org.flyunion.service;

import org.flyunion.entity.PlannedFlight;

import java.util.List;


/**
 * 抽象的计划航班业务
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
public interface PlannedFlightService {

	void newPlan(PlannedFlight plannedFlight);

	boolean changeStatus(String status, String id, String cid);

	boolean deletePlan(String id, String cid);

	List<PlannedFlight> getPlannedFlightByUser(String cid);

}
