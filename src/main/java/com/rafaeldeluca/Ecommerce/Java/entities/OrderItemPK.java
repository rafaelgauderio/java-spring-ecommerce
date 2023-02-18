package com.rafaeldeluca.Ecommerce.Java.entities;

// as duas chaves estrangeiras juntas vão formar uma chave primária composta
// no java é necessário cliar um nova chave para representar a chave primária (primary key) pk

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name ="pedido_id")
    private Order pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Product produto;

    public OrderItemPK() {

    }

    public OrderItemPK(Order pedido, Product produto) {
        this.pedido = pedido;
        this.produto = produto;
    }

    public Order getPedido() {
        return pedido;
    }

    public void setPedido(Order pedido) {
        this.pedido = pedido;
    }

    public Product getProduto() {
        return produto;
    }

    public void setProduto(Product produto) {
        this.produto = produto;
    }
}
