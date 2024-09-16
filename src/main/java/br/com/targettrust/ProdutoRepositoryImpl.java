package br.com.targettrust;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    @Override
    public List<Produto> findAll() {
        return new ArrayList<>();
    }
}
