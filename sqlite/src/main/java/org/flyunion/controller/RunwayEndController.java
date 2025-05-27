package org.flyunion.controller;

import org.flyunion.service.RunwayEndService;

import org.flyunion.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/runwayEnd")
public class RunwayEndController {

	private final RunwayEndService runwayEndService;

	public RunwayEndController(RunwayEndService runwayEndService) {
		this.runwayEndService = runwayEndService;
	}

	@GetMapping("/getRunwayInfo/{ident}")
	public ResponseEntity<Result<Map<String, Object>>> getRunwayInfo(@PathVariable String ident){
		Map<String, Object> map = runwayEndService.getRunwayNameAndHeading(ident);
		return ResponseEntity.ok(new Result<>(200, "操作完毕", map));
	}
}
