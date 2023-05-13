package Models;

public class ItemDePedido {
    private Produto produto;
    private int quantidade;
    private double subtotal = 0.0;
    private double total = 0.0;

    /* Construtores */
    public ItemDePedido() {
    }

    public ItemDePedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.getPreco() * quantidade;
        this.total += this.subtotal;
    }

    /* Getters&Setters */
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total + getSubtotal();
    }

    /* Métodos */
    @Override
    public String toString() {
        return produto.toString() + "| Quantidade: " + quantidade + " | Subtotal: R$" + subtotal;
    }
}
