package Models;

import java.util.ArrayList;

public class Cliente extends Usuario {
    private String endereco, fone;
    private ArrayList<Pedido> histPedidos;
    private ArrayList<Produto> carrinho;

    /*Consatrutores*/
    public Cliente(String nome, String id, String email, String senha, String endereco, String fone) {
        super(nome, id, email, senha);
        this.endereco = endereco;
        this.fone = fone;
    }

    /*Getters&Setters*/

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getFone() {
        return fone;
    }
    public void setFone(String fone) {
        this.fone = fone;
    }
    public ArrayList<Pedido> getHistPedidos() {
        return histPedidos;
    }
    public void setHistPedidos(ArrayList<Pedido> histPedidos) {
        this.histPedidos = histPedidos;
    }
    public ArrayList<Produto> getCarrinho() {
        return carrinho;
    }
    public void setCarrinho(ArrayList<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    /*Métodos*/
    public void registrarPedido(Produto p) {
        carrinho.add(p);
        System.out.println("Item adicionado ao carrinho.");
    }
    public void removerPedido() {
        System.out.println("Qual pedido deseja remover do carrinho?");
        for(int i = 0; i < carrinho.size(); i++) {
            System.out.println(carrinho.get(i).getNome());
            System.out.println(carrinho.get(i).getPreco());
        }
    }
}
