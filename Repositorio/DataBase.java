package Repositorio;

import Models.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class DataBase {
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private static ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    private static Map<Produto, Integer> estoqueProdutos = new LinkedHashMap<>();
    /*Construtor*/
    // Padrão
    /* Getters&Setters */
    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(ArrayList<Usuario> usuarios) {
        DataBase.usuarios = usuarios;
    }

    public static ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public static void setPedidos(ArrayList<Pedido> pedidos) {
        DataBase.pedidos = pedidos;
    }

    public static Map<Produto, Integer> getEstoqueProdutos() {
        return estoqueProdutos;
    }
    /* Métodos */

    // ESTOQUE&PRODUTOS
    public static void adicionarAoEstoque(Produto produto, int quantidade) {
        estoqueProdutos.put(produto, quantidade);
    }
    public static void removerDoEstoque(int id) {
        for (Map.Entry<Produto, Integer> produto : estoqueProdutos.entrySet()) {
            Produto p = produto.getKey();
            if (p.getId() == id) {
                estoqueProdutos.remove(p);
                System.out.println(produto.getKey() + " removido.");
                return;
            }
        }
        System.out.println("ID não encontrado.");
    }
    public static void exibirEstoque() {
        for (Map.Entry<Produto, Integer> produto : estoqueProdutos.entrySet()) {
            System.out.println(produto.getKey() + " Quantidade em estoque: " + produto.getValue());
        }
    }
    public static int tamanhoEstoque() {
        return estoqueProdutos.size();
    }
    public static boolean verificaDisponibilidade(int id) {
        for (Map.Entry<Produto, Integer> produto : DataBase.getEstoqueProdutos().entrySet()) {
            if (produto.getKey().getId() == id && produto.getValue() > 0) {
                return true;
            }
        }
        return false;
    }
    public static void estoquePadrao() {
        DataBase.adicionarAoEstoque(new Produto("Pipoca", "Caramelo", 8.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Pipoca", "Manteiga", 5.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Algodão doce", "Morango", 2.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Algodão doce", "Laranja", 2.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Refrigerante", "Coca-cola", 10.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Refrigerante", "Fanta Uva", 10.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Torta", "Chocolate", 50.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Torta", "Frango", 45.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Balão", "Festa", 10.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Balão", "Formato coração", 12.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Toalha de mesa", "Estampa Batman", 12.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Toalha de mesa", "Sem estampa", 10.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Vela", "Estrelinha", 8.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Vela", "Cometa", 10.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Descartável", "Colorido", 10.0), 3);
        DataBase.adicionarAoEstoque(new Produto("Descartavel", "Branco", 10.0), 3);
    }
    public static int criaIdProduto(int possivelId) {
        for (Map.Entry<Produto, Integer> produto : estoqueProdutos.entrySet()) {
            if (produto.getKey().getId() == possivelId) {
                return criaIdProduto(possivelId + 1);
            }
        }
        return possivelId;
    }
    public static void exibirCardapioDinamico() {
        for (Map.Entry<Produto, Integer> produto : estoqueProdutos.entrySet()) {
            if (verificaDisponibilidade(produto.getKey().getId())) {
                System.out.println(produto.getKey());
            }
        }
    }
    public static void atualizarEstoque(Pedido pedido){
        for (ItemDePedido item: pedido.getCarrinho()){
            estoqueProdutos.put(item.getProduto(), (estoqueProdutos.get(item.getProduto())) - item.getQuantidade());
        }
    }

    public static Produto getProduto(int id) {
        for (Map.Entry<Produto, Integer> produto : DataBase.getEstoqueProdutos().entrySet()) {
            if (produto.getKey().getId() == id) {
                return produto.getKey();
            }
        }
        return null;
    }

    public static boolean verificaQuantidade(Produto produto, int quantidade) {
        for (Map.Entry<Produto, Integer> p : estoqueProdutos.entrySet()) {
            if (p.getKey() == produto && quantidade <= p.getValue()) {
                return true;
            }
        }
        return false;
    }


    // PEDIDOS
    public static void exibirPedidos() {
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    private static Pedido pesquisarPedido(Pedido pedidoProcurado) {
        for (Pedido pedido : pedidos) {
            if (pedidoProcurado == pedido) {
                System.out.println("Models.Pedido cadastrado");
                return pedido;
            }
        }
        return null;
    }

    public static void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // USUARIOS
    public static void exibirUsuarios() {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    public static Usuario pesquisarUsuario(Usuario usuarioProcurado) {
        for (Usuario usuario : usuarios) {
            if (usuarioProcurado == usuario) {
                System.out.println("Esse cliente está cadastrado.");
                return usuario;
            }
        }
        return null;
    }

    public static void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static void cadastrosPadrao() {
        DataBase.adicionarUsuario(new Administrador("Maria", "adm", "maria@gmail.com", "senha123", "Administrador"));
        DataBase.adicionarUsuario(new Atendente("Alex", "atd", "alex@gmail.com", "senha321", "Atendente"));
        DataBase.adicionarUsuario(new Cliente("Paula", "clt", "paula@gmail.com", "123senha", "Rua ruazinha", "83955555555"));
    }
}
