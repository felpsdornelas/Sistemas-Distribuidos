package br.edu.iftm.tspi.sd.websockets_atividade.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker
public class WsConfig implements WebSocketMessageBrokerConfigurer {

     @Autowired
     private UserInterceptor userInterceptor;

     private PresenceChannelInterceptor presenceChannelInterceptor;

     @Override
     public void registerStompEndpoints(StompEndpointRegistry registry) {
          registry.addEndpoint("/ws").setAllowedOriginPatterns("*");

     }

     @Override
     public void configureMessageBroker(MessageBrokerRegistry registry) {
          registry.enableSimpleBroker("/topic");
          registry.setApplicationDestinationPrefixes("/app");
     }

     @Override
     public void configureClientInboundChannel(ChannelRegistration registration) {
          if (userInterceptor != null) {
               registration.interceptors(userInterceptor);
          }
     }
}
