package br.com.targettrust.controller;

import br.com.targettrust.model.Produto;
import br.com.targettrust.repository.ProdutoRepository;
import br.com.targettrust.repository.ProdutoRepositoryImpl;

import java.util.List;

//
public class ProdutoController {

    private ProdutoRepository produtoRepository = new ProdutoRepositoryImpl();
    // Caso de uso ou entrada do fluxo
    public void refill() {
        // Carregar arquivo csv
        // comparar com o banco disponivel
        // alterar as quantidades
        // vao estar no csv mas não estão no banco -> inserir
        // não vai estar no csv e vai estar no banco -> manter
        // vai estar nos dois -> atualizar os dados do produto e a quantidade mantendo o codigo,
        //    sendo que a quantidade é uma soma
        // salvar
        //

    }

    public List<Produto> listAvailable() {
        return produtoRepository.findAll()
                .stream()
                .filter(produto -> produto.getQuantidade() > 0)
                .toList();
    }
}
