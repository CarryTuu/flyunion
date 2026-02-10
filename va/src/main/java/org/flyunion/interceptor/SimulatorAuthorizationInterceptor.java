package org.flyunion.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.flyunion.annotation.SimulatorAuthorization;
import org.flyunion.annotation.SkipAuthentication;
import org.flyunion.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Slf4j
@Component
public class SimulatorAuthorizationInterceptor implements HandlerInterceptor {
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
        if (method.isAnnotationPresent(SimulatorAuthorization.class)) {
            String authorization = request.getHeader("Authorization");
            if (authorization == null || authorization.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"code\": 401, \"message\": \"Unauthorized\", \"data\": null}");
                return false;
            }

            log.info("已获取Token：{}", authorization);
            if (JwtUtil.validateToken(authorization)){
                return true;
            }
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"code\": 401, \"message\": \"Unauthorized\", \"data\": null}");
            return false;
        }else if(method.isAnnotationPresent(SkipAuthentication.class)){
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
