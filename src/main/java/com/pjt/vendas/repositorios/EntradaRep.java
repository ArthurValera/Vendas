package com.pjt.vendas.repositorios;

import com.pjt.vendas.modelos.EntradaProd;
import org.springframework.data.jpa.repository.JpaRepository;

//interaçoes com o banco serao feitas aqui
public interface EntradaRep extends JpaRepository<EntradaProd, Long> {

}
