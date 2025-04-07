package org.flyunion.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flyunion.entity.Constants;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 常量数据库仓库
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Mapper
@Repository
public interface ConstantsMapper {

	@Update("insert into flyunion.constants value (#{key}, #{value}, #{description})")
	int newConstants(Constants constants);

	@Select("select * from flyunion.constants where `key` = #{key}")
	Constants getConstantsByKey(String key);

	@Update("update flyunion.constants set `value` = #{value} , `description` = #{description} where `key` = #{key}")
	int updateConstants(Constants constants);

	@Select("select * from flyunion.constants")
	List<Constants> getAllConstants();

}
