package br.com.targettrust;

import br.com.targettrust.model.Produto;
import br.com.targettrust.repository.ProdutoRepository;
import br.com.targettrust.repository.ProdutoRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Lendo arquivos json");

        ProdutoRepository produtoRepository = new ProdutoRepositoryImpl();
        List<Produto> produtos = produtoRepository.findAll();
        System.out.println(produtos);

    }
}