package com.rafaeldeluca.Ecommerce.Java.crontrollers;

import com.rafaeldeluca.Ecommerce.Java.entities.Product;
import com.rafaeldeluca.Ecommerce.Java.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private ProductRepository repositorio;

    @GetMapping
    public String buscarPorId (){
        Optional<Product> resultado = repositorio.findById(2L);
        Product produto = resultado.get(); // pega o objeto que estiver dentro do Optinal
        return produto.getNome();
    }

}
