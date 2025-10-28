package org.flyunion.controller;

import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuggerController {

	@RequestMapping("/")
	public ResponseEntity<Result<String>> why() {
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(new Result<>(418, "I am a teapot", "你爬虫你m呢？？？"));
	}

}
