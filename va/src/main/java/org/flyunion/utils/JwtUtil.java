package org.flyunion.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.flyunion.exception.TokenExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */

public class JwtUtil {
	private static final String SECRET_KEY = "flyunion100013"; // 用于签名的密钥
	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

	// 生成JWT
	public static String generateTokenByCID(String cid) {
		log.info("开始生成token，登录用户: {}", cid);
		Map<String, Object> claims = new HashMap<>();
		claims.put("cid", cid);

		String token = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1440))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
		log.info("token生成完毕: {}，有效时间为三十分钟", token);
		return token;
	}

	// 生成JWT
	public static String generateTokenByEmail(String email) {
		log.info("开始生成token，登录用户: {}", email);
		Map<String, Object> claims = new HashMap<>();
		claims.put("email", email);

		String token = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
		log.info("token生成完毕: {}，有效时间为三十分钟", token);
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

}
