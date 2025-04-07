package org.flyunion.service;

import org.flyunion.entity.Fleet;

import java.util.List;

/**
 * 抽象的机队业务方法
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public interface FleetService {

	int newFleet(Fleet fleet);

	int modifyFleet(Fleet fleet);

	int addPlane(String name);

	List<Fleet> getAllFleet();

	Fleet getFleetByName(String name);
}
