package com.pjt.vendas.modelos;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "item_entrada")
public class ItemEntrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double preco = 0;
    private double custo;
    private int quantidade = 0;
    @ManyToOne
    @JoinColumn(name = "entrada_prod_id")
    private EntradaProd entradaProd;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public EntradaProd getEntradaProd() {
        return entradaProd;
    }

    public void setEntradaProd(EntradaProd entradaProd) {
        this.entradaProd = entradaProd;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
}