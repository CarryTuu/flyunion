package org.flyunion.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
	@Select("select * from flyunion.`flight-log`")
	List<FlightLog> getAllLog();

	@Select("select * from flyunion.`flight-log` where pilot = #{pilot}")
	List<FlightLog> getLogByPilot(int pilot);

	@Select("select * from flyunion.`flight-log` where id = #{id}")
	FlightLog getLogById(String id);

	@Insert("insert into flyunion.`flight-log` value " +
			"(#{id}, #{code}, #{plane}, #{pilot}, curdate(), #{rate}, #{oil})")
	int newLog(FlightLog flightLog);
}
