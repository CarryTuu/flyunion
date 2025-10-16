package org.flyunion.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.flyunion.entity.Plane;
import org.flyunion.entity.User;
import org.flyunion.exception.PlaneExistException;
import org.flyunion.mapper.PlaneMapper;
import org.flyunion.mapper.UserMapper;
import org.flyunion.service.PlaneService;
import org.flyunion.utils.RegistrationNumberGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 飞机业务
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */

@Slf4j
@Service
public class PlaneServiceImpl implements PlaneService {

	private final PlaneMapper planeMapper;
	private final UserMapper userMapper;

	public PlaneServiceImpl(PlaneMapper planeMapper, UserMapper userMapper) {
		this.planeMapper = planeMapper;
		this.userMapper = userMapper;
	}

	@Override
	public Plane getPlaneByCode(String code) {
		return planeMapper.getPlaneByCode(code);
	}

	@Override
	public List<Plane> getPlanesByUser(String cid) {
		return planeMapper.getPlanesByUser(cid);
	}

	@Override
	public int newPlane(Plane plane) throws PlaneExistException {
		log.info("准备添加飞机！");
		if(planeMapper.getPlanesByUser(plane.getOwner()).isEmpty()){
			log.info("此飞机为用户名下第一架飞机，注册号将使用四位呼号");
			User user = userMapper.loadUserByCid(plane.getOwner());
			plane.setCode("B-" + user.getCallsign());
		}else{
			plane.setCode(RegistrationNumberGenerator.generateRegistrationNumber());
		}

		log.info("飞机注册号：{}", plane.getCode());
		log.info("飞机所有者：{}", plane.getOwner());
		log.info("飞机型号：{}", plane.getModel());
		log.info("飞机机队：{}", plane.getFleet());
		if (this.getPlaneByCode(plane.getCode()) != null) {
			log.error("注册号为{}的飞机已经存在，请重新设定注册号", plane.getCode());
			throw new PlaneExistException("注册号为" + plane.getCode() + "的飞机已经存在，请重新设定注册号", HttpStatus.CONFLICT);
		}
		return planeMapper.newPlane(plane);
	}

	@Override
	public int addTime(Integer time, String code) {
		return planeMapper.addTime(time, code);
	}

	@Override
	public int planeDestroyed(String code) {
		return planeMapper.planeDestroyed(code);
	}

	@Override
	public Map<String, List<Plane>> getPlaneByCompany(String company) {
		List<Plane> planeList = planeMapper.getPlaneByCompany(company);
		Map<String, List<Plane>> planeMap = new HashMap<>();
		for (Plane plane : planeList) {
			if (!planeMap.containsKey(plane.getFleet())) {
				planeMap.put(plane.getFleet(), new ArrayList<>());
				planeMap.get(plane.getFleet()).add(plane);
			} else {
				planeMap.get(plane.getFleet()).add(plane);
			}
		}
		return planeMap;
	}

	@Override
	public List<Plane> getAllPlane() {
		return planeMapper.getAllPlane();
	}

	@Override
	public int restorePlaneStatus(String code) {
		return planeMapper.restorePlaneStatus(code);
	}

	@Override
	public int publicPlane(String code) {
		return planeMapper.publicPlane(code);
	}

	@Override
	public int getPlaneCountByCompany(String company) {
		return planeMapper.getPlaneByCompany(company).size();
	}
}
