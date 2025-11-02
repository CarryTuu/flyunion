package org.flyunion.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
/**
 * Redis配置类，用于对于不同的情况配置Redis连接工厂和RedisTemplate
 * @author 1228
 * @version 0.2.6-SNAPSHOT
 * */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

	private final RedisProperties redisProperties;

	public RedisConfig(RedisProperties redisProperties) {
		this.redisProperties = redisProperties;
	}

	// 创建配置化的ObjectMapper
	@Bean
	public ObjectMapper redisObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	}

	// 创建通用的序列化器
	@Bean
	public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer(ObjectMapper objectMapper) {
		ObjectMapper redisMapper = objectMapper.copy();
		redisMapper.activateDefaultTyping(
				redisMapper.getPolymorphicTypeValidator(),
				ObjectMapper.DefaultTyping.NON_FINAL
		);
		return new GenericJackson2JsonRedisSerializer(redisMapper);
	}

	// 为每个数据库创建独立的连接工厂
	@Bean
	@Primary
	public RedisConnectionFactory redisConnectionFactory() {
		return createInitializedConnectionFactory(1);
	}

	@Bean(name = "tokenAndCaptchaRedisConnectionFactory")
	public RedisConnectionFactory tokenAndCaptchaRedisConnectionFactory() {return createInitializedConnectionFactory(0);}

	@Bean(name = "planRedisConnectionFactory")
	public RedisConnectionFactory planRedisConnectionFactory() {
		return createInitializedConnectionFactory(1);
	}

	@Bean(name = "notificationRedisConnectionFactory")
	public RedisConnectionFactory notificationRedisConnectionFactory() {return createInitializedConnectionFactory(2);}

	@Bean(name = "ipBlackListRedisConnectionFactory")
	public RedisConnectionFactory ipBlackListRedisConnectionFactory(){
		return createInitializedConnectionFactory(3);
	}

	private RedisConnectionFactory createInitializedConnectionFactory(int database) {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName(redisProperties.getHost());
		config.setPort(redisProperties.getPort());
		config.setPassword(RedisPassword.of(redisProperties.getPassword()));
		config.setDatabase(database);

		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
				.commandTimeout(Duration.ofSeconds(30))
				.build();

		LettuceConnectionFactory factory = new LettuceConnectionFactory(config, clientConfig);
		factory.afterPropertiesSet(); // 关键：初始化连接工厂
		return factory;
	}

	// 为每个数据库创建独立的RedisTemplate
	@Bean
	@Primary
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
													   GenericJackson2JsonRedisSerializer serializer) {
		return buildRedisTemplate(redisConnectionFactory, serializer);
	}

	@Bean(name = "tokenAndCaptchaRedisTemplate")
	public RedisTemplate<String, Object> tokenAndCaptchaRedisTemplate(
			@Qualifier("tokenAndCaptchaRedisConnectionFactory") RedisConnectionFactory tokenAndCaptchaRedisConnectionFactory,
			GenericJackson2JsonRedisSerializer serializer) {
		return buildRedisTemplate(tokenAndCaptchaRedisConnectionFactory, serializer);
	}

	@Bean(name = "planRedisTemplate")
	public RedisTemplate<String, Object> planRedisTemplate(
			@Qualifier("planRedisConnectionFactory") RedisConnectionFactory planRedisConnectionFactory,
			GenericJackson2JsonRedisSerializer serializer) {
		return buildRedisTemplate(planRedisConnectionFactory, serializer);
	}

	@Bean(name = "notificationRedisTemplate")
	public RedisTemplate<String, Object> notificationRedisTemplate(
			@Qualifier("notificationRedisConnectionFactory") RedisConnectionFactory notificationRedisConnectionFactory,
			GenericJackson2JsonRedisSerializer serializer) {
		return buildRedisTemplate(notificationRedisConnectionFactory, serializer);
	}

	@Bean(name = "ipBlackListRedisTemplate")
	public RedisTemplate<String, Object> ipBlackListRedisTemplate(
			@Qualifier("ipBlackListRedisConnectionFactory") RedisConnectionFactory ipBlackListRedisConnectionFactory,
			GenericJackson2JsonRedisSerializer serializer){
		return buildRedisTemplate(ipBlackListRedisConnectionFactory, serializer);
	}

	private RedisTemplate<String, Object> buildRedisTemplate(RedisConnectionFactory connectionFactory,
															 GenericJackson2JsonRedisSerializer serializer) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);

		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(serializer);
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(serializer);

		template.setEnableTransactionSupport(false);
		template.afterPropertiesSet(); // 初始化RedisTemplate
		return template;
	}
}