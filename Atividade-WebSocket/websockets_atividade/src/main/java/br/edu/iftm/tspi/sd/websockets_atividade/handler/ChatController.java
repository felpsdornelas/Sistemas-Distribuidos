package br.edu.iftm.tspi.sd.websockets_atividade.handler;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

     @Autowired
     private SimpMessagingTemplate messagingTemplate;

     private final List<String> usuariosOnline = new CopyOnWriteArrayList<>();

     @MessageMapping("/chat.send")
     @SendTo("/topic/public")
     public Mensagem enviarTexto(Mensagem mensagem) {
          mensagem.setTipoMensagem(TipoMensagem.ENVIAR_TEXTO);
          mensagem.setDataHora(Instant.now());
          return mensagem;
     }

     @MessageMapping("/chat.join")
     @SendTo("/topic/public")
     public Mensagem entrar(Mensagem mensagem) {
          mensagem.setTipoMensagem(TipoMensagem.ENTRAR);
          mensagem.setDataHora(Instant.now());

          if (!usuariosOnline.contains(mensagem.getOrigem())) {
               usuariosOnline.add(mensagem.getOrigem());
          }

          notificarUsuariosOnline(); // Atualiza
          return mensagem;
     }

     @MessageMapping("/chat.leave")
     @SendTo("/topic/public")
     public Mensagem sair(Mensagem mensagem) {
          mensagem.setTipoMensagem(TipoMensagem.SAIR);
          mensagem.setDataHora(Instant.now());
          mensagem.setTexto(mensagem.getOrigem() + " saiu");

          usuariosOnline.remove(mensagem.getOrigem());
          notificarUsuariosOnline(); // Atualiza
          return mensagem;
     }

     public void notificarUsuariosOnline() {
          messagingTemplate.convertAndSend("/topic/online", usuariosOnline);
     }

     @MessageMapping("/chat.private")
     public void enviarPrivado(Mensagem mensagem) {
          mensagem.setTipoMensagem(TipoMensagem.ENVIAR_TEXTO);
          mensagem.setDataHora(Instant.now());

          messagingTemplate.convertAndSend(
                    "/topic/dm." + mensagem.getDestino(), mensagem);

          messagingTemplate.convertAndSend(
                    "/topic/dm." + mensagem.getOrigem(), mensagem);
     }
}
