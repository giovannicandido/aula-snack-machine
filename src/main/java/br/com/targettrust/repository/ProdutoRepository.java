package br.com.targettrust.repository;

import br.com.targettrust.model.Produto;

import java.util.List;

public interface ProdutoRepository {
    List<Produto> findAll();
}
