package Models.pedidos;

import Enums.Status;
import Models.usuarios.Cliente;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Pedido {
    private ArrayList<ItemDePedido> carrinho = new ArrayList<>();
    private Cliente cliente;
    private LocalDateTime data = LocalDateTime.now();
    private DateTimeFormatter padraoDiaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private boolean marcadoParaEntrega;
    private Status status;
    private double valorTotal;

    /* Construtores */
    public Pedido() {
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.status = Enums.Status.valueOf("PENDENTE");
    }

    /* Getters&Setters */
    public Cliente getCliete() {
        return cliente;
    }

    public void setCliete(Cliente cliete) {
        this.cliente = cliete;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public ArrayList<ItemDePedido> getCarrinho() {
        return carrinho;
    }

    public ArrayList<Produto> getProdutosNoPedido() {
        ArrayList<Produto> produtosNoPedido = new ArrayList<>();
        for (ItemDePedido itemDePedido : carrinho) {
            produtosNoPedido.add(itemDePedido.getProduto());
        }
        return produtosNoPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DateTimeFormatter getPadraoDiaHora() {
        return padraoDiaHora;
    }

    /* Métodos */

    // Referentes a atualização do carrinho
    public void adicionarItemAoCarrinho(ItemDePedido item) {
        carrinho.add(item);
    }

    public void removerItem(ItemDePedido item) {
        carrinho.remove(item);
    }

    public boolean carrinhoEstaVazio() {
        return carrinho.isEmpty();
    }

    public void mostrarCarrinho() {
        if (carrinhoEstaVazio()) {
            System.out.println("O carrinho está vazio.");
        } else {
            System.out.println("| Carrinho");
            for (int i = 0; i < carrinho.size(); i++) {
                System.out.println("| Item número " + i + " | " + carrinho.get(i));
            }
        }
    }

    public int quantidadeProdutoCarrinho(Produto produto) {
        int soma = 0;
        for (ItemDePedido item : carrinho) {
            if (item.getProduto() == produto) {
                soma += item.getQuantidade();
            }
        }
        return soma;
    }

    public int editarCarrinho() {
        Scanner sc = new Scanner(System.in);
        mostrarCarrinho();
        System.out.print("Que item deseja remover? [-1 para sair]\n>>> ");
        int option = Integer.parseInt(sc.nextLine());
        if (option == -1) {
            return 0;
        }
        for (int i = 0; i < carrinho.size(); i++) {
            if (i == option) {
                carrinho.remove(i);
            }
        }
        return 1;
    }

    public void esvaziarCarrinho() {
        carrinho.clear();
    }

    public void fecharPedido() {
    }

    // Referentes a lógica do pedido
    public String sumario() {
        return cliente.toString() + "\n" + this.toString() + "\n";
    }

    public double total() {
        double soma = 0.0;
        for (ItemDePedido item : carrinho) {
            soma += item.getSubtotal();
        }
        return soma;
    }

    public void entregaOuRetirada(int opt) {
        if (opt == 1) {
            marcarParaEntrega();
        } else if (opt == 2) {
            marcarParaRetirada();
        }
    }

    public void marcarParaEntrega() {
        this.marcadoParaEntrega = true;
    }

    public void marcarParaRetirada() {
        this.marcadoParaEntrega = true;
    }

    public void aplicarCombo(int desconto) {

    }

    public String toString() {
        return "| Data e hora do pedido: " + data.format(padraoDiaHora) + "\n" + "| Status do pedido: "
                + status.getStatus() + "\n" + "| Total a pagar: R$" + valorTotal;
    }

}
