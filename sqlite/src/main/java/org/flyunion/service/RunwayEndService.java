package org.flyunion.service;

import org.flyunion.entity.RunwayEnd;

import java.util.Map;

public interface RunwayEndService {

	RunwayEnd getRunwayEnd(Integer runwayEndId);

	Map<String, Object> getRunwayNameAndHeading(String ident);

}
