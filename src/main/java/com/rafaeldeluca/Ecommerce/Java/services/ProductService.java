package com.rafaeldeluca.Ecommerce.Java.services;

import com.rafaeldeluca.Ecommerce.Java.dto.ProductDTO;
import com.rafaeldeluca.Ecommerce.Java.entities.Product;
import com.rafaeldeluca.Ecommerce.Java.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<ProductDTO> bucarTodos (Pageable paginavel) {
        Page<Product> listaPaginadaDeProdutos = repositorio.findAll(paginavel);
        // converter Page de Produto para  page ProdutoDTO
        // page já é um stream do java. Pode chamar direto a aplica função labda sem ter que conveter para stream
        // e depois de volta para lista
        return listaPaginadaDeProdutos.map(produto-> new ProductDTO(produto));
    }

    @Transactional(readOnly = false)
    public ProductDTO inserir(ProductDTO dto) {
        // preparar um entidade do tipo produto para receber o dto que veio lá do controlador
        Product produto = new Product();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setImgUrl(dto.getImgUrl());

        produto = repositorio.save(produto);
        return new ProductDTO(produto);

    }


}
