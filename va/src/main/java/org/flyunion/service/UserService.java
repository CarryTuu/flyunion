package org.flyunion.service;

import org.flyunion.entity.User;
import org.flyunion.entity.request.PasswordResetRequest;
import org.flyunion.exception.*;

import java.util.List;

/**
 * 抽象的用户业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public interface UserService {

	String loginByUsername(User user) throws IncorrectPasswordException, UserNotFoundException, UserBannedException;

	String register(User user) throws UserExistException;

	String loginByEmail(User user) throws IncorrectPasswordException, UserNotFoundException, UserBannedException;

	User loadUserByCid(String cid);

	int banUser(String cid);

	int changePassword(PasswordResetRequest request);

	List<User> getTopTenUserByLogs();

	List<User> getUserByCompany(String company);

	void logOut(String cid) throws TokenExpiredException;
}
