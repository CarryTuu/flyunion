package org.flyunion.service.impl;

import org.flyunion.entity.PlanList;
import org.flyunion.mapper.PlanListMapper;
import org.flyunion.service.PlanListService;
import org.flyunion.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlanListServiceImpl implements PlanListService {

	private final PlanListMapper planListMapper;

	public PlanListServiceImpl(PlanListMapper planListMapper) {
		this.planListMapper = planListMapper;
	}

	@Override
	public List<PlanList> getListByPilot(String pilot) {
		return planListMapper.getListByPilot(pilot);
	}

	@Override
	public int newPlan(PlanList planList) {
		planList.setPlanId(UUIDGenerator.getId());
		planList.setPlanTime(LocalDate.now());
		return planListMapper.newPlan(planList);
	}
}
