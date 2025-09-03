package org.flyunion.utils;

import lombok.extern.slf4j.Slf4j;
import org.flyunion.exception.CaptchaExistException;
import org.flyunion.exception.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {


	private final RedisTemplate<String, String> redisTemplate;
	@Value("${redis.verify.tokenKey}")
	private String tokenKey;
	@Value("${redis.verify.captchaKey}")
	private String captchaKey;

	public RedisUtil(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	//存储token，并且设置十五分钟后删除
	public void storeToken(String cid, String token){
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		ops.set(tokenKey + cid, token, 24, TimeUnit.HOURS);
	}

	//检查token存在性
	public void isTokenExists(String cid) throws TokenExpiredException {
		String key = tokenKey + cid;
		if (!Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
			throw new TokenExpiredException("Token已经过期，请重新登录");
		}
	}

	//获取Token
	public String getToken(String cid) throws TokenExpiredException {
		isTokenExists(cid);
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		return ops.get(tokenKey + cid);
	}

	//删除Token
	public void deleteToken(String cid) throws TokenExpiredException {
		isTokenExists(cid);
		redisTemplate.delete(tokenKey + cid);
	}

	//以下为Captcha验证码方法
	public void storeCaptcha(String email, String code){
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		ops.set(captchaKey + email, code, 5, TimeUnit.MINUTES);
		log.info("验证码存储完毕，唯一标识符为邮箱");
	}


	public String getCaptcha(String email) throws CaptchaExistException {
		String key = captchaKey + email;
		if (!Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
			throw new CaptchaExistException("验证码不存在！");
		}
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		return ops.get(key);
	}

	public void deleteCaptcha(String email){
		redisTemplate.delete(captchaKey + email);
	}
}
