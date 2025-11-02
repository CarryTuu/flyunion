package org.flyunion.controller;

import org.flyunion.entity.weather.MetarData;
import org.flyunion.service.service.MetarService;
import org.flyunion.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
/**
 * METAR数据控制器
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@RestController
@RequestMapping("/metar")
public class MetarController {

	private final MetarService metarService;

	public MetarController(MetarService metarService) {
		this.metarService = metarService;
	}

	@GetMapping("/{icao}")
	public ResponseEntity<Result<MetarData>> fetchData(@PathVariable String icao) throws IOException, InterruptedException {
		MetarData metarData = metarService.getMetarData(icao);
		return ResponseEntity.ok(new Result<>(200, "获取到如下METAR", metarData));
	}
}
