package com.pjt.vendas.modelos;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Pessoa {

    //JPA
    public Cliente() {
        super();
    }

    public Cliente(Long id, String nome, String cpf, String telefone, String endereco, String numero, String bairro, String email, Cidade cidade, String funcao) {
        super(id, nome, cpf, telefone, endereco, numero, bairro, email, cidade);
    }
}