package org.flyunion.service.impl;

import org.flyunion.entity.MaintainingRecord;
import org.flyunion.mapper.MaintainingRecordMapper;
import org.flyunion.service.MaintainingRecordService;
import org.flyunion.utils.DateFormatter;
import org.flyunion.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 维护记录业务
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@Service
public class MaintainingRecordServiceImpl implements MaintainingRecordService {

	private final MaintainingRecordMapper maintainingRecordMapper;
	private static final Map<String, String> statusMap = new HashMap<>();

	public MaintainingRecordServiceImpl(MaintainingRecordMapper maintainingRecordMapper) {
		this.maintainingRecordMapper = maintainingRecordMapper;
	}

	static{
		statusMap.put("Heavy landing maintenance", "重着陆后检查维护");
		statusMap.put("Routine maintenance", "例行检查维护");
	}

	private String statusMapper(String status){
		if(statusMap.containsKey(status)){
			return statusMap.get(status);
		}
		return null;
	}

	@Override
	public int newRecord(MaintainingRecord maintainingRecord) {
		maintainingRecord.setId(UUIDGenerator.getId());
		return maintainingRecordMapper.newRecord(maintainingRecord);
	}

	@Override
	public List<MaintainingRecord> getRecordByPlane(String plane) {
		List<MaintainingRecord> recordByPlane = maintainingRecordMapper.getRecordByPlane(plane);
		for (MaintainingRecord maintainingRecord : recordByPlane) {
			maintainingRecord.setFormattedTime(DateFormatter.formatTime(maintainingRecord.getDate()));
			maintainingRecord.setType(statusMapper(maintainingRecord.getType()));
		}
		return recordByPlane;
	}

	@Override
	public List<MaintainingRecord> getAllRecord() {
		List<MaintainingRecord> allRecord = maintainingRecordMapper.getAllRecord();
		for (MaintainingRecord maintainingRecord : allRecord) {
			maintainingRecord.setFormattedTime(DateFormatter.formatTime(maintainingRecord.getDate()));
			maintainingRecord.setType(statusMapper(maintainingRecord.getType()));
		}
		return allRecord;
	}
}
