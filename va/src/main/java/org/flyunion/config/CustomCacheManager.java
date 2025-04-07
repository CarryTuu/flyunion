package org.flyunion.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓存管理器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
public class CustomCacheManager implements CacheManager {
	private final Map<String, CacheWrapper> cacheMap = new ConcurrentHashMap<>();
	private final ReentrantLock lock = new ReentrantLock();

	@Override
	public Cache getCache(String name) {
		lock.lock();
		try {
			return cacheMap.computeIfAbsent(name, k -> new CacheWrapper());
		} finally {
			lock.unlock();
		}
	}

	@Override
	public Collection<String> getCacheNames() {
		return cacheMap.keySet();
	}

	private static class CacheWrapper implements Cache {
		private final Map<Object, CacheEntry> entries = new ConcurrentHashMap<>();

		@Override
		public String getName() {
			return "CaptchaCache";
		}

		@Override
		public Object getNativeCache() {
			return entries;
		}

		@Override
		public ValueWrapper get(Object key) {
			CacheEntry entry = entries.get(key);
			if (entry == null || entry.isExpired()) {
				return null;
			}
			return new SimpleValueWrapper(entry.value);
		}

		@Override
		public <T> T get(Object key, Class<T> type) {
			return null;
		}

		@Override
		public <T> T get(Object key, Callable<T> valueLoader) {
			return null;
		}

		@Override
		public void put(Object key, Object value) {
			entries.put(key, new CacheEntry(value, 5, TimeUnit.MINUTES));
		}

		@Override
		public void evict(Object key) {
			entries.remove(key);
		}

		@Override
		public void clear() {
			entries.clear();
		}

		private static class CacheEntry {
			private final Object value;
			private final long expiryTime;

			CacheEntry(Object value, long duration, TimeUnit unit) {
				this.value = value;
				this.expiryTime = System.currentTimeMillis() + unit.toMillis(duration);
			}

			boolean isExpired() {
				return System.currentTimeMillis() > expiryTime;
			}
		}
	}
}