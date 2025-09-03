package org.flyunion.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flyunion.entity.PlannedFlight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlannedFlightMapper {

	@Insert("insert into flyunion.planned_flight(id, flight_code, pilot, planned_time, status)" +
			"value (#{id}, #{flightCode}, #{departure}, #{arrival}, #{pilot}, CURDATE(), 'preparing')")
	int newPlan(PlannedFlight plannedFlight);

	@Update("update flyunion.planned_flight set status = #{status} where id = #{id}")
	int changeStatus(String status, String id);

	@Update("update flyunion.planned_flight set status = 'completed' where id = #{id}")
	int deletePlan(String id);

	@Select("select * from flyunion.planned_flight where pilot = #{cid}")
	List<PlannedFlight> getPlannedFlightByUser(String cid);
}
