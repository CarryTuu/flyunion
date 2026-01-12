package org.flyunion.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket 配置
 * @author 1228
 * @version 0.2.6-HOTFIX
 * */
@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws-aviation")
				.setAllowedOriginPatterns("*") // 生产环境应该配置具体的域名
				.withSockJS(); // 启用 SockJS 回退选项

		log.info("WebSocket 服务器已启动，端点: /ws-aviation");
	}

}
