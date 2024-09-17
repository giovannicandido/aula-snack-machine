package br.com.targettrust.repository;

import br.com.targettrust.model.Produto;

import java.util.List;

public class BancoDados {
    private List<Produto> produtos;
    // A lista de vendas pode ficar aqui

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
