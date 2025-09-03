package org.flyunion.service;

import org.flyunion.entity.MaintainingRecord;

import java.util.List;

public interface MaintainingRecordService {

	int newRecord(MaintainingRecord maintainingRecord);

	List<MaintainingRecord> getRecordByPlane(String plane);

	List<MaintainingRecord> getAllRecord();

}
