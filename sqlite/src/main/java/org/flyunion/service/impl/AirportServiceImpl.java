package org.flyunion.service.impl;

import org.flyunion.entity.Airport;
import org.flyunion.mapper.AirportMapper;
import org.flyunion.service.AirportService;

public class AirportServiceImpl implements AirportService {

	private final AirportMapper airportMapper;

	public AirportServiceImpl(AirportMapper airportMapper) {
		this.airportMapper = airportMapper;
	}

	@Override
	public Airport getAirportId(String ident) {
		return airportMapper.getAirportId(ident);
	}
}
