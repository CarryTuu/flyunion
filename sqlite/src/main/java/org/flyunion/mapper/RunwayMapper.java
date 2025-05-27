package org.flyunion.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.flyunion.entity.Runway;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface RunwayMapper {

	@Select("select * from runway where airport_id = #{ident}")
	List<Runway> getRunwayId(Integer id);

}
