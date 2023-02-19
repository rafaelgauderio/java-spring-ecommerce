package com.rafaeldeluca.Ecommerce.Java.crontrollers;

import com.rafaeldeluca.Ecommerce.Java.dto.ProductDTO;
import com.rafaeldeluca.Ecommerce.Java.entities.Product;
import com.rafaeldeluca.Ecommerce.Java.repositories.ProductRepository;
import com.rafaeldeluca.Ecommerce.Java.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

// um controladdor implementa um recurso na API REST
// funcionalidades de um sistema web são organizadas em forma de recursos URL
// URL = universal resource locator
@RestController
@RequestMapping (value = "/produtos")
public class ProductController {

    @Autowired
    private ProductService servico;

    @GetMapping(value="/{id}")
    public ProductDTO buscarPorId (@PathVariable Long id){
        ProductDTO produtoDTO = servico.buscarPorId(id);
        return produtoDTO;
    }

}
