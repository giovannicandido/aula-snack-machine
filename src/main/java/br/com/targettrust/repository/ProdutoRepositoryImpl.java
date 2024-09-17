package br.com.targettrust.repository;

import br.com.targettrust.model.Produto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    public static final String BANCO_JSON = "banco.json";
    public static final String USER_DIR = "user.home";
    // We probably need to configure the mapper in all places
    private ObjectMapper objectMapper;

    public ProdutoRepositoryImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
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
