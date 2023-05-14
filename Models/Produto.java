package Models;

import Repositorio.DataBase;

public class Produto {
    private String nome;
    private String tipo;
    private double preco;
    private int id;

    /* Constructor */
    public Produto() {
    }

    public Produto(String nome, String tipo, double preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.id = DataBase.criaIdProduto(DataBase.tamanhoEstoque());
    }

    /* Getters&Setters */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    /* Métodos */

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Tipo: " + tipo + " | Preço: R$" + preco + " ";
    }
}
