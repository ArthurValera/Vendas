package com.pjt.vendas.repositorios;


import com.pjt.vendas.modelos.ItemEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//interaçoes com o banco serao feitas aqui
public interface ItemEntradaRep extends JpaRepository<ItemEntrada, Long> {
    @Query("SELECT e FROM ItemEntrada e WHERE e.entradaProd.id = ?1")
    List<ItemEntrada> buscarPorEntrada(long id);

}
