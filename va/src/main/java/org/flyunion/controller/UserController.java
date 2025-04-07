package org.flyunion.controller;

import org.flyunion.annotation.SkipAuthentication;
import org.flyunion.entity.User;
import org.flyunion.entity.request.PasswordResetRequest;
import org.flyunion.exception.IncorrectPasswordException;
import org.flyunion.exception.UserBannedException;
import org.flyunion.exception.UserExistException;
import org.flyunion.exception.UserNotFoundException;
import org.flyunion.service.UserService;
import org.flyunion.utils.JwtUtil;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;


	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login/cid")
	@SkipAuthentication
	public ResponseEntity<Result<String>> loginByCid(@RequestBody User user) throws UserNotFoundException,
			IncorrectPasswordException, UserBannedException {
		String token = userService.loginByUsername(user);
		return ResponseEntity.ok(new Result<>(200, "登录成功", token));
	}

	@PostMapping("/login/email")
	@SkipAuthentication
	public ResponseEntity<Result<String>> loginByEmail(@RequestBody User user) throws UserNotFoundException,
			IncorrectPasswordException, UserBannedException {
		String token = userService.loginByEmail(user);
		return ResponseEntity.ok(new Result<>(200, "登录成功", token));
	}

	@PostMapping("/register")
	@SkipAuthentication
	public ResponseEntity<Result<String>> register(@RequestBody User user) throws UserExistException {
		int register = userService.register(user);
		return register <= 0 ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Result<>(500, "注册出现未知错误", null)) :
				ResponseEntity.ok(new Result<>(200, "注册成功！", JwtUtil.generateTokenByCID(user.getCid())));

	}

	@PostMapping("/changePassword")
	@SkipAuthentication
	public ResponseEntity<Result<String>> changePassword(@RequestBody PasswordResetRequest request) {
		int i = userService.changePassword(request);
		return i > 0 ? ResponseEntity.ok(new Result<>
				(200, "更改密码完毕，即将登录", JwtUtil.generateTokenByEmail(request.getEmail()))) :
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new Result<>(500, "出现未知问题，请联系管理员", null));

	}

	@GetMapping("/loadLoginUser")
	public ResponseEntity<Result<User>> loadLoginUser(@RequestHeader("Authorization") String token) throws UserNotFoundException {
		User user = userService.loadUserByCid(JwtUtil.getCidFromToken(token));
		if (user == null) {
			throw new UserNotFoundException("用户不存在！", HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(new Result<>(200, "登录用户信息查询成功", user));
		}
	}

	@PutMapping("/ADM/banUser/{cid}")
	public ResponseEntity<Result<String>> banUser(@PathVariable String cid) throws UserNotFoundException {
		if (userService.banUser(cid) > 0) {
			return ResponseEntity.ok(new Result<>(200, "封禁完毕", cid));
		}
		throw new UserNotFoundException("用户" + cid + "不存在", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/topTen")
	public ResponseEntity<Result<List<User>>> getTopTenUserByLogs(){
		return ResponseEntity.ok(new Result<>(200, null, userService.getTopTenUserByLogs()));
	}
}

