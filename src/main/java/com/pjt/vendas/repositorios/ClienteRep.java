package com.pjt.vendas.repositorios;


import com.pjt.vendas.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

//interaçoes com o banco serao feitas aqui
public interface ClienteRep extends JpaRepository<Cliente, Long> {


}
