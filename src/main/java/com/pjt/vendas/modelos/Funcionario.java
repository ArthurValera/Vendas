package com.pjt.vendas.modelos;
import jakarta.persistence.*;

@Entity
public class Funcionario extends Pessoa {

    private String funcao;

    //JPA
    public Funcionario() {
        super();
    }

    public Funcionario(Long id, String nome, String cpf, String telefone, String endereco, String numero, String bairro, String email, Cidade cidade, String funcao) {
        super(id, nome, cpf, telefone, endereco, numero, bairro, email, cidade);
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}