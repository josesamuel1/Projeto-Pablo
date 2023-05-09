package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {
    private ArrayList<Produto> produtos;
    private LocalDateTime data;
    private boolean pedidoAtivo;

    /*Construtor*/
    public Pedido(ArrayList<Produto> produtos){
        this.produtos = produtos;
    }
    public Pedido(Produto produto, int quantidade, ArrayList<Produto> produtos){
        this.produtos = produtos;
        pedidoAtivo = true;
    }

    /*Getters&Setters*/
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isPedidoAtivo() {
        return pedidoAtivo;
    }

    public void setPedidoAtivo(boolean pedidoAtivo) {
        this.pedidoAtivo = pedidoAtivo;
    }
}
