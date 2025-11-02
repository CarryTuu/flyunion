package org.flyunion.controller;

import org.flyunion.annotation.SkipAuthentication;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 应对爬虫的控制器，嘴臭了点但是没关系，反正爬虫爬不到
 * @author 1228
 * @version 0.2.6-HOTFIX
 * */
@RestController
public class BuggerController {

	@SkipAuthentication
	@RequestMapping("/")
	public ResponseEntity<Result<String>> why() {
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(new Result<>(418, "I am a teapot", "你爬虫你m呢？？？"));
	}

}
