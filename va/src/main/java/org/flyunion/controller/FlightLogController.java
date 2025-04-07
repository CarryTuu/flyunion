package org.flyunion.controller;

import org.flyunion.entity.FlightLog;
import org.flyunion.service.FlightLogService;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 航班报告控制器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/flightLog")
public class FlightLogController {

	private final FlightLogService flightLogService;

	public FlightLogController(FlightLogService flightLogService) {
		this.flightLogService = flightLogService;
	}

	@GetMapping("/")
	public ResponseEntity<Result<List<FlightLog>>> getAllLogs() {
		List<FlightLog> allLog = flightLogService.getAllLog();
		if (allLog.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Result<>(404, "未找到任何航班报告信息！", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下航班报告信息", allLog));
	}

	@GetMapping("/{pilot}")
	public ResponseEntity<Result<List<FlightLog>>> getLogByPilot(@PathVariable int pilot) {
		List<FlightLog> logByPilot = flightLogService.getLogByPilot(pilot);
		if (logByPilot.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Result<>(404, "未找到任何航班报告信息！", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下航班报告信息", logByPilot));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Result<FlightLog>> getLogById(@PathVariable String id) {
		FlightLog log = flightLogService.getLogById(id);
		if (log == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Result<>(404, "未找到任何航班报告信息！", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下航班报告信息", log));
	}

	@PostMapping("/fly")
	public ResponseEntity<Result<?>> fly(@RequestBody FlightLog flightLog) {
		int i = flightLogService.newLog(flightLog);
		if (i > 0) {
			return ResponseEntity.ok(
					new Result<>(200, "航班报告提交完成，请通知管理员审批", flightLog.getId()));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Result<>(500, "出现错误，请重新提交或联系系统管理员", flightLog.getId()));
	}
}
