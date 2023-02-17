package com.rafaeldeluca.Ecommerce.Java.entities;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(columnDefinition = "TEXT") // para poder ser um texto longo maior que VARCHAR (255)
    private String descricao;
    private Double preco;
    private String imgUrl;

    // Como a relação é muitos para muitos, para não repetir o Id na Tabela de relacionamento (JoinTable) gerada
    // deve-se usar o tipo Set e não List. Colletion set não aceita valores repetidos do par key, value
    @ManyToMany
    @JoinTable(name = "tb_product_category",
    joinColumns = @JoinColumn(name = "produto_id"),
    inverseJoinColumns = @JoinColumn(name="categoria_id"))
    private Set<Category> categorias = new HashSet<Category>();

    //acessar os itens do pedido de cada produto
    @OneToMany(mappedBy = "id.produto")
    private Set<OrderItem> items = new HashSet<OrderItem>();


    public Product () {

    }

    public Product(Long id, String nome, String descricao, Double preco, String imgUrl) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategorias() {
        return categorias;
    }

    /*
    public void setCategorias(Set<Category> categorias) {
        this.categorias = categorias;
    }
     */
}
