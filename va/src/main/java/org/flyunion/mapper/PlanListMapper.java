package org.flyunion.mapper;
;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.flyunion.entity.PlanList;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface PlanListMapper {

	@Select("select * from flyunion.plan_list where pilot = #{pilot}")
	List<PlanList> getListByPilot(String pilot);

	@Insert("insert into flyunion.plan_list (plan_id, flight_code, departure, arrival, pilot, plan_time) " +
			"value(#{planId}, #{flightCode}, #{departure}, #{arrival}, #{pilot}, #{planTime})")
	int newPlan(PlanList planList);

}