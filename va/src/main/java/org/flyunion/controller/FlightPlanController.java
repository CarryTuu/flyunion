package org.flyunion.controller;

import org.flyunion.annotation.BackendAuthorization;
import org.flyunion.entity.FlightPlan;
import org.flyunion.entity.request.PlanSearchRequest;
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

	@GetMapping("/airport")
	public ResponseEntity<Result<List<FlightPlan>>> getPlanByQuery(@RequestBody PlanSearchRequest searchForm) {
		List<FlightPlan> planByQuery = flightPlanService.getPlanByQuery(searchForm.getDeparture(), searchForm.getArrival());
		if (planByQuery.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Result<>(404, "未找到任何可用航班", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下可用航班信息", planByQuery));
	}

	@PostMapping("/")
	@BackendAuthorization(permission = 2)
	public ResponseEntity<Result<?>> newPlan(@RequestBody FlightPlan flightPlan) {
		int i = flightPlanService.newFlightPlan(flightPlan);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "添加完毕", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Result<>(500, "系统出错，请联系管理员", null));
	}

	@PutMapping("/")
	@BackendAuthorization(permission = 2)
	public ResponseEntity<Result<?>> updatePlan(@RequestBody FlightPlan flightPlan) {
		int i = flightPlanService.updatePlan(flightPlan);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "修改完毕", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Result<>(500, "系统出错，请联系管理员", null));
	}

	@GetMapping("/queryByCompany/{company}")
	public ResponseEntity<Result<List<FlightPlan>>> getPlanByCompany(@PathVariable String company){
		List<FlightPlan> planByCompany = flightPlanService.getPlanByCompany(company);
		if(planByCompany.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "未找到符合条件的数据", null));
		}else {
			return ResponseEntity.ok(new Result<>(200, "找到如下数据", planByCompany));
		}
	}

	@GetMapping("/planCount/{company}")
	public ResponseEntity<Result<?>> getPlanCountByCompany(@PathVariable String company){
		List<FlightPlan> planByCompany = flightPlanService.getPlanByCompany(company);
		if(planByCompany.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "未找到符合条件的数据", null));
		}else {
			return ResponseEntity.ok(new Result<>(200, "找到如下数据", planByCompany.size()));
		}
	}

	@DeleteMapping("/{id}")
	@BackendAuthorization(permission = 2)
	public ResponseEntity<Result<?>> deletePlan(@PathVariable String id){
		flightPlanService.deletePlan(id);
		return ResponseEntity.ok(new Result<>(200, "删除完毕", null));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Result<FlightPlan>> getPlanById(@PathVariable String id){
		FlightPlan planById = flightPlanService.getPlanById(id);
		if(planById != null){
			return ResponseEntity.ok(new Result<>(200, "找到如下数据", planById));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "未找到符合条件的数据", null));
	}
}
