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
        copiardeDToParaEntidade(dto,produto);
        produto = repositorio.save(produto);
        return new ProductDTO(produto);
    }
    @Transactional(readOnly = false)
    public ProductDTO atualizar(Long id, ProductDTO dto) {
        // operação getReferenceById não toca o database, somente prepara o objeto monitorado pela jpa
        // ao se instanciar um objeto com new, ele não está monitorado pela jpa
        Product produto = repositorio.getReferenceById(id);
        copiardeDToParaEntidade(dto,produto);
        produto = repositorio.save(produto);
        return new ProductDTO(produto);
    }

    private void copiardeDToParaEntidade(ProductDTO dto, Product produto) {
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setImgUrl(dto.getImgUrl());
    }

}
