package org.flyunion.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flyunion.entity.FlightPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FlightPlanMapper {

	@Select("select * from flyunion.`flight-plan`")
	List<FlightPlan> getAllPlan();

	@Select("select * from flyunion.`flight-plan` where departure = #{departure}")
	List<FlightPlan> getFlightPlanByDeparture(String departure);

	@Select("select * from flyunion.`flight-plan` where arrival = #{arrival}")
	List<FlightPlan> getFlightPlanByDestination(String arrival);

	@Select("select * from flyunion.`flight-plan` where departure = #{departure}" +
			" and arrival = #{arrival}")
	List<FlightPlan> getFlightPlanByAll(String departure, String arrival);

	@Insert("insert into flyunion.`flight-plan` value (#{planId}, #{departure}, #{arrival}, #{route})")
	int newFlightPlan(FlightPlan flightPlan);

	@Update("update flyunion.`flight-plan` set departure = #{departure}, arrival = #{arrival}, " +
			"route = #{route} where `plan-id` = #{planId}")
	int updatePlan(FlightPlan flightPlan);
}
