package org.flyunion.service;

import org.flyunion.entity.PlannedFlight;

import java.util.List;

public interface PlannedFlightService {

	void newPlan(PlannedFlight plannedFlight);

	boolean changeStatus(String status, String id, String cid);

	boolean deletePlan(String id, String cid);

	List<PlannedFlight> getPlannedFlightByUser(String cid);

}
