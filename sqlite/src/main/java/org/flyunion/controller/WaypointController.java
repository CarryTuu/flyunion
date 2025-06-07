package org.flyunion.controller;

import org.flyunion.service.WaypointService;
import org.flyunion.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/waypoint")
public class WaypointController {

	private final WaypointService waypointService;

	public WaypointController(WaypointService waypointService) {
		this.waypointService = waypointService;
	}

	@GetMapping("/{route}")
	public ResponseEntity<Result<?>> getCoordinates(@PathVariable String route){
		return ResponseEntity.ok(new Result<>(200, "操作完成", waypointService.getCoordinates(route)));
	}
}
