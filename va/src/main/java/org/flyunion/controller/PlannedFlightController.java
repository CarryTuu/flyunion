package org.flyunion.controller;

import org.flyunion.entity.PlannedFlight;
import org.flyunion.service.PlannedFlightService;
import org.flyunion.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plannedFlight")
public class PlannedFlightController {

	private final PlannedFlightService plannedFlightService;

	public PlannedFlightController(PlannedFlightService plannedFlightService) {
		this.plannedFlightService = plannedFlightService;
	}

	@PostMapping("/")
	public ResponseEntity<Result<?>> newPlan(@RequestBody PlannedFlight plannedFlight){
		return ResponseEntity.ok(new Result<>(200, "添加完毕",
				plannedFlightService.newPlan(plannedFlight)));
	}

	@PutMapping("/{status}/{id}")
	public ResponseEntity<Result<?>> changeStatus(@PathVariable String status, @PathVariable String id){
		return ResponseEntity.ok(new Result<>(200, "更改完毕",
				plannedFlightService.changeStatus(status, id)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Result<?>> deletePlan(@PathVariable String id){
		return ResponseEntity.ok(new Result<>(200, "状态已更改为已完成",
				plannedFlightService.deletePlan(id)));
	}
}
