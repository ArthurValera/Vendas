package com.pjt.vendas.repositorios;

import com.pjt.vendas.modelos.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

//interaçoes com o banco serao feitas aqui
public interface EstadoRep extends JpaRepository<Estado, Long> {

}
