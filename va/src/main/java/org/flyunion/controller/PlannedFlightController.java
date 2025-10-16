package org.flyunion.controller;

import org.flyunion.annotation.SkipAuthentication;
import org.flyunion.entity.PlannedFlight;
import org.flyunion.service.PlannedFlightService;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plannedFlight")
public class PlannedFlightController {

	private final PlannedFlightService plannedFlightService;

	public PlannedFlightController(PlannedFlightService plannedFlightService) {
		this.plannedFlightService = plannedFlightService;
	}

	@SkipAuthentication
	@PostMapping("/")
	public ResponseEntity<Result<?>> newPlan(@RequestBody PlannedFlight plannedFlight){
		plannedFlightService.newPlan(plannedFlight);
		return ResponseEntity.ok(new Result<>(200, "添加完毕", null));
	}

	@SkipAuthentication
	@PutMapping("/{status}/{id}/{cid}")
	public ResponseEntity<Result<?>> changeStatus(@PathVariable String status, @PathVariable String id, @PathVariable String cid){
		plannedFlightService.changeStatus(status, id, cid);
		return ResponseEntity.ok(new Result<>(200, "更改完毕", null));
	}

	@SkipAuthentication
	@DeleteMapping("/{id}/{cid}")
	public ResponseEntity<Result<?>> deletePlan(@PathVariable String id, @PathVariable String cid){
		plannedFlightService.deletePlan(id, cid);
		return ResponseEntity.ok(new Result<>(200, "状态已更改为已完成", null));
	}

	@SkipAuthentication
	@GetMapping("/{cid}")
	public ResponseEntity<Result<List<PlannedFlight>>> getPlannedFlightByUser(@PathVariable String cid){
		List<PlannedFlight> plannedFlightByUser = plannedFlightService.getPlannedFlightByUser(cid);
		return !plannedFlightByUser.isEmpty() ? ResponseEntity.ok(new Result<>(200, "找到如下数据", plannedFlightByUser)) :
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "未找到符合条件的数据", null));
	}
}
