package org.flyunion.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.flyunion.entity.RunwayEnd;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface RunwayEndMapper {

	@Select("select * from runway_end where runway_end_id = #{runwayEndId}")
	RunwayEnd getRunwayEnd(Integer runwayEndId);

}
