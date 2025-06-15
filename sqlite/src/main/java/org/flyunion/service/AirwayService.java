package org.flyunion.service;

import org.flyunion.entity.Airway;

import java.util.List;

public interface AirwayService {

	List<Airway> getAirwayByName(String name);

}
