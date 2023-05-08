import java.util.ArrayList;

public class DataBase {
    private static ArrayList<Produto> produtosCadastrados = new ArrayList<Produto>();
    private static ArrayList<Usuario> usuariosCadastrados = new ArrayList<Usuario>();
    private static ArrayList<Pedido> pedidosCadastrados = new ArrayList<Pedido>();

    /*Construtor*/
    // Padrão

    /*Getters&Setters*/
    public static ArrayList<Produto> getProdutosCadastrados() {
        return produtosCadastrados;
    }

    public static void setProdutosCadastrados(ArrayList<Produto> produtosCadastrados) {
        DataBase.produtosCadastrados = produtosCadastrados;
    }

    public static ArrayList<Usuario> getusuariosCadastrados() {
        return usuariosCadastrados;
    }

    public static void setusuariosCadastrados(ArrayList<Usuario> usuariosCadastrados) {
        DataBase.usuariosCadastrados = usuariosCadastrados;
    }

    public static ArrayList<Pedido> getPedidos() {
        return pedidosCadastrados;
    }

    public static void setPedidos(ArrayList<Pedido> pedidos) {
        DataBase.pedidosCadastrados = pedidos;
    }

    /*Métodos*/
    // Mostrar cadastrados
    public void mostrarProdutosCadastrados(){
        for (Produto produto : produtosCadastrados){
            System.out.println(produto);
        }
    }
    public void mostrarPedidos(){
        for (Pedido pedido : pedidosCadastrados){
            System.out.println(pedido);
        }
    }
    public void mostrarusuariosCadastrados(){
        for(Usuario usuario : usuariosCadastrados){
            System.out.println(usuario);
        }
    }

    // Pesquisar
    public Produto pesquisarProduto(Produto produtoProcurado){
        for (Produto produto : produtosCadastrados){
            if (produtoProcurado == produto) {
                System.out.println("Esse produto está cadastrado");
                return produto;
            }
        }
        return null;
    }
    public Usuario pesquisarUsuario(Usuario usuarioProcurado){
        for (Usuario usuario : usuariosCadastrados){
            if (usuarioProcurado == usuario) {
                System.out.println("Esse cliente está cadastrado");
                return usuario;
            }
        }
        return null;
    }
    private Pedido pesquisarPedido(Pedido pedidoProcurado){
        for (Pedido pedido : pedidosCadastrados){
            if (pedidoProcurado == pedido){
                System.out.println("Pedido cadastrado");
                return pedido;
            }
        }
        return null;
    }
}
