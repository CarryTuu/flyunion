package org.flyunion.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.flyunion.entity.Waypoint;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WaypointMapper {

	@Select("select * from waypoint where ident = #{name} and region like 'Z%'")
	Waypoint getWaypointByName(String name);

}




