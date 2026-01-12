package org.flyunion.config;

import org.flyunion.interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC配置器
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final AuthenticationInterceptor authenticationInterceptor;
	private final BackendInterceptor backendInterceptor;
    private final SimulatorAuthorizationInterceptor simulatorAuthorizationInterceptor;

	public WebConfig(AuthenticationInterceptor authenticationInterceptor, BackendInterceptor backendInterceptor, SimulatorAuthorizationInterceptor simulatorAuthorizationInterceptor) {
		this.authenticationInterceptor = authenticationInterceptor;
		this.backendInterceptor = backendInterceptor;
        this.simulatorAuthorizationInterceptor = simulatorAuthorizationInterceptor;
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor)
				.addPathPatterns("/**")
				.order(0);
		registry.addInterceptor(backendInterceptor)
				.addPathPatterns("/**")
				.order(1);
        registry.addInterceptor(simulatorAuthorizationInterceptor)
                .addPathPatterns("/**")
                .order(2);
	}
}
