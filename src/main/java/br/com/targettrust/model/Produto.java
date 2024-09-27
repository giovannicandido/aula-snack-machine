package br.com.targettrust.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto implements Comparable<Produto>{
    private Integer codigo;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;

    public Produto() {
    }

    public Produto(Integer codigo, String nome, BigDecimal preco, Integer quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // se os objetos estão na mesma posição de memoria
        if (this == o) return true;
        // Verifica se é mesma classe
        if (o == null || getClass() != o.getClass()) return false;
        // so converte para comparar
        Produto produto = (Produto) o;
        // a comparacao valida é aqui
        return Objects.equals(codigo, produto.codigo);
    }


    // Se o equals for igual o hashcode tem que gerar o mesmo numero
    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    @Override
    public int compareTo(Produto o) {
        if(nome == null || o.nome == null) {
          return -1;
        }

        return nome.toLowerCase().compareTo(o.nome.toLowerCase());
    }
}
