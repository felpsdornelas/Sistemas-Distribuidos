package br.edu.iftm.tspi.sd.websockets_atividade.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class PresenceChannelInterceptor implements ChannelInterceptor {
     private final Map<String, String> usuariosConectados = new ConcurrentHashMap<>();

     public Map<String, String> getUsuariosConectados() {
          return usuariosConectados;
     }

     @Override
     public Message<?> preSend(Message<?> message, MessageChannel channel) {
          StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

          if (StompCommand.CONNECT.equals(accessor.getCommand())) {
               String username = accessor.getFirstNativeHeader("username");
               if (username != null) {
                    usuariosConectados.put(accessor.getSessionId(), username);
                    System.out.println("Usuário conectado: " + username);
               }
          } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
               String username = usuariosConectados.remove(accessor.getSessionId());
               System.out.println("Usuário desconectado: " + username);
          }

          return message;
     }
}
