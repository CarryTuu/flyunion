package org.flyunion.controller;

import org.flyunion.annotation.SkipAuthentication;
import org.flyunion.service.UserService;
import org.flyunion.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 提供给FSD的接口
 * @author 1228
 * @version 0.2.6-HOTFIX
 * */
@RestController
@RequestMapping("/fsd")
public class FSDController {

	private final UserService userService;

	public FSDController(UserService userService) {
		this.userService = userService;
	}

	@SkipAuthentication
	@PostMapping("/login")
	public ResponseEntity<Result<?>> login(){
		return ResponseEntity.ok(new Result<>(200, "登录成功", null));
	}
}

