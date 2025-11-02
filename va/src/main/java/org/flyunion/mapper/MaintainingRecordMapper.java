package org.flyunion.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.flyunion.entity.MaintainingRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 维护记录数据库仓库
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@Mapper
@Repository
public interface MaintainingRecordMapper {

	@Select("select * from flyunion.maintaining_record")
	List<MaintainingRecord> getAllRecord();

	@Select("select * from flyunion.maintaining_record where code = #{plane}")
	List<MaintainingRecord> getRecordByPlane(String plane);

	@Insert("insert into flyunion.maintaining_record value (#{id}, #{code}, #{type}, CURDATE())")
	int newRecord(MaintainingRecord maintainingRecord);
}
