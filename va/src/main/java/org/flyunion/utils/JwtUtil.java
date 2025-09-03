package org.flyunion.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.flyunion.exception.TokenExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * JWT工具
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */

public class JwtUtil {

	private static final String SECRET_KEY = "flyunion100013";// 用于签名的密钥
	private static final int EXPIRE_TIME = 24 * 60 * 60 * 1000;
	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
	private static final Set<String> tokenBlacklist = Collections.synchronizedSet(new HashSet<>());

	// 生成JWT
	public static String generateTokenByCID(String cid) {
		Date now = new Date();
		Date expireDate = new Date(now.getTime() + EXPIRE_TIME);
		log.info("开始生成token，登录用户: {}", cid);
		Map<String, Object> claims = new HashMap<>();
		claims.put("cid", cid);

		String token = Jwts.builder()
				.setClaims(claims)
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
		log.info("token生成完毕: {}，有效时间为十五分钟", token);
		return token;
	}

	// 生成JWT
	public static String generateTokenByEmail(String email) {
		Date now = new Date();
		Date expireDate = new Date(now.getTime() + EXPIRE_TIME);
		log.info("开始生成token，登录用户: {}", email);
		Map<String, Object> claims = new HashMap<>();
		claims.put("email", email);

		String token = Jwts.builder()
				.setClaims(claims)
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
		log.info("token生成完毕: {}，有效时间为十五分钟", token);
		return token;
	}

	// 从JWT中解析cid
	public static String getCidFromToken(String token) {
		log.info("开始在token中解析CID");
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		String cid = (String) claims.get("cid");
		log.info("cid提取成功：{}", cid);
		return cid;
	}

	// 验证JWT是否有效
	public static boolean validateToken(String token) {
		try {
			if (tokenBlacklist.contains(token)) {
				log.warn("Token已被加入黑名单: {}", token);
				return false;
			}
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isTokenExpired(String token) throws TokenExpiredException {
		try {
			// 解析JWT
			Jwts.parser()
					.setSigningKey(SECRET_KEY)
					.parseClaimsJws(token)
					.getBody();

			return true;

		} catch (io.jsonwebtoken.ExpiredJwtException e) {
			throw new TokenExpiredException("Token已经过期！请重新登录");
		}
	}
	/**将token加入黑名单*/
	public static void addBlackList(String token){
		tokenBlacklist.add(token);
	}

	public static void cleanExpiredBlacklistedTokens() {
		synchronized (tokenBlacklist) {
			Iterator<String> iterator = tokenBlacklist.iterator();
			while (iterator.hasNext()) {
				String token = iterator.next();
				try {
					// 如果Token已过期，从黑名单中移除
					Claims claims = Jwts.parser()
							.setSigningKey(SECRET_KEY)
							.parseClaimsJws(token)
							.getBody();

					if (claims.getExpiration().before(new Date())) {
						iterator.remove();
						log.info("清理过期的黑名单Token: {}", token);
					}
				} catch (Exception e) {
					// 解析失败的Token（可能是格式错误），从黑名单中移除
					iterator.remove();
					log.info("清理无效的黑名单Token: {}", token);
				}
			}
		}
	}
}
