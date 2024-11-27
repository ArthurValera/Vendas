package com.pjt.vendas.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "item_entrada")
public class ItemEntrada extends Item {

    private double preco = 0;
    private double custo;

    @ManyToOne
    @JoinColumn(name = "entrada_prod_id", nullable = false)
    private EntradaProd entradaProd;

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public EntradaProd getEntradaProd() {
        return entradaProd;
    }

    public void setEntradaProd(EntradaProd entradaProd) {
        this.entradaProd = entradaProd;
    }
}
