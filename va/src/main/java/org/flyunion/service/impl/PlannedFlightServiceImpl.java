package org.flyunion.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.flyunion.entity.PlannedFlight;
import org.flyunion.service.PlannedFlightService;
import org.flyunion.utils.DateFormatter;
import org.flyunion.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * 已计划航班业务实现类
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@Service
@Slf4j
public class PlannedFlightServiceImpl implements PlannedFlightService {

	@Qualifier("planRedisTemplate")
	private final RedisTemplate<String, Object> redisTemplate;
	private static final Map<String, String> STATUS_MAP = new HashMap<>();

	private static final String PILOT_PLANS_HASH = "pilot_plans_hash:";
	private static final String PILOT_PLANS_INDEX = "pilot_plans_index:";

	static{
		STATUS_MAP.put("preparing", "准备中");
		STATUS_MAP.put("taxing", "滑行中");
		STATUS_MAP.put("climbing", "爬升高度中");
		STATUS_MAP.put("cruising", "高空巡航中");
		STATUS_MAP.put("descending", "下降高度中");
		STATUS_MAP.put("approaching", "进近中");
		STATUS_MAP.put("landing", "即将着陆");
		STATUS_MAP.put("landed", "已经着陆");
		STATUS_MAP.put("parked", "在停机位中");
	}

	public PlannedFlightServiceImpl(@Qualifier("planRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void newPlan(PlannedFlight plannedFlight) {
		log.info("收到PlannedFlight对象！添加必须数据中");
		plannedFlight.setId(UUIDGenerator.getId());
		plannedFlight.setPlannedTime(LocalDateTime.now());
		plannedFlight.setFormattedTime(DateFormatter.formatTime(LocalDateTime.now()));
		plannedFlight.setStatus("preparing");
		log.info("所有必须数据添加完毕，将计划添加入Redis数据库");
		String hashKey = PILOT_PLANS_HASH + plannedFlight.getPilot();
		String indexKey = PILOT_PLANS_INDEX + plannedFlight.getPilot();

		// 1. 存储到Hash中（计划详情）
		log.info("将计划详情存入hash中");
		redisTemplate.opsForHash().put(hashKey, plannedFlight.getId(), plannedFlight);

		// 2. 添加到时间索引SortedSet中（使用计划时间的秒数作为分数）
		log.info("将计划时间存入时间索引中");
		double score = plannedFlight.getPlannedTime().atZone(ZoneId.systemDefault()).toEpochSecond();
		redisTemplate.opsForZSet().add(indexKey, plannedFlight.getId(), score);

	}

	@Override
	public boolean changeStatus(String status, String id, String cid) {
		String hashKey = PILOT_PLANS_HASH + cid;

		// 1. 获取现有的计划
		PlannedFlight existingPlan = (PlannedFlight) redisTemplate.opsForHash().get(hashKey, id);

		if (existingPlan == null) {
			return false; // 计划不存在
		}
		// 2. 更新status字段
		existingPlan.setStatus(status);

		// 3. 保存回Redis
		redisTemplate.opsForHash().put(hashKey, id, existingPlan);
		return true;
	}

	@Override
	public boolean deletePlan(String id, String cid) {
		String hashKey = PILOT_PLANS_HASH + cid;
		String indexKey = PILOT_PLANS_INDEX + cid;

		// 从Hash中删除
		Long hashResult = redisTemplate.opsForHash().delete(hashKey, id);

		// 从SortedSet中删除
		Long zsetResult = redisTemplate.opsForZSet().remove(indexKey, id);

		return (hashResult != null && hashResult > 0) || (zsetResult != null && zsetResult > 0);
	}

	@Override
	public List<PlannedFlight> getPlannedFlightByUser(String cid) {
		String hashKey = PILOT_PLANS_HASH + cid;
		String indexKey = PILOT_PLANS_INDEX + cid;

		// 从SortedSet获取所有计划ID（按时间排序）
		Set<Object> planIds = redisTemplate.opsForZSet().range(indexKey, 0, -1);

		if (planIds == null || planIds.isEmpty()) {
			return new ArrayList<>();
		}

		// 批量从Hash获取计划详情
		List<Object> planDetails = redisTemplate.opsForHash().multiGet(hashKey, planIds);



		return planDetails.stream()
				.filter(Objects::nonNull)
				.map(obj -> (PlannedFlight) obj)
				.map(this::translateStatus)
				.toList();
	}

	private PlannedFlight translateStatus(PlannedFlight plan) {
		if (plan != null && plan.getStatus() != null) {
			String translatedStatus = STATUS_MAP.getOrDefault(plan.getStatus(), plan.getStatus());
			plan.setStatus(translatedStatus);
		}
		return plan;
	}
}
