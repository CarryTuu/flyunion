package org.flyunion.service;

import org.flyunion.entity.PlanList;

import java.util.List;

public interface PlanListService {

	List<PlanList> getListByPilot(String pilot);

	int newPlan(PlanList planList);

}
