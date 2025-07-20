package org.flyunion.controller;

import org.flyunion.annotation.SkipAuthentication;
import org.flyunion.entity.PlanList;
import org.flyunion.service.PlanListService;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planList")
public class PlanListController {

	private final PlanListService planListService;

	public PlanListController(PlanListService planListService) {
		this.planListService = planListService;
	}

	@GetMapping("/{pilot}")
	public ResponseEntity<Result<?>> getPlanListByPilot(@PathVariable String pilot){
		return ResponseEntity.ok(new Result<>(
				200, "找到如下数据", planListService.getListByPilot(pilot)));
	}

	@SkipAuthentication
	@PostMapping("/newPlan")
	public ResponseEntity<Result<?>> newPlan(@RequestBody PlanList planList){
		int i = planListService.newPlan(planList);
		return  i > 0 ? ResponseEntity.ok(new Result<>(200, "添加完成！", null)) :
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result<>
						(500, "出现未知错误，请联系管理员", null));
	}
}
