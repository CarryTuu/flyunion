package org.flyunion.controller;

import org.flyunion.annotation.BackendAuthorization;
import org.flyunion.entity.Constants;
import org.flyunion.service.ConstantsService;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 常量控制器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/constants")
public class ConstantsController {

	private final ConstantsService constantsService;

	public ConstantsController(ConstantsService constantsService) {
		this.constantsService = constantsService;
	}

	@PostMapping("/")
	@BackendAuthorization(permission = 4)
	public ResponseEntity<Result<?>> newConstants(@RequestBody Constants constants) {
		int i = constantsService.newConstants(constants);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "添加完毕", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Result<>(500, "出现未知错误", null));
	}

	@PutMapping("/")
	@BackendAuthorization(permission = 4)
	public ResponseEntity<Result<?>> updateConstants(@RequestBody Constants constants) {
		int i = constantsService.updateConstants(constants);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "更改完毕", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Result<>(500, "出现未知错误", null));
	}

	@GetMapping("/")
	@BackendAuthorization(permission = 4)
	public ResponseEntity<Result<List<Constants>>> getAllConstants() {
		List<Constants> allConstants = constantsService.getAllConstants();
		if (allConstants.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "系统中不存在任何常量", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下常量", allConstants));
	}

	@GetMapping("/{name}")
	@BackendAuthorization(permission = 4)
	public ResponseEntity<Result<Constants>> getConstantsByKey(@PathVariable String name) {
		Constants constantsByKey = constantsService.getConstantsByKey(name);
		if (constantsByKey != null) {
			return ResponseEntity.ok(new Result<>(200, "找到如下常量", constantsByKey));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new Result<>(404, "未找到对应的常量", null));
	}
}
