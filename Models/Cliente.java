package Models;

import java.util.ArrayList;

public class Cliente extends Usuario {
    private String endereco, fone;
    private Pedido p;
    private ArrayList<Pedido> histPedidos = new ArrayList<>();

    /* Construtores */
    public Cliente(String nome, String id, String email, String senha, String endereco, String fone) {
        super(nome, id, email, senha);
        this.endereco = endereco;
        this.fone = fone;
    }

    /* Getters&Setters */
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

    public Pedido getP() {
        return p;
    }

    public void setP(Pedido p) {
        this.p = p;
    }

    /* Métodos */
    public void mostrarHistoricoPedidos(Cliente cliente) {
        for (Pedido pedido : histPedidos) {
            System.out.println(pedido);
        }
    }

    public void adicionarAoHistorico(Pedido pedido) {
        histPedidos.add(pedido);
    }

    // Cardapio
    public static void limpaTela() {
        for (int i = 0; i < 20; ++i) {
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "| Cliente: " + super.getNome() + " (" + super.getEmail() + ")\n"
                + "| Endereço: " + endereco + ". Telefone: " + fone;
    }
}
