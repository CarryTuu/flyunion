package org.flyunion.service;

import org.flyunion.entity.FlightPlan;

import java.util.List;

/**
 * 抽象的航空器业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public interface FlightPlanService {

	List<FlightPlan> getAllPlan();

	List<FlightPlan> getFlightPlanByDeparture(String departure);

	List<FlightPlan> getFlightPlanByDestination(String arrival);

	List<FlightPlan> getFlightPlanByAll(String departure, String arrival);

	int newFlightPlan(FlightPlan flightPlan);

	int updatePlan(FlightPlan flightPlan);

	List<FlightPlan> getPlanByQuery(String departure, String arrival);

	List<FlightPlan> getPlanByCompany(String company);

	int deletePlan(String id);

	FlightPlan getPlanById(String id);

}
