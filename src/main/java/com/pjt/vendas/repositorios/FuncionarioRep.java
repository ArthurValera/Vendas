package com.pjt.vendas.repositorios;


import com.pjt.vendas.modelos.Cliente;
import com.pjt.vendas.modelos.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

//interaçoes com o banco serao feitas aqui
public interface FuncionarioRep extends JpaRepository<Funcionario, Long> {


}
