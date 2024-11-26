package com.pjt.vendas.repositorios;


import com.pjt.vendas.modelos.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//interaçoes com o banco serao feitas aqui
public interface ItemVendaRep extends JpaRepository<ItemVenda, Long> {
    @Query("SELECT e FROM ItemVenda e WHERE e.venda.id = ?1")
    List<ItemVenda> buscarPorVenda(long id);
}
