package br.com.targettrust;

import br.com.targettrust.model.Produto;
import br.com.targettrust.repository.ProdutoRepository;
import br.com.targettrust.repository.ProdutoRepositoryImpl;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Lendo arquivos json");

        ProdutoRepository produtoRepository = new ProdutoRepositoryImpl();
        List<Produto> produtos = produtoRepository.findAll();
        System.out.println(produtos);

        Produto produto = new Produto();
        produto.setCodigo(30);
        produto.setNome("Novo produto");
        produto.setPreco(new BigDecimal("20.45"));
        produtoRepository.save(produto);

        produtos = produtoRepository.findAll();
        System.out.println(produtos);

        // Cliente escolhe 10

        Produto procurado = new Produto();
        procurado.setCodigo(10);

        int index = produtos.indexOf(produto);

//        if(index > -1){
//            produtos.get(index);
//            produtos.remove(index);
//            produtos.set(index, novoProduto);
//        }

    }
}