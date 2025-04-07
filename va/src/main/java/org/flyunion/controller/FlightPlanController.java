package org.flyunion.controller;

import jakarta.annotation.Nullable;
import org.flyunion.entity.FlightPlan;
import org.flyunion.service.FlightPlanService;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 航班计划控制器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/flightPlan")
public class FlightPlanController {

	private final FlightPlanService flightPlanService;

	public FlightPlanController(FlightPlanService flightPlanService) {
		this.flightPlanService = flightPlanService;
	}

	@GetMapping("/")
	public ResponseEntity<Result<List<FlightPlan>>> getAllFlightPlan() {
		List<FlightPlan> allPlan = flightPlanService.getAllPlan();
		if (allPlan.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Result<>(404, "未找到任何可用航班", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下可用航班信息", allPlan));
	}

	@GetMapping("/{departure}/{arrival}")
	public ResponseEntity<Result<List<FlightPlan>>> getPlanByQuery(@PathVariable @Nullable String departure,
																   @PathVariable @Nullable String arrival) {
		List<FlightPlan> planByQuery = flightPlanService.getPlanByQuery(departure, arrival);
		if (planByQuery.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Result<>(404, "未找到任何可用航班", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下可用航班信息", planByQuery));
	}

	@PostMapping("/")
	public ResponseEntity<Result<?>> newPlan(@RequestBody FlightPlan flightPlan) {
		int i = flightPlanService.newFlightPlan(flightPlan);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "添加完毕", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Result<>(500, "系统出错，请联系管理员", null));
	}

	@PutMapping("/")
	public ResponseEntity<Result<?>> updatePlan(FlightPlan flightPlan) {
		int i = flightPlanService.updatePlan(flightPlan);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "修改完毕", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Result<>(500, "系统出错，请联系管理员", null));
	}
}
