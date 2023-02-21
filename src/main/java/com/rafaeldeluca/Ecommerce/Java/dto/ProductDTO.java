package com.rafaeldeluca.Ecommerce.Java.dto;

import com.rafaeldeluca.Ecommerce.Java.entities.Product;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;


// validações Bean Validation sempre no DTO. DTO traféga na web.
public class ProductDTO {

    private Long id;

    @Size(min=3, max=70, message = "Nome tem que ter entre 3 e 70 caracteres")
    @NotBlank(message = "Campo nome é obrigatório")
    private String nome;
    @Size(min=10, max = 300, message = "Descrição tem que ter entre 10 e 300 caracteres")
    @NotBlank(message = "Campo descrição é obrigatório")
    private String descricao;
    @Positive (message = "O preço do produto deve ser maior que zero")
    private Double preco;
    private String imgUrl;

    public ProductDTO () {

    }

    public ProductDTO(Long id, String nome, String descricao, Double preco, String imgUrl) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.descricao = entidade.getDescricao();
        this.preco = entidade.getPreco();
        this.imgUrl = entidade.getImgUrl();
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
}
