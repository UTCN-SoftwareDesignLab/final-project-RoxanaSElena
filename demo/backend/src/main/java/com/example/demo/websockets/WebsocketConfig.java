package com.example.demo.websockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@CrossOrigin(value = "*", maxAge = 3600)
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    public void configureMessageBroker(final MessageBrokerRegistry config)
    {
        config.enableSimpleBroker("/d");
        config.setApplicationDestinationPrefixes("/app");
    }

    public void registerStompEndpoints (final StompEndpointRegistry registry)
    {
        registry.addEndpoint("/gs-guide-websocket")
                .setAllowedOriginPatterns("*")
               // .setAllowedOrigins("http://localhost:8092")
                .withSockJS();
    }
}
