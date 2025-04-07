package org.flyunion.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flyunion.entity.Fleet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 机队数据库仓库
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Mapper
@Repository
public interface FleetMapper {
	@Insert("insert into flyunion.fleet value(#{name}, #{model}, #{economy}, #{business}, #{cargo}, 0)")
	int newFleet(Fleet fleet);

	@Update("update flyunion.fleet set economy = #{economy} , business = #{business} , cargo = #{cargo} where name = #{name}")
	int modifyFleet(Fleet fleet);

	@Update("update flyunion.fleet set number = number + 1 where name = #{name}")
	int addPlane(String name);

	@Select("select * from flyunion.fleet")
	List<Fleet> getAllFleet();

	@Select("select * from flyunion.fleet where name = #{name}")
	Fleet getFleetByName(String name);
}
