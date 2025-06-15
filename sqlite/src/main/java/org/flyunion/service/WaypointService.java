package org.flyunion.service;

import org.flyunion.entity.request.RouteRequest;

import java.util.List;
import java.util.Map;

public interface WaypointService {

	List<List<Double>> getCoordinates(RouteRequest request);
	
}
