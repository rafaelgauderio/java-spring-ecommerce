package com.rafaeldeluca.Ecommerce.Java.crontrollers;

import com.rafaeldeluca.Ecommerce.Java.dto.ProductDTO;
import com.rafaeldeluca.Ecommerce.Java.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

// um controladdor implementa um recurso na API REST
// funcionalidades de um sistema web são organizadas em forma de recursos URL
// URL = universal resource locator
@RestController
@RequestMapping (value = "/produtos")
public class ProductController {

    @Autowired
    private ProductService servico;

    @GetMapping(value="/{id}")
    public ResponseEntity<ProductDTO> buscarPorId (@PathVariable Long id){
        ProductDTO produtoDTO = servico.buscarPorId(id);
        return ResponseEntity.ok(produtoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> buscarTodos (Pageable paginavel) {
        Page<ProductDTO> listaPaginadaDeproductDTO = servico.bucarTodos(paginavel);
        // vai retornar uma lista com o parametro content. Esse parametro content é uma lista com os objetos Product
        // por default retorna uma página de 20 elementos
        return ResponseEntity.ok(listaPaginadaDeproductDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> inserir (@RequestBody ProductDTO dto) {
        // ao receber um objeto json do frontend o framework vai instanciar um ProdutoDTO
        // annotation @RequestBody, o corpo da requisição rebida vao instanciar um dto correpondente
        dto = servico.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        // create faz retornar o código 201 de recurso criado
        // boa prática, no cabeçalho da resposta vai ter um link com o recurso criado
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<ProductDTO> atualizar(@PathVariable Long id, @RequestBody ProductDTO dto) {
        dto = servico.atualizar(id,dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // retorna uma resposta com o corpo vazio
        servico.detelar(id);
        // resposta que da certo e não tem corpo retorna código http 204
        return ResponseEntity.noContent().build();
    }
}