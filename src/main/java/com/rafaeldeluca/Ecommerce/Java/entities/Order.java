package com.rafaeldeluca.Ecommerce.Java.entities;

import com.rafaeldeluca.Ecommerce.Java.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition ="TIMESTAMP WITHOUT TIME ZONE") // salvar no bando com padrão UTC
    private Instant momentoDoPedido;
    private OrderStatus statusDoPedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // chave estrangeira no banco
    private User cliente;


    @OneToOne (mappedBy = "pedido", cascade = CascadeType.ALL)
    private Payment pagamento;

    @OneToMany(mappedBy = "id.pedido")
    private Set<OrderItem> items = new HashSet<OrderItem>();

    public Order () {

    }

    public Order(Long id, Instant momentoDoPedido, OrderStatus statusDoPedido, User cliente, Payment pagamento) {
        this.id = id;
        this.momentoDoPedido = momentoDoPedido;
        this.statusDoPedido = statusDoPedido;
        this.cliente = cliente;
        this.pagamento = pagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMomentoDoPedido() {
        return momentoDoPedido;
    }

    public void setMomentoDoPedido(Instant momentoDoPedido) {
        this.momentoDoPedido = momentoDoPedido;
    }

    public OrderStatus getStatusDoPedido() {
        return statusDoPedido;
    }

    public void setStatusDoPedido(OrderStatus statusDoPedido) {
        this.statusDoPedido = statusDoPedido;
    }

    public User getCliente() {
        return cliente;
    }

    public void setCliente(User cliente) {
        this.cliente = cliente;
    }

    public Payment getPagamento() {
        return pagamento;
    }

    public void setPagamento(Payment pagamento) {
        this.pagamento = pagamento;
    }
}