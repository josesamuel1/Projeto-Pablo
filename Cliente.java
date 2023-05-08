import java.util.ArrayList;

public class Cliente extends Usuario {
    private String endereco, fone;
    private ArrayList<Produto> histPedidos;
    private ArrayList<Pedido> carrinho;

    public Cliente(String nome, String id, String email, String senha, String endereco, String fone) {
        super(nome, id, email, senha);
        this.endereco = endereco;
        this.fone = fone;
    }

    public void registrarPedido(Pedido p) {
        carrinho.add(p);
        System.out.println("Item adicionado ao carrinho.");
    }

    public void removerPedido() {
        System.out.println("Qual pedido deseja remover do carrinho?");

        for (int i = 0; i < carrinho.size(); i++) {
            System.out.println(carrinho.get(i).getProduto());
            System.out.println(carrinho.get(i).getQuantidade());
        }
    }

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

    public ArrayList<Produto> getHistPedidos() {
        return histPedidos;
    }

    public void setHistPedidos(ArrayList<Produto> histPedidos) {
        this.histPedidos = histPedidos;
    }

    public ArrayList<Pedido> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ArrayList<Pedido> carrinho) {
        this.carrinho = carrinho;
    }
}
