package org.flyunion.service;

import org.flyunion.entity.PlannedFlight;

import java.util.List;

public interface PlannedFlightService {

	int newPlan(PlannedFlight plannedFlight);

	int changeStatus(String status, String id);

	int deletePlan(String id);

	List<PlannedFlight> getPlannedFlightByUser(String cid);

}
