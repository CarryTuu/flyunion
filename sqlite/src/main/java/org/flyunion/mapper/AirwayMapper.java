package org.flyunion.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.flyunion.entity.Airway;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AirwayMapper {

	@Select("select * from airway where airway_name = #{name}")
	List<Airway> getAirwayByName(String name);

}




