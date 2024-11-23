package com.pjt.vendas.repositorios;

import com.pjt.vendas.modelos.Fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;

//interaçoes com o banco serao feitas aqui
public interface FornecedorRep extends JpaRepository<Fornecedor, Long> {


}
