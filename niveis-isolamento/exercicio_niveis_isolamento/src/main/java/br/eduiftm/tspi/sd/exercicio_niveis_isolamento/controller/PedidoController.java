package br.eduiftm.tspi.sd.exercicio_niveis_isolamento.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eduiftm.tspi.sd.exercicio_niveis_isolamento.domain.ItemPedido;
import br.eduiftm.tspi.sd.exercicio_niveis_isolamento.domain.Pedido;
import br.eduiftm.tspi.sd.exercicio_niveis_isolamento.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> salvarPedido(@RequestBody Pedido pedido) {
        for (ItemPedido item: pedido.getItens()) {
            item.setPedido(pedido);
        }

        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
    }
}
