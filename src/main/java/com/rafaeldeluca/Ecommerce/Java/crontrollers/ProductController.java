package com.rafaeldeluca.Ecommerce.Java.crontrollers;

import com.rafaeldeluca.Ecommerce.Java.dto.ProductDTO;
import com.rafaeldeluca.Ecommerce.Java.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public Page<ProductDTO> buscarTodos (Pageable paginavel) {
        Page<ProductDTO> listaPaginadaDeproductDTO = servico.bucarTodos(paginavel);
        // vai retornar uma lista com o parametro content. Esse parametro content é uma lista com os objetos Product
        // por default retorna uma página de 20 elementos
        return listaPaginadaDeproductDTO;
    }

}
