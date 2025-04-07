package org.flyunion.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.flyunion.annotation.SkipAuthentication;
import org.flyunion.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 验证Token拦截器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod handlerMethod) {

			// 检查是否有SkipAuthentication注解
			if (handlerMethod.getBeanType().isAnnotationPresent(SkipAuthentication.class) ||
					handlerMethod.getMethod().isAnnotationPresent(SkipAuthentication.class)) {
				log.info("检测到@SkipAuthentication注解，跳过Token验证拦截器！");
				return true; // 跳过验证
			}

			// 从请求头中获取Token
			String token = request.getHeader("Authorization");

			// 进行Token验证
			if (token == null || !JwtUtil.validateToken(token)) {
				log.error("未获取Token或Token已过期！");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("{\"code\": 401, \n \"message\": \"No Token Detected Or Token Expired\", \n \"data\": null}");
				return false; // 验证失败，不继续处理请求
			}
		}

		return true; // 验证成功或无需验证，继续处理请求
	}
}  