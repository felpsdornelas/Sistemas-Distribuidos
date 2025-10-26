package br.edu.iftm.tspi.sd.websockets_atividade.listener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import br.edu.iftm.tspi.sd.websockets_atividade.config.PresenceChannelInterceptor;
import br.edu.iftm.tspi.sd.websockets_atividade.handler.Mensagem;
import br.edu.iftm.tspi.sd.websockets_atividade.handler.TipoMensagem;

@Component
public class WebSocketEventListener {
     @Autowired
     private PresenceChannelInterceptor presenceChannelInterceptor;

     @Autowired
     private SimpMessagingTemplate messagingTemplate;

     private final List<String> usuariosOnline = new CopyOnWriteArrayList<>();

     @EventListener
     public void handleWebSocketConnectListener(SessionConnectEvent event) {
          StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
          String username = accessor.getFirstNativeHeader("username");
          if (username != null && !usuariosOnline.contains(username)) {
               usuariosOnline.add(username);
               messagingTemplate.convertAndSend("/topic/online", usuariosOnline);
          }
     }

     @EventListener
     public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
          String sessionId = event.getSessionId();
          String username = presenceChannelInterceptor.getUsuariosConectados().remove(sessionId);
          if (username != null) {
               usuariosOnline.remove(username);

               Mensagem msg = new Mensagem();
               msg.setOrigem(username);
               msg.setTipoMensagem(TipoMensagem.SAIR);
               msg.setTexto(username + " saiu");
               messagingTemplate.convertAndSend("/topic/public", msg);

               messagingTemplate.convertAndSend("/topic/online", usuariosOnline);
          }
     }
}
