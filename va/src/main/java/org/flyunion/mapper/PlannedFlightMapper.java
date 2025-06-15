package org.flyunion.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.flyunion.entity.PlannedFlight;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PlannedFlightMapper {

	@Insert("insert into flyunion.planned_flight(id, flight_code, pilot, planned_time, status)" +
			"value (#{id}, #{flightCode}, #{pilot}, CURDATE(), 'ready to flight')")
	int newPlan(PlannedFlight plannedFlight);

	@Update("update flyunion.planned_flight set status = #{status} where id = #{id}")
	int changeStatus(String status, String id);

	@Update("update flyunion.planned_flight set status = 'completed' where id = #{id}")
	int deletePlan(String id);
}
