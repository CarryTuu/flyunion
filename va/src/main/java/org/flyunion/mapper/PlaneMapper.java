package org.flyunion.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flyunion.entity.Plane;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 飞机数据库仓库
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Mapper
@Repository
public interface PlaneMapper {

	@Select("select * from plane where code = #{code}")
	Plane getPlaneByCode(String code);

	@Select("select * from flyunion.plane where owner = #{cid}")
	List<Plane> getPlanesByUser(Integer cid);

	@Insert("insert into flyunion.plane value (#{code}, #{owner}, #{fleet}, #{model},'available', 0, 100.00, #{company})")
	int newPlane(Plane plane);

	@Update("update flyunion.plane set time = time + #{time} where code = #{code}")
	int addTime(Integer time, String code);

	@Update("update flyunion.plane set status = 'crashed' where code = #{code}")
	int planeDestroyed(String code);

	@Select("select * from flyunion.plane where company = #{company}")
	List<Plane> getPlaneByCompany(String company);
}
