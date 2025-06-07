package org.flyunion.service.impl;

import org.flyunion.entity.Waypoint;
import org.flyunion.mapper.WaypointMapper;
import org.flyunion.service.WaypointService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@Service
public class WaypointServiceImpl implements WaypointService {

	private final WaypointMapper waypointMapper;

	public WaypointServiceImpl(WaypointMapper waypointMapper) {
		this.waypointMapper = waypointMapper;
	}

	@Override
	public List<List<Double>> getCoordinates(String route) {
		String[] parts = route.split(" ");
		List<List<Double>> list = new ArrayList<>();
		for(int i = 0; i <= parts.length; i++){
			if(i%2 == 0){
				Waypoint waypointByName = waypointMapper.getWaypointByName(parts[i]);
				List<Double> coordinates = new ArrayList<>();
				coordinates.add(waypointByName.getLonx());
				coordinates.add(waypointByName.getLaty());
				list.add(coordinates);
			}
		}
		return list;
	}
}
