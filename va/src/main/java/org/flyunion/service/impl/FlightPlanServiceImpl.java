package org.flyunion.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.flyunion.entity.FlightPlan;
import org.flyunion.mapper.FlightPlanMapper;
import org.flyunion.service.FlightPlanService;
import org.flyunion.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 航班计划业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Slf4j
@Service
public class FlightPlanServiceImpl implements FlightPlanService {

	private final FlightPlanMapper flightPlanMapper;

	public FlightPlanServiceImpl(FlightPlanMapper flightPlanMapper) {
		this.flightPlanMapper = flightPlanMapper;
	}

	@Override
	public List<FlightPlan> getAllPlan() {
		return flightPlanMapper.getAllPlan();
	}

	@Override
	public List<FlightPlan> getFlightPlanByDeparture(String departure) {
		return flightPlanMapper.getFlightPlanByDeparture(departure);
	}

	@Override
	public List<FlightPlan> getFlightPlanByDestination(String arrival) {
		return flightPlanMapper.getFlightPlanByDestination(arrival);
	}

	@Override
	public List<FlightPlan> getFlightPlanByAll(String departure, String arrival) {
		return flightPlanMapper.getFlightPlanByAll(departure, arrival);
	}

	@Override
	public int newFlightPlan(FlightPlan flightPlan) {
		flightPlan.setPlanId(UUIDGenerator.getId());
		return flightPlanMapper.newFlightPlan(flightPlan);
	}

	@Override
	public int updatePlan(FlightPlan flightPlan) {
		return flightPlanMapper.updatePlan(flightPlan);
	}

	@Override
	public List<FlightPlan> getPlanByQuery(String departure, String arrival) {
		if (departure != null && !departure.isEmpty()) {
			log.info("departure:{}", departure);
			if (arrival != null && !arrival.isEmpty()) {
				log.info("arrival:{}", arrival);
				return this.getFlightPlanByAll(departure, arrival);
			} else {
				log.info("arrival为空");
				return this.getFlightPlanByDeparture(departure);
			}
		} else {
			log.info("departure为空");
			return this.getFlightPlanByDestination(arrival);
		}
	}

	@Override
	public int deletePlan(String id) {
		return flightPlanMapper.deletePlan(id);
	}

	@Override
	public FlightPlan getPlanById(String id) {
		return flightPlanMapper.getPlanById(id);
	}


	@Override
	public List<FlightPlan> getPlanByCompany(String company) {
		return flightPlanMapper.getPlanByCompany(company);
	}
}
