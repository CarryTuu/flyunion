package org.flyunion.service.impl;

import org.flyunion.entity.Fleet;
import org.flyunion.mapper.FleetMapper;
import org.flyunion.service.FleetService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机队业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Service
public class FleetServiceImpl implements FleetService {

	private final FleetMapper fleetMapper;

	public FleetServiceImpl(FleetMapper fleetMapper) {
		this.fleetMapper = fleetMapper;
	}

	@Override
	public int newFleet(Fleet fleet) {
		return fleetMapper.newFleet(fleet);
	}

	@Override
	public int modifyFleet(Fleet fleet) {
		return fleetMapper.modifyFleet(fleet);
	}

	@Override
	public int addPlane(String name) {
		return fleetMapper.addPlane(name);
	}

	@Override
	public List<Fleet> getAllFleet() {
		return fleetMapper.getAllFleet();
	}

	@Override
	public Fleet getFleetByName(String name) {
		return fleetMapper.getFleetByName(name);
	}
}
