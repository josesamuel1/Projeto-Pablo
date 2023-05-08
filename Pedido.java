import java.util.Date;

public class Pedido {
    private Produto produto;
    private int quantidade;
    private Date data;
    private boolean pedidoAtivo;

    /*Construtor*/
    public Pedido(){}
    public Pedido(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
        pedidoAtivo = true;
    }

    /*Getters&Setters*/
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
}
