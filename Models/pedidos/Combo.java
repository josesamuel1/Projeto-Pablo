package Models.pedidos;

import Models.pedidos.Produto;

import java.util.ArrayList;

public class Combo {
    private ArrayList<Produto> produtos = new ArrayList<>();
    private int porcentagemDesconto;

    /*Construtores*/
    public Combo(){}
    public Combo(ArrayList<Produto> produtos, int porcentagemDesconto) {
        this.produtos = produtos;
        this.porcentagemDesconto = porcentagemDesconto;
    }
    /*Getters&Setters*/
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    public int getPorcentagemDesconto() {
        return porcentagemDesconto;
    }
    public void setPorcentagemDesconto(int porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }

    /*Métodos*/
    public void adicionarProduto(Produto produto){
        produtos.add(produto);
    }
    public void removerProduto(Produto produto){
        produtos.remove(produto);
    }
}
