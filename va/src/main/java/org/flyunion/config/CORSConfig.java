package org.flyunion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

/**
 * CORS配置
 *
 * @author 1228
 * @version 0.1-SNAPSHOT
 */
//@Configuration
//@EnableWebMvc
public class CORSConfig {

	@Bean
	public CorsFilter corsFilter() {
		//0.2.5-HOTFIX：测试用途，将允许请求的源改为个人电脑IP
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("*")); // 允许来自这个源的请求
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 允许的方法
		config.setAllowedHeaders(List.of("*")); // 允许的头部

		// 对于所有路径应用CORS配置
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}

}
