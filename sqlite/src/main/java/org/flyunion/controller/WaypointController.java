package org.flyunion.controller;

import org.flyunion.entity.request.RouteRequest;
import org.flyunion.service.WaypointService;
import org.flyunion.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/waypoint")
public class WaypointController {

	private final WaypointService waypointService;

	public WaypointController(WaypointService waypointService) {
		this.waypointService = waypointService;
	}

	@PostMapping("/getRoute")
	public ResponseEntity<Result<?>> getCoordinates(@RequestBody RouteRequest route){
		return ResponseEntity.ok(new Result<>(200, "操作完成", waypointService.getCoordinates(route)));
	}
}
