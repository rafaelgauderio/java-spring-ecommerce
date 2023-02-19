package com.rafaeldeluca.Ecommerce.Java.services;

import com.rafaeldeluca.Ecommerce.Java.dto.ProductDTO;
import com.rafaeldeluca.Ecommerce.Java.entities.Product;
import com.rafaeldeluca.Ecommerce.Java.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repositorio;

    @Transactional(readOnly = true)
    public ProductDTO buscarPorId (Long id) {
        // para buscar direto do banco de dados precisa do repository
        Optional<Product> resultado = repositorio.findById(id);
        Product produto = resultado.get();
        ProductDTO produtoDTO = new ProductDTO(produto);
        return produtoDTO;
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> bucarTodos () {
        List<Product> listaDeProdutos = repositorio.findAll();
        // converter lista de Produto par  Lista de ProdutoDTO
        return listaDeProdutos.stream().map(produto-> new ProductDTO(produto)).toList();
    }
}
