package org.flyunion.controller;

import org.flyunion.annotation.BackendAuthorization;
import org.flyunion.entity.Plane;
import org.flyunion.exception.PlaneExistException;
import org.flyunion.service.PlaneService;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 航空器控制器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/plane")
public class PlaneController {

	private final PlaneService planeService;

	public PlaneController(PlaneService planeService) {
		this.planeService = planeService;
	}

	@GetMapping("/code/{code}")
	public ResponseEntity<Result<Plane>> getPlaneByCode(@PathVariable String code) {
		Plane plane = planeService.getPlaneByCode(code);
		return plane == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "未找到符合条件的航空器！", null)) :
				ResponseEntity.ok(new Result<>(200, "找到航空器注册号为" + code + "的航空器", plane));
	}

	@GetMapping("/{cid}")
	public ResponseEntity<Result<?>> getPlanesByCid(@PathVariable String cid) {
		List<Plane> planesByUser = planeService.getPlanesByUser(cid);
		if (planesByUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "未找到符合条件的航空器", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下所属人为" + cid + "的航空器", planesByUser));
	}

	@PostMapping("/newPlane")
	@BackendAuthorization(permission = 4)
	public ResponseEntity<Result<?>> newPlane(@RequestBody Plane plane) throws PlaneExistException {
		int i = planeService.newPlane(plane);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "添加成功", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result<>(500,
				"服务器出现致命错误导致此操作未完成，请联系管理员！", null));
	}

	@PutMapping("/crash/{code}")
	public ResponseEntity<Result<?>> planeDestroyed(@PathVariable String code) {
		int i = planeService.planeDestroyed(code);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "调整完毕", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result<>(500,
				"服务器出现致命错误导致此操作未完成，请联系管理员", null));
	}

	@PutMapping("/addTime/{code}")
	public ResponseEntity<Result<?>> addTime(@PathVariable String code, Integer time) {
		int i = planeService.addTime(time, code);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "调整完毕", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result<>(500,
				"服务器出现致命错误导致此操作未完成，请联系管理员", null));
	}

	@GetMapping("/getPlaneByCompany/{company}")
	public ResponseEntity<Result<?>> getPlanesByCompany(@PathVariable String company){
		return ResponseEntity.ok(new Result<>(200, "找到如下飞机",
				planeService.getPlaneByCompany(company)));

	}

	@GetMapping("/getPlaneCountByCompany/{company}")
	public ResponseEntity<Result<?>> getPlaneCountByCompany(@PathVariable String company){
		return ResponseEntity.ok(new Result<>(200, "找到以下数据", planeService.getPlaneCountByCompany(company)));
	}

	@GetMapping("/getAllPlane")
	public ResponseEntity<Result<List<Plane>>> getAllPlane(){
		return ResponseEntity.ok(new Result<>(200, "找到如下数据", planeService.getAllPlane()));
	}

	@PutMapping("/restorePlane/{code}")
	@BackendAuthorization(permission = 4)
	public ResponseEntity<Result<?>> restorePlaneStatus(@PathVariable String code){
		int i = planeService.restorePlaneStatus(code);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "调整完毕", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result<>(500,
				"服务器出现致命错误导致此操作未完成，请联系管理员", null));
	}

	@PutMapping("/publicPlane/{code}")
	@BackendAuthorization(permission = 4)
	public ResponseEntity<Result<?>> publicPlane(@PathVariable String code){
		int i = planeService.publicPlane(code);
		if (i > 0) {
			return ResponseEntity.ok(new Result<>(200, "调整完毕", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result<>(500,
				"服务器出现致命错误导致此操作未完成，请联系管理员", null));
	}
}
