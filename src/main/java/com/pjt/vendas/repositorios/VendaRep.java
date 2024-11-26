package com.pjt.vendas.repositorios;

import com.pjt.vendas.modelos.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

//interaçoes com o banco serao feitas aqui
public interface VendaRep extends JpaRepository<Venda, Long> {

}
