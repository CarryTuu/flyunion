package org.flyunion.service;

import java.util.List;
import java.util.Map;

public interface WaypointService {

	List<List<Double>> getCoordinates(String route);
	
}
