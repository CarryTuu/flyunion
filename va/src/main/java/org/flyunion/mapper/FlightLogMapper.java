package org.flyunion.mapper;

import org.apache.ibatis.annotations.*;
import org.flyunion.entity.FlightLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 航班报告数据库仓库
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Mapper
@Repository
public interface FlightLogMapper {
	@Select("select * from flyunion.`flight_log`")
	List<FlightLog> getAllLog();

	@Select("select * from flyunion.`flight_log` where pilot = #{pilot}")
	List<FlightLog> getLogByPilot(int pilot);

	@Select("select * from flyunion.`flight_log` where id = #{id}")
	FlightLog getLogById(String id);

	@Insert("insert into flyunion.`flight_log` value " +
			"(#{id}, #{code}, #{plane}, #{pilot},#{departure}, #{arrival}," +
			" #{route}, curdate(), #{rate}, #{oil}, 'verify')")
	int newLog(FlightLog flightLog);

	@Select("select * from flyunion.flight_log where plane = #{pilot}")
	List<FlightLog> getLogByPlane(String plane);

	@Select("select * from flyunion.flight_log where code like  CONCAT(#{iata}, '%') and status = 'verify'")
	FlightLog getLog(String iata);

	@Select("select count(*) from flyunion.flight_log where code like  CONCAT(#{iata}, '%') and status = 'verify'")
	int getLogNumber(String iata);

	@Select("select * from flyunion.flight_log where code like  CONCAT(#{iata}, '%') and status = 'verify'")
	List<FlightLog> getVerifyLog(String iata);

	@Update("update flyunion.flight_log set status = 'accepted' where id = #{id}")
	int acceptLog(String id);

	@Update("update flyunion.flight_log set status = 'rejected' where id = #{id}")
	int rejectLog(String id);

	@Delete("delete from flyunion.flight_log where id = #{id}")
	int deleteLog(String id);
}
