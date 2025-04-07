package org.flyunion.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.flyunion.entity.FlightPlan;
import org.flyunion.mapper.FlightPlanMapper;
import org.flyunion.service.FlightPlanService;
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
		return flightPlanMapper.newFlightPlan(flightPlan);
	}

	@Override
	public int updatePlan(FlightPlan flightPlan) {
		return flightPlanMapper.updatePlan(flightPlan);
	}

	@Override
	public List<FlightPlan> getPlanByQuery(String departure, String arrival) {
		if (departure != null) {
			log.info("department不为空:{}", departure);
			if (arrival != null) {
				log.info("arrival不为空:{}", arrival);
				return this.getFlightPlanByAll(departure, arrival);
			} else {
				log.info("arrival为空");
				return this.getFlightPlanByDeparture(departure);
			}
		} else {
			log.info("department为空");
			return this.getFlightPlanByDestination(arrival);
		}
	}
}
