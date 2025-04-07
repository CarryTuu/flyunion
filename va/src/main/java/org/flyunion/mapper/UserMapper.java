package org.flyunion.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flyunion.entity.User;
import org.flyunion.entity.request.PasswordResetRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据库仓库
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Mapper
@Repository
public interface UserMapper {

	@Select("select * from flyunion.user where cid = #{cid}")
	User loginByUsername(String cid);

	@Select("select * from flyunion.user where email = #{email}")
	User loginByEmail(String email);

	@Insert("insert into flyunion.user(cid, username, callsign, password, email, permission," +
			" status, balance, company, position, time, flight_count, qq, job, plane_count) " +
			"value (#{cid}, #{userName}, #{callsign}, #{password}, #{email}, 1, " +
			"'normal', 0, #{company}, #{position}, 0, 0, #{qq}, 0, 0)")
	int register(User user);

	@Select("select * from flyunion.user where cid = #{cid}")
	User loadUserByCid(String cid);

	@Update("update flyunion.user set status = 'banned' where cid = #{cid}")
	int banUser(String cid);

	@Update("update flyunion.user set password = #{password} where email = #{email}")
	int changePassword(PasswordResetRequest request);

	@Select("select * from flyunion.user order by flight_count desc limit 10")
	List<User> getTopTenUserByLogs();
}
