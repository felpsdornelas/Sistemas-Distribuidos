package br.edu.iftm.tspi.sd.websockets_atividade.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class UserInterceptor implements ChannelInterceptor {
     
     public static final Map<String, String> usuariosConectados = new ConcurrentHashMap<>();

     @Override
     public Message<?> preSend(Message<?> message, MessageChannel channel) {
          StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

          if (accessor.getCommand() != null && accessor.getCommand().toString().equals("CONNECT")) {
               String username = accessor.getFirstNativeHeader("username");
               if (username != null && !username.isEmpty()) {
                    usuariosConectados.put(accessor.getSessionId(), username);
               }
          }
          return message;
     }
}
