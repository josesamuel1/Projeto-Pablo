package Models;


import Enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {
    private ArrayList<Produto> produtos;
    private LocalDateTime data;
    private Status status;

    /*Construtor*/
    public Pedido(ArrayList<Produto> produtos){
        this.produtos = produtos;
    }
    public Pedido(Produto produto, int quantidade, ArrayList<Produto> produtos){
        this.produtos = produtos;
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

    public Status getStatus() {
        return status;
    }
}
