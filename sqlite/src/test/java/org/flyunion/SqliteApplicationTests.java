package org.flyunion;

import org.flyunion.entity.Airport;
import org.flyunion.entity.Runway;
import org.flyunion.entity.RunwayEnd;
import org.flyunion.mapper.AirportMapper;
import org.flyunion.mapper.RunwayEndMapper;
import org.flyunion.mapper.RunwayMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SqliteApplicationTests {

	@Autowired
	AirportMapper airportMapper;
	@Autowired
	RunwayMapper runwayMapper;
	@Autowired
	RunwayEndMapper runwayEndMapper;
	@Test
	void contextLoads() {
		Airport zbad = airportMapper.getAirportId("ZSPD");
//		System.out.println("zbad = " + zbad);

		List<Runway> runwayId = runwayMapper.getRunwayId(zbad.getAirportId());
//		System.out.println("runwayId = " + runwayId);
		RunwayEnd runwayEnd = runwayEndMapper.getRunwayEnd(runwayId.get(0).getPrimaryEndId());
		RunwayEnd runwayEnd1 = runwayEndMapper.getRunwayEnd(runwayId.get(0).getSecondaryEndId());
		Double heading = runwayEnd.getHeading();

		Map<String, Object> map = new HashMap<>();
		map.put("name1", runwayEnd.getName());
		map.put("name2", runwayEnd1.getName());
		map.put("heading", heading);

		System.out.println("map = " + map);
	}

}
