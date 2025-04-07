package org.flyunion.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Configuration
@EnableCaching
public class CacheConfig {
	@Bean
	public CustomCacheManager cacheManager() {
		return new CustomCacheManager();
	}
}
