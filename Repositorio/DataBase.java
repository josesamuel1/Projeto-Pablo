package Repositorio;

import Models.*;

import java.util.ArrayList;

public class DataBase {
    private static ArrayList<Produto> produtos = new ArrayList<Produto>();
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private static ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

    /*Construtor*/
    // Padrão

    /*Getters&Setters*/
    public static ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public static void setProdutos(ArrayList<Produto> produtos) {
        DataBase.produtos = produtos;
    }

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

    /*Métodos*/

    // Mostrar cadastrados
    public void mostrarProdutos() {
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public void mostrarPedidos() {
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    public void mostrarUsuarios() {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    // Pesquisar
    public Produto pesquisarProduto(Produto produtoProcurado) {
        for (Produto produto : produtos) {
            if (produtoProcurado == produto) {
                System.out.println("Esse produto está cadastrado");
                return produto;
            }
        }
        return null;
    }

    public Usuario pesquisarUsuario(Usuario usuarioProcurado) {
        for (Usuario usuario : usuarios) {
            if (usuarioProcurado == usuario) {
                System.out.println("Esse cliente está cadastrado");
                return usuario;
            }
        }
        return null;
    }

    private Pedido pesquisarPedido(Pedido pedidoProcurado) {
        for (Pedido pedido : pedidos) {
            if (pedidoProcurado == pedido) {
                System.out.println("Models.Pedido cadastrado");
                return pedido;
            }
        }
        return null;
    }

    // Adicionar
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    public void adicionarPedido(Pedido pedido){
        pedidos.add(pedido);
    }
}

