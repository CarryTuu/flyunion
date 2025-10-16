package org.flyunion.controller;

import org.flyunion.annotation.BackendAuthorization;
import org.flyunion.entity.Fleet;
import org.flyunion.service.FleetService;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机队控制器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/fleet")
public class FleetController {

	private final FleetService fleetService;

	public FleetController(FleetService fleetService) {
		this.fleetService = fleetService;
	}

	@PostMapping("/")
	@BackendAuthorization(permission = 4)
	public ResponseEntity<Result<?>> newFleet(@RequestBody Fleet fleet) {
		int i = fleetService.newFleet(fleet);
		return i > 0 ? ResponseEntity.ok(new Result<>(200, "机队创建完毕！", null)) :
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new Result<>(500, "创建机队失败，请查看日志查阅问题", null));
	}

	@GetMapping("/")
	public ResponseEntity<Result<List<Fleet>>> getAllFleet() {
		List<Fleet> allFleet = fleetService.getAllFleet();
		if (allFleet.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Result<>(404, "系统中没有任何的机队", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下机队信息", allFleet));
	}

	@GetMapping("/{name}")
	public ResponseEntity<Result<Fleet>> getFleetByName(@PathVariable String name) {
		Fleet fleetByName = fleetService.getFleetByName(name);
		if (fleetByName == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Result<>(404, "系统中没有任何的机队", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下机队信息", fleetByName));
	}

	@PutMapping("/")
	@BackendAuthorization(permission = 4)
	public ResponseEntity<Result<String>> modifyFleet(@RequestBody Fleet fleet) {
		int i = fleetService.modifyFleet(fleet);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "编辑机队信息成功！", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Result<>(500, "出现未知错误，请查阅日志进行排查！", null));
	}

	@PutMapping("/{name}")
	@BackendAuthorization(permission = 4)
	public ResponseEntity<Result<?>> addPlane(@PathVariable String name) {
		int i = fleetService.addPlane(name);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "飞机添加成功！", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Result<>(500, "出现未知错误，请查阅日志进行排查！", null));
	}
}
