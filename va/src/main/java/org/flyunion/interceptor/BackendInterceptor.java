package org.flyunion.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.flyunion.annotation.BackendAuthorization;
import org.flyunion.annotation.SkipAuthentication;
import org.flyunion.entity.User;
import org.flyunion.service.UserService;
import org.flyunion.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

/**
 * 请求信息拦截器
 * 拦截请求信息，并进行权限检查
 * 如果请求方法有BackendAuthorization注解，则检查用户权限
 * 如果用户权限不足，则返回403错误
 * 如果用户权限满足，则放行请求
 * 如果请求方法没有BackendAuthorization注解，则放行请求
 * 如果请求方法有SkipAuthentication注解，则放行请求
 * 如果请求方法没有SkipAuthentication注解，则检查用户Token
 * 如果用户Token有效，则放行请求
 * 如果用户Token无效，则返回401错误
 * @author 1228
 * @version 0.1-SNAPSHOT
 * */
@Slf4j
@Component
public class BackendInterceptor implements HandlerInterceptor {

	private final UserService userService;

	public BackendInterceptor(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		log.info("请求方法：{}", request.getMethod());
		log.info("请求地址：{}", request.getRequestURI());
		log.info("请求IP：{}", getClientIP(request));

		// 检查是否是方法处理器
		if (!(handler instanceof HandlerMethod handlerMethod)) {
			log.info("放行非HandlerMethod: {} - 类型: {} - URI: {}",
					handler.getClass().getSimpleName(), handler, request.getRequestURI());
			return true;
		}else{
			log.info("拦截HandlerMethod: {} - URI: {}",
					handlerMethod.getShortLogMessage(),
					request.getRequestURI());
		}

		Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(SkipAuthentication.class)){
            return true;
        }
		// 检查方法是否有BackendAuthorization注解
		if (method.isAnnotationPresent(BackendAuthorization.class)) {
            BackendAuthorization authAnnotation = method.getAnnotation(BackendAuthorization.class);
            String token = request.getHeader("Authorization");
            User user = userService.loadUserByCid(JwtUtil.getCidFromToken(token));
            int requiredPermission = authAnnotation.permission();

            // 获取用户权限
            Integer userPermission = user.getPermission();

            // 权限检查
            if (userPermission == null || userPermission < requiredPermission) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("{\"code\": 403, \"message\": \"Permission Not Granted\", \"data\": null}");
                return false;
            }
            return true;
		}
		return true;
	}
	private String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
