package org.flyunion.mapper;

import org.apache.ibatis.annotations.*;
import org.flyunion.entity.FlightPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 可执行航班数据库仓库
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@Mapper
@Repository
public interface FlightPlanMapper {

	@Select("select * from flyunion.`flight_plan`")
	List<FlightPlan> getAllPlan();

	@Select("select * from flyunion.`flight_plan` where departure = #{departure}")
	List<FlightPlan> getFlightPlanByDeparture(String departure);

	@Select("select * from flyunion.`flight_plan` where arrival = #{arrival}")
	List<FlightPlan> getFlightPlanByDestination(String arrival);

	@Select("select * from flyunion.`flight_plan` where departure = #{departure} " +
			"and arrival = #{arrival}")
	List<FlightPlan> getFlightPlanByAll(String departure, String arrival);

	@Insert("insert into flyunion.`flight_plan` value (#{planId}, #{flightCode}, " +
			"#{departure}, #{arrival}, #{route}, #{company})")
	int newFlightPlan(FlightPlan flightPlan);

	@Update("update flyunion.`flight_plan` set departure = #{departure}, arrival = #{arrival}, " +
			"route = #{route} where `plan_id` = #{planId}")
	int updatePlan(FlightPlan flightPlan);

	@Select("select * from flyunion.`flight_plan` where company = #{company}")
	List<FlightPlan> getPlanByCompany(String company);

	@Delete("delete from flyunion.flight_plan where plan_id = #{id}")
	int deletePlan(String id);

	@Select("select * from flyunion.flight_plan where plan_id = #{id}")
	FlightPlan getPlanById(String id);
}
