package org.flyunion.controller;

import org.flyunion.entity.MaintainingRecord;
import org.flyunion.service.MaintainingRecordService;
import org.flyunion.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintainingRecord")
public class MaintainingRecordController {

	private final MaintainingRecordService maintainingRecordService;

	public MaintainingRecordController(MaintainingRecordService maintainingRecordService) {
		this.maintainingRecordService = maintainingRecordService;
	}

	@GetMapping("/")
	public ResponseEntity<Result<List<MaintainingRecord>>> getAllRecord(){
		List<MaintainingRecord> allRecord = maintainingRecordService.getAllRecord();
		if(allRecord.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "未找到符合条件的数据", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下符合条件的数据", allRecord));
	}

	@PostMapping("/")
	public ResponseEntity<Result<?>> newRecord(@RequestBody MaintainingRecord maintainingRecord){
		int i = maintainingRecordService.newRecord(maintainingRecord);
		if(i > 0){
			return ResponseEntity.ok(new Result<>(200, "添加完成", null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result<>(500, "发生未知错误", null));
	}

	@GetMapping("/{plane}")
	public ResponseEntity<Result<List<MaintainingRecord>>> getRecordByPlane(@PathVariable String plane){
		List<MaintainingRecord> recordByPlane = maintainingRecordService.getRecordByPlane(plane);
		if(recordByPlane.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result<>(404, "未找到符合条件的数据", null));
		}
		return ResponseEntity.ok(new Result<>(200, "找到如下符合条件的数据", recordByPlane));
	}
}
