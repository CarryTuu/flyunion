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

import java.io.IOException;

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
		// 如果不是控制器方法，直接放行（如静态资源请求）
		if (!(handler instanceof HandlerMethod handlerMethod)) {
			return true;
		}

		// 检查是否有SkipAuthentication注解
		if (hasSkipAuthenticationAnnotation(handlerMethod)) {
			log.info("检测到@SkipAuthentication注解，跳过Token验证拦截器！");
			return true;
		}

		// 从请求头中获取Token
		String authHeader = request.getHeader("Authorization");

		// 验证Token格式和有效性
		if (!isValidToken(authHeader)) {
			handleUnauthorizedResponse(response);
			return false;
		}

		return true;
	}

	private boolean hasSkipAuthenticationAnnotation(HandlerMethod handlerMethod) {
		return handlerMethod.getBeanType().isAnnotationPresent(SkipAuthentication.class) ||
				handlerMethod.getMethod().isAnnotationPresent(SkipAuthentication.class);
	}

	private boolean isValidToken(String authHeader) {
		if (authHeader == null) {
			return false;
		}
		return JwtUtil.validateToken(authHeader);
	}

	private void handleUnauthorizedResponse(HttpServletResponse response) throws IOException {
		log.error("未获取Token或Token已过期！");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json;charset=UTF-8");

		// 使用更结构化的错误响应
		String errorResponse = "{\"code\": 401, \"message\": \"No Token Detected Or Token Expired\", \"data\": null}";
		response.getWriter().write(errorResponse);
	}
}