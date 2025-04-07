package org.flyunion.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flyunion.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 航司数据库仓库
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Mapper
@Repository
public interface CompanyMapper {

	@Select("select * from flyunion.company")
	List<Company> getAllCompany();

	@Select("select * from flyunion.company where name = #{name}")
	Company getCompanyByName(String name);

	@Select("select * from flyunion.company where id = #{id}")
	Company getCompanyByID(String id);

	@Insert("insert into flyunion.company (id, name, pilot_count, plane_count, balance, base)" +
			"value (#{id}, #{name}, 0, 0, '0', #{base})")
	int newCompany(Company company);

	@Update("update flyunion.company set pilot_count = pilot_count + 1 where name = #{name}")
	int newPilot(String name);

	@Update("update flyunion.company set plane_count = plane_count + 1 where name = #{name}")
	int addPlane(String name);

}
