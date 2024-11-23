package com.pjt.vendas.repositorios;

import com.pjt.vendas.modelos.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

//interaçoes com o banco serao feitas aqui
public interface CidadeRep extends JpaRepository<Cidade, Long> {


}
