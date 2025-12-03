package br.eduiftm.tspi.sd.exercicio_niveis_isolamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eduiftm.tspi.sd.exercicio_niveis_isolamento.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
     
}
