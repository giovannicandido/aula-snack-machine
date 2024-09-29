package br.com.targettrust.controller;

import br.com.targettrust.model.Produto;
import br.com.targettrust.repository.ProdutoRepository;
import br.com.targettrust.repository.ProdutoRepositoryImpl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.targettrust.Constants.MACHINE_ID;
import static br.com.targettrust.Constants.USER_DIR;

public class ManutecaoController {
    private static final String CAMINHO_ARQUIVOS = System.getProperty(USER_DIR);
    private ProdutoRepository produtoRepository = new ProdutoRepositoryImpl();

    private List<String> produtosCsv;
    private List<Produto> produtosDisponiveis;
    private List<Produto> produtosAImportar;
    private List<Integer> codigosImportacao;
    private List<Produto> produtosDisponiveisAtualizar;
    private List<Produto> produtosAAdicionar;

    public void iniciarManutecao() {
        prepararExecucao();
        imprimirArquivoDeImportacao();
        // não são todos os produtos
        imprimirPlano();
        calcularEImportarProdutos2();
        imprimirResultado();
    }

    private void imprimirResultado() {
        System.out.println("Produtos disponiveis na maquina: ");
        produtoRepository.findAll()
                .forEach(System.out::println);
    }

    private void calcularEImportarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        // Não atualizoua quantidade (soma) sobrescreveu ela.
        List<Produto> produtosNovosAtualizar = new ArrayList<>();
        produtosDisponiveisAtualizar.forEach(produto -> {
            Integer quantidadeAtual = produto.getQuantidade();
            Integer codigo = produto.getCodigo();
            Optional<Produto> produtoArquivo = produtosAImportar.stream()
                    .filter(pa -> pa.getCodigo().equals(codigo))
                    .findFirst();
            produtoArquivo.ifPresent(p -> {
                p.setQuantidade(quantidadeAtual + p.getQuantidade());
                produtosNovosAtualizar.add(p);
            });
        });

        List<Produto> produtosExistentes = produtosDisponiveis.stream()
                .filter(p -> !produtosNovosAtualizar.contains(p) && !produtosAAdicionar.contains(p))
                .toList();

        produtos.addAll(produtosNovosAtualizar);
        produtos.addAll(produtosAAdicionar);
        produtos.addAll(produtosExistentes);


        produtoRepository.saveAll(produtos);


    }

    private void calcularEImportarProdutos2() {
        List<Produto> produtosDisponiveis = new ArrayList<>(this.produtosDisponiveis);
        produtosAImportar.forEach(produtoImportar -> {
            Optional<Produto> produtoBanco = produtosDisponiveis.stream()
                    .filter(p -> p.getCodigo().equals(produtoImportar.getCodigo()))
                    .findFirst();

            produtoBanco.ifPresentOrElse(pbanco -> {
                produtoImportar.setQuantidade(pbanco.getQuantidade() + produtoImportar.getQuantidade());
                produtosDisponiveis.remove(pbanco);
                produtosDisponiveis.add(produtoImportar);
            }, () -> {
                produtosDisponiveis.add(produtoImportar);
            });
        });

        produtoRepository.saveAll(produtosDisponiveis);
    }

    private void imprimirPlano() {
        System.out.println("Produtos a serem atualizados: ");
        produtosDisponiveisAtualizar.forEach(System.out::println);
        System.out.println("Produtos a serem adicionados: ");
        produtosAAdicionar.forEach(System.out::println);

    }

    private void prepararExecucao() {
        String importFilePath = MessageFormat.format("{0}{1}{2}{3}", CAMINHO_ARQUIVOS, File.separator, MACHINE_ID, ".csv");
        Path path = Paths.get(importFilePath);
        try {
            produtosCsv = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        produtosDisponiveis = produtoRepository.findAll();

        produtosAImportar = parseCsv(produtosCsv);

        codigosImportacao = produtosAImportar.stream().map(Produto::getCodigo).toList();

        produtosDisponiveisAtualizar = produtosDisponiveis
                .stream()
                .filter(disponivel -> codigosImportacao.contains(disponivel.getCodigo()))
                .toList();

        produtosAAdicionar = produtosAImportar
                .stream().filter(adicionar -> !produtosDisponiveisAtualizar.contains(adicionar))
                .toList();
    }

    private void imprimirArquivoDeImportacao() {
        System.out.println("Produtos a serem importados:");
        // :: é a referencia pra funçao println. Assim não precisa criar um lambda
        produtosCsv.forEach(System.out::println);

    }

    private List<Produto> parseCsv(List<String> csvLines) {
        return produtosCsv.stream()
                .map(line -> line.split(";"))
                .map(line -> new Produto(Integer.parseInt(line[0]), line[1], new BigDecimal(line[2]), Integer.parseInt(line[3])))
                .toList();
    }
}
