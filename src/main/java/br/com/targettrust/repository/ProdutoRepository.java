package br.com.targettrust.repository;

import br.com.targettrust.model.Produto;

import java.util.List;

public interface ProdutoRepository {
    /**
     * Retrieve a list of all products.
     *
     * @return a list of products
     */
    List<Produto> findAll();

    /**
     * Save or update the product by codigo
     * @param produto
     */
    void save(Produto produto);

    /**
     * Save all overrides the produtos in database with the list
     * @param produtos List of products
     */
    void saveAll(List<Produto> produtos);
}
