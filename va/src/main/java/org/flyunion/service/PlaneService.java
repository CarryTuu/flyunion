package org.flyunion.service;

import org.flyunion.entity.Plane;
import org.flyunion.exception.PlaneExistException;

import java.util.List;
import java.util.Map;

/**
 * 抽象的飞机业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public interface PlaneService {
	Plane getPlaneByCode(String code);

	List<Plane> getPlanesByUser(String cid);

	int newPlane(Plane plane) throws PlaneExistException;

	int addTime(Integer time, String code);

	int planeDestroyed(String code);

	Map<String, List<Plane>> getPlaneByCompany(String company);

	List<Plane> getAllPlane();

	int restorePlaneStatus(String code);

	int publicPlane(String code);

	int getPlaneCountByCompany(String company);
}
