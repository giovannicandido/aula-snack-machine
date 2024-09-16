package br.com.targettrust;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    public static final String BANCO_JSON = "banco.json";
    public static final String USER_DIR = "user.home";
    // We probably need to configure the mapper in all places
    private ObjectMapper objectMapper = new ObjectMapper();

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

    /**
     * Get the database json file path from user directory
     * @return The file path
     */
    private String findDatabasePath() {
        String userHome = System.getProperty(USER_DIR);
        return MessageFormat.format("{0}{1}{2}", userHome, File.separator, BANCO_JSON);
    }
}
