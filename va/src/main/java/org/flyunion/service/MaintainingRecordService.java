package org.flyunion.service;

import org.flyunion.entity.MaintainingRecord;

import java.util.List;


/**
 * 抽象的维护记录业务
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
public interface MaintainingRecordService {

	int newRecord(MaintainingRecord maintainingRecord);

	List<MaintainingRecord> getRecordByPlane(String plane);

	List<MaintainingRecord> getAllRecord();

}
