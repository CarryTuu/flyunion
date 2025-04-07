package org.flyunion.service;

import org.flyunion.entity.User;
import org.flyunion.entity.request.PasswordResetRequest;
import org.flyunion.exception.IncorrectPasswordException;
import org.flyunion.exception.UserBannedException;
import org.flyunion.exception.UserExistException;
import org.flyunion.exception.UserNotFoundException;

import java.util.List;

/**
 * 抽象的用户业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public interface UserService {

	String loginByUsername(User user) throws IncorrectPasswordException, UserNotFoundException, UserBannedException;

	int register(User user) throws UserExistException;

	String loginByEmail(User user) throws IncorrectPasswordException, UserNotFoundException, UserBannedException;

	User loadUserByCid(String cid);

	int banUser(String cid);

	int changePassword(PasswordResetRequest request);

	List<User> getTopTenUserByLogs();
}
