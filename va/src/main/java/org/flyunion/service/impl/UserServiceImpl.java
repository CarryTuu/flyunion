package org.flyunion.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.flyunion.entity.Company;
import org.flyunion.entity.User;
import org.flyunion.entity.request.PasswordResetRequest;
import org.flyunion.exception.IncorrectPasswordException;
import org.flyunion.exception.UserBannedException;
import org.flyunion.exception.UserExistException;
import org.flyunion.exception.UserNotFoundException;
import org.flyunion.mapper.CompanyMapper;
import org.flyunion.mapper.UserMapper;
import org.flyunion.service.UserService;
import org.flyunion.utils.JwtUtil;
import org.flyunion.utils.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final CompanyMapper companyMapper;

	public UserServiceImpl(UserMapper userMapper, CompanyMapper companyMapper) {
		this.userMapper = userMapper;
		this.companyMapper = companyMapper;
	}

	@Override
	public String loginByUsername(User user) throws IncorrectPasswordException, UserNotFoundException, UserBannedException {
		log.info("====================登录功能开始====================");
		log.info("开始在系统中查询cid为{}的用户信息", user.getCid());
		User userFromDB = userMapper.loginByUsername(user.getCid());
		if (userFromDB != null) {
			log.info("cid为{}的用户存在，比较密码信息", user.getCid());
			if (PasswordEncoder.compare(user.getPassword(), userFromDB.getPassword())) {
				if ("banned".equals(userFromDB.getStatus())) {
					throw new UserBannedException("用户" + user.getCid() + "已经被封禁，如为误封请联系管理员！", HttpStatus.FORBIDDEN);
				}
				log.info("密码信息校对正确，登录用户身份已验证：{}，开始生成token", user.getCid());
				return JwtUtil.generateTokenByCID(userFromDB.getCid());
			}
			log.error("密码信息校对失败，登录用户身份验证失败！");
			throw new IncorrectPasswordException("密码输入不正确！", HttpStatus.UNAUTHORIZED);
		}
		log.error("CID验证失败，用户不存在！登录用户身份验证失败");
		throw new UserNotFoundException("用户" + user.getCid() + "不存在！", HttpStatus.NOT_FOUND);
	}

	@Override
	public int register(User user) throws UserExistException {
		log.info("====================注册功能开始====================");
		log.info("检验当前注册的CID是否存在：{}", user.getCid());
		if (userMapper.loadUserByCid(user.getCid()) == null) {
			log.info("当前注册的CID：{}不存在，开始注册逻辑！", user.getCid());
			log.info("开始加密明文密码");
			user.setPassword(PasswordEncoder.encode(user.getPassword()));
			log.info("明文密码加密完毕！");
			Company companyByID = companyMapper.getCompanyByID(user.getCompany());
			log.info("将航司基地：{}填入到注册对象中", companyByID.getBase());
			user.setAirport(companyByID.getBase());
			log.info("执行数据库插入语句，将用户信息添加进系统");
			int result = userMapper.register(user);
			if (result > 0) {
				log.info("注册成功！");
				return result;
			}
			log.error("注册出现错误，请查阅下方具体报错信息！");
			return 0;
		}
		log.error("当前CID：{}已存在于系统中！", user.getCid());
		throw new UserExistException("用户" + user.getCid() + "已经存在，若忘记密码，请进入帮助中心！", HttpStatus.CONFLICT);
	}

	@Override
	public String loginByEmail(User user) throws IncorrectPasswordException, UserNotFoundException, UserBannedException {
		log.info("====================登录功能开始====================");
		log.info("开始在系统中查询cid为{}的用户信息", user.getEmail());
		User userFromDB = userMapper.loginByEmail(user.getEmail());
		if (userFromDB != null) {
			log.info("email为{}的用户存在，比较密码信息", user.getEmail());
			if (PasswordEncoder.compare(user.getPassword(), userFromDB.getPassword())) {
				if ("banned".equals(userFromDB.getStatus())) {
					throw new UserBannedException("用户" + user.getEmail() + "已经被封禁，如为误封请联系管理员！", HttpStatus.FORBIDDEN);
				}
				log.info("密码信息校对正确，登录用户身份已验证：{}，开始生成token", user.getEmail());
				return JwtUtil.generateTokenByEmail(userFromDB.getEmail());
			}
			log.error("密码信息校对失败，登录用户身份验证失败！");
			throw new IncorrectPasswordException("密码输入不正确！", HttpStatus.UNAUTHORIZED);
		}
		log.error("email验证失败，用户不存在！登录用户身份验证失败");
		throw new UserNotFoundException("用户" + user.getEmail() + "不存在！", HttpStatus.NOT_FOUND);
	}

	@Override
	public User loadUserByCid(String cid) {
		return userMapper.loadUserByCid(cid);
	}

	@Override
	public int banUser(String cid) {
		return userMapper.banUser(cid);
	}

	@Override
	public int changePassword(PasswordResetRequest request) {
		log.info("接收到更改密码请求！");
		log.info("请求更改密码的用户为{}", request.getEmail());
		String encode = PasswordEncoder.encode(request.getPassword());
		request.setPassword(encode);
		return userMapper.changePassword(request);
	}

	@Override
	public List<User> getTopTenUserByLogs() {
		List<User> list = userMapper.getTopTenUserByLogs();
		for(User user : list){
			Company company = companyMapper.getCompanyByID(user.getCompany());
			user.setCompany(company.getName());
		}
		return list;
	}

	@Override
	public List<User> getUserByCompany(String company) {
		return userMapper.getUserByCompany(company);
	}
}
