package org.flyunion.service.impl;

import org.flyunion.entity.Airport;
import org.flyunion.entity.Airway;
import org.flyunion.entity.Waypoint;
import org.flyunion.entity.request.RouteRequest;
import org.flyunion.mapper.WaypointMapper;
import org.flyunion.service.AirportService;
import org.flyunion.service.AirwayService;
import org.flyunion.service.WaypointService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WaypointServiceImpl implements WaypointService {

	private final WaypointMapper waypointMapper;
	private final AirwayService airwayService;
	private final AirportService airportService;

	public WaypointServiceImpl(WaypointMapper waypointMapper, AirwayService airwayService, AirportService airportService) {
		this.waypointMapper = waypointMapper;
		this.airwayService = airwayService;
		this.airportService = airportService;
	}

	@Override
	public List<List<Double>> getCoordinates(RouteRequest request) {
		String[] parts = request.getRoute().split(" ");
		List<List<Double>> list = new ArrayList<>();
		list.add(getAirportCoordinate(request.getDep()));
		//拆分航路航点
		for(int i = 0; i <= parts.length; i++){
			//判断是航路还是航点
			if(i%2 == 0){
				Waypoint waypoint = waypointMapper.getWaypointByName(parts[i]);
				List<Double> coordinates = new ArrayList<>();
				coordinates.add(waypoint.getLonx());
				coordinates.add(waypoint.getLaty());
				list.add(coordinates);
			}
		}
		list.add(getAirportCoordinate(request.getArr()));
		return list;
	}
	private List<Double> getAirportCoordinate(String airport){
		Airport airportId = airportService.getAirportId(airport);
		List<Double> list = new ArrayList<>();
		list.add(airportId.getLonx());
		list.add(airportId.getLaty());
		return list;
	}
}
