package org.flyunion.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.flyunion.entity.Airport;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AirportMapper {

	@Select("select * from airport where ident = #{ident}")
	Airport getAirportId(String ident);

}
