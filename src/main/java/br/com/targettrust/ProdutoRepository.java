package br.com.targettrust;

import java.util.List;

public interface ProdutoRepository {
    List<Produto> findAll();
}
