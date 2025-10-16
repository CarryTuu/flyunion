package org.flyunion.service.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flyunion.entity.BlacklistRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RedisIPBlacklistService {

	private static final String BLACKLIST_KEY_PREFIX = "ip_blacklist:";
	private static final String BLACKLIST_SET_KEY = "global:blacklisted_ips";

	@Qualifier("ipBlackListRedisTemplate")
	private final RedisTemplate<String, Object> redisTemplate;

	public RedisIPBlacklistService(@Qualifier("ipBlackListRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 添加IP到黑名单（带过期时间）
	 */
	public void addToBlacklist(String ip, Duration duration, String reason) {
		String key = BLACKLIST_KEY_PREFIX + ip;
		BlacklistRecord record = new BlacklistRecord(ip, reason, Instant.now(), duration);

		// 存储详细信息
		redisTemplate.opsForValue().set(key, record, duration);

		// 同时存储到集合中便于批量查询
		redisTemplate.opsForSet().add(BLACKLIST_SET_KEY, ip);

		// 设置集合过期时间（比最长黑名单时间稍长）
		redisTemplate.expire(BLACKLIST_SET_KEY, duration.plus(Duration.ofDays(1)));
	}

	/**
	 * 永久黑名单
	 */
	public void addToBlacklistPermanently(String ip, String reason) {
		String key = BLACKLIST_KEY_PREFIX + ip;
		BlacklistRecord record = new BlacklistRecord(ip, reason, Instant.now(), null);

		redisTemplate.opsForValue().set(key, record);
		redisTemplate.opsForSet().add(BLACKLIST_SET_KEY, ip);
	}

	/**
	 * 检查IP是否在黑名单中
	 */
	public boolean isBlacklisted(String ip) {
		String key = BLACKLIST_KEY_PREFIX + ip;
		return Boolean.TRUE.equals(redisTemplate.hasKey(key));
	}

	/**
	 * 获取黑名单详情
	 */
	public BlacklistRecord getBlacklistRecord(String ip) {
		String key = BLACKLIST_KEY_PREFIX + ip;
		return (BlacklistRecord) redisTemplate.opsForValue().get(key);
	}

	/**
	 * 从黑名单移除
	 */
	public boolean removeFromBlacklist(String ip) {
		String key = BLACKLIST_KEY_PREFIX + ip;
		redisTemplate.delete(key);
		redisTemplate.opsForSet().remove(BLACKLIST_SET_KEY, ip);
		return true;
	}

	/**
	 * 获取所有黑名单IP（高效方式）
	 */
	public Set<String> getAllBlacklistedIPs() {
		Set<Object> members = redisTemplate.opsForSet().members(BLACKLIST_SET_KEY);
		if (members == null) {
			return Collections.emptySet();
		}
		return members.stream()
				.map(Object::toString)
				.collect(Collectors.toSet());
	}

	/**
	 * 清理过期的黑名单记录
	 */
	public void cleanupExpiredRecords() {
		Set<String> allIPs = getAllBlacklistedIPs();
		for (String ip : allIPs) {
			if (!isBlacklisted(ip)) {
				// 自动从集合中移除过期的记录
				redisTemplate.opsForSet().remove(BLACKLIST_SET_KEY, ip);
			}
		}
	}
}
