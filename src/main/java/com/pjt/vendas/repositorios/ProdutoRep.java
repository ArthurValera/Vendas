package com.pjt.vendas.repositorios;

import com.pjt.vendas.modelos.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

//interaçoes com o banco serao feitas aqui
public interface ProdutoRep extends JpaRepository<Produto, Long> {

}
