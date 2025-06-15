package org.flyunion.service;

import org.flyunion.entity.PlannedFlight;

public interface PlannedFlightService {

	int newPlan(PlannedFlight plannedFlight);

	int changeStatus(String status, String id);

	int deletePlan(String id);

}
