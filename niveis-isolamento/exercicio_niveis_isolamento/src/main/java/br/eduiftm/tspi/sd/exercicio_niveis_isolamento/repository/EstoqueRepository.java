package br.eduiftm.tspi.sd.exercicio_niveis_isolamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.eduiftm.tspi.sd.exercicio_niveis_isolamento.domain.Produto;

public interface EstoqueRepository extends JpaRepository<Produto, Integer>  {
     
     @Modifying
     @Query("UPDATE Produto p SET p.estoque = p.estoque - :quantidade WHERE p.id = :id")
     int atualizaEstoque (@Param("id") Integer id, @Param("quantidade") int quantidade); 
}
