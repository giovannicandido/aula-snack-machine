package br.com.targettrust.repository;

import br.com.targettrust.config.ObjectMapperConfig;
import br.com.targettrust.model.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    public static final String BANCO_JSON = "banco.json";
    public static final String USER_DIR = "user.home";
    private ObjectMapper objectMapper;

    public ProdutoRepositoryImpl() {
       objectMapper = ObjectMapperConfig.getInstance();
    }

    @Override
    public List<Produto> findAll() {
        String databasePath = findDatabasePath();
        File banco = new File(databasePath);
        try {
            BancoDados bancoDados = objectMapper.readValue(banco, BancoDados.class);
            return bancoDados.getProdutos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Produto produto) {
        List<Produto> produtos = findAll();
        produtos.remove(produto);
        produtos.add(produto);
        sort(produtos);
        writeDatabase(produtos);
    }

    private void sort(List<Produto> produtos) {
        Collections.sort(produtos);
    }

    private void writeDatabase(List<Produto> produtos) {
        String databasePath = findDatabasePath();
        File banco = new File(databasePath);
        try {
            BancoDados bancoDados = new BancoDados();
            bancoDados.setProdutos(produtos);
            objectMapper.writeValue(banco, bancoDados);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the database json file path from user directory
     *
     * @return The file path
     */
    private String findDatabasePath() {
        String userHome = System.getProperty(USER_DIR);
        return MessageFormat.format("{0}{1}{2}", userHome, File.separator, BANCO_JSON);
    }
}
