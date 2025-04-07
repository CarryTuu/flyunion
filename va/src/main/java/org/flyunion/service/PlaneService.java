package org.flyunion.service;

import org.flyunion.entity.Plane;
import org.flyunion.exception.PlaneExistException;

import java.util.List;

/**
 * 抽象的飞机业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public interface PlaneService {
	Plane getPlaneByCode(String code);

	List<Plane> getPlanesByUser(Integer cid);

	int newPlane(Plane plane) throws PlaneExistException;

	int addTime(Integer time, String code);

	int planeDestroyed(String code);
}
