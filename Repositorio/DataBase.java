package Repositorio;

import Models.pedidos.*;
import Models.usuarios.*;

import java.util.*;

public class DataBase {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Atendente> atendentes = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();
    private static ArrayList<Combo> combos = new ArrayList<>();
    private static Map<Produto, Integer> estoqueProdutos = new LinkedHashMap<>();
    /* Construtor */
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

    public static ArrayList<Atendente> getAtendentes() {
        return atendentes;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static ArrayList<Combo> getCombos() {
        return combos;
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

    public static void atualizarProdutos(int id) {
        Scanner input = new Scanner(System.in);

        for (Map.Entry<Produto, Integer> produto : estoqueProdutos.entrySet()) {
            Produto p = produto.getKey();

            if (p.getId() == id) {
                System.out.print("Novo nome:\n>>> ");
                String novoNome = input.nextLine();
                p.setNome(novoNome);

                System.out.print("Novo tipo:\n>>> ");
                String novoTipo = input.nextLine();
                p.setTipo(novoTipo);

                System.out.print("Novo preço:\n>>> ");
                int novoPreco = Integer.parseInt(input.nextLine());
                p.setPreco(novoPreco);

                System.out.print("Nova quantidade:\n>>> ");
                Integer novaQntd = Integer.parseInt(input.nextLine());
                estoqueProdutos.put(p, novaQntd);

                System.out.println("Novo produto " + p.getNome() + " atualizado com sucesso.");
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

    public static int criaIdProduto(int possivelId) {
        for (Map.Entry<Produto, Integer> produto : estoqueProdutos.entrySet()) {
            if (produto.getKey().getId() == possivelId) {
                return criaIdProduto(possivelId + 1);
            }
        }
        return possivelId;
    }

    public static void atualizarEstoquePedidoConfirmado(Pedido pedido) {
        for (ItemDePedido item : pedido.getCarrinho()) {
            estoqueProdutos.put(item.getProduto(), (estoqueProdutos.get(item.getProduto())) - item.getQuantidade());
        }
    }

    public static void atualizarEstoquePedidoCancelado(Pedido pedido) {
        for (ItemDePedido item : pedido.getCarrinho()) {
            estoqueProdutos.put(item.getProduto(), (estoqueProdutos.get(item.getProduto())) + item.getQuantidade());
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

    public static void exibirProdutosCadastrados() {
        for (Map.Entry<Produto, Integer> produto : estoqueProdutos.entrySet()) {
            System.out.println(produto.getKey());
        }
    }

    public static Produto pegaProduto(int id) {
        if (DataBase.getProduto(id) != null) {
            return DataBase.getProduto(id);
        }
        return null;
    }

    // PEDIDOS
    public static void exibirCardapioDinamico() {
        for (Map.Entry<Produto, Integer> produto : estoqueProdutos.entrySet()) {
            if (verificaDisponibilidade(produto.getKey().getId())) {
                System.out.println(produto.getKey());
            }
        }
    }

    public static void exibirPedidos() {
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.getCarrinho() + "\n");
        }
    }

    private static Pedido pesquisarPedido(Pedido pedidoProcurado) {
        for (Pedido pedido : pedidos) {
            if (pedidoProcurado == pedido) {
                System.out.println("Models.pedidos.Pedido cadastrado");
                return pedido;
            }
        }
        return null;
    }

    public static void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // USUARIOS
    public static boolean listarClientes() {

        // Caso onde não há nenhum cliente, retorna false.
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return false;

            // Caso tenha alguém na lista de clientes, informa os valores e retona true.
        } else {
            // Informa a quantidade de clientes cadastrados.
            System.out.println("Clientes cadastrados: " + clientes.size());

            for (int i = 0; i < clientes.size(); i++) {
                // Informa o nome, email, contato e endereço do cliente.
                System.out.println("| Nome: " + clientes.get(i).getNome() +
                        " | Email: " + clientes.get(i).getEmail() +
                        " | Contato: " + clientes.get(i).getFone() +
                        " | Endereço: " + clientes.get(i).getEndereco() + "\n");
            }
            return true;
        }
    }

    public static void removerCliente(String email) {
        boolean userRemoved = true;

        // Caso não tenha nenhum cliente cadastrado.
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
        }

        // Caso tenha, vai procurá-lo para ser removido.
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getEmail().equals(email)) {
                // Caso seja removido.
                System.out.println(clientes.get(i).getNome() + " removido.");
                clientes.remove(i);
                userRemoved = true;
                break;

                // Caso não seja removido.
            } else {
                userRemoved = false;
            }
        }
        // Lógica que será executada apenas se o usuário não foi encontrado.
        if (!userRemoved) {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void atualizarCliente(String email) {
        Scanner input = new Scanner(System.in);
        boolean userFound = true;

        // Caso não tenha nenhum cliente cadastrado.
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
        }

        // Caso tenha, vai procurá-lo para ser editado.
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getEmail().equals(email)) {
                System.out.print("Novo nome:\n>>> ");
                String novoNome = input.nextLine();
                clientes.get(i).setNome(novoNome);

                System.out.print("Novo contato:\n>>> ");
                String novoCtt = input.nextLine();
                clientes.get(i).setFone(novoCtt);

                System.out.print("Novo endereço:\n>>> ");
                String novoEnd = input.nextLine();
                clientes.get(i).setEndereco(novoEnd);

                System.out.println(clientes.get(i).getNome() + " atualizado com sucesso.");
                userFound = true;
                break;

                // Caso não seja encontrado.
            } else {
                userFound = false;
            }
        }
        // Lógica que será executada apenas se o usuário não foi encontrado.
        if (!userFound) {
            System.out.println("Cliente não encontrado.");
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

    public static void mostrarListaPedidos(ArrayList<Pedido> pedidos) {
        for (Pedido p : pedidos) {
            System.out.println("+-------------------------------------------+");
            System.out.println(p);
        }
    }

    // Adicionar usuários nas respectivas listas
    public static void adicionarUsuario(Usuario user) {
        usuarios.add(user);
    }

    public static void adicionarAtendente(Atendente atd) {
        atendentes.add(atd);
        adicionarUsuario(atd);
    }

    public static void adicionarCliente(Cliente clt) {
        clientes.add(clt);
        adicionarUsuario(clt);
    }

    // FUNCIONARIOS
    public static boolean listarFuncionarios() {
        // Caso onde não há ninguém na lista de funcionários, retorna false.
        if (atendentes.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return false;
            // Caso tenha alguém na lista de funcionários, informa os valores e retorna
            // true.
        } else {
            // Informa a quantidade de funcionários cadastrados.
            System.out.println("Funcionários cadastrados: " + atendentes.size());
            for (int i = 0; i < atendentes.size(); i++) {
                // Informa o nome, email e cargo do funcionário.
                System.out.println("| Nome: " + atendentes.get(i).getNome() +
                        " | Email: " + atendentes.get(i).getEmail() +
                        " | Cargo: " + atendentes.get(i).getCargo() +
                        " | Salário do mês: R$" + atendentes.get(i).getSalario() + "\n");
            }
            return true;
        }
    }

    public static void removerFuncionario(String email) {
        boolean userRemoved = true;

        // Caso não tenha nenhum funcionário cadastrado.
        if (atendentes.isEmpty()) {
            System.out.println("Não há funcionários cadastrados.");
        }

        // Caso tenha, vai procurá-lo para ser removido.
        for (int i = 0; i < atendentes.size(); i++) {
            if (atendentes.get(i).getEmail().equals(email)) {
                // Caso seja removido.
                System.out.println(atendentes.get(i).getNome() + " removido.");
                atendentes.remove(i);
                userRemoved = true;
                break;

                // Caso não seja removido.
            } else {
                userRemoved = false;
            }
        }
        // Lógica que será executada apenas se o usuário não tenha sido encontrado.
        if (!userRemoved) {
            System.out.println("Funcionário não encontrado.");
        }
    }

    public static void recalcularSalario(String email, int horas) {
        // Caso não tenha nenhum funcionário cadastrado
        if (atendentes.isEmpty()) {
            System.out.println("Não há funcionários cadastrados.");
        }

        // Caso ache o felizardo, irá calcular sua hora extra.
        for (int i = 0; i < atendentes.size(); i++) {
            if (atendentes.get(i).getEmail().equals(email)) {
                atendentes.get(i).calcularHoraExtra(horas);
                break;
            }
        }
    }

    // COMBOS
    public static void adicionarCombo(Combo combo) {
        combos.add(combo);
    }

    public static void removerCombos(Combo combo) {
        combos.remove(combo);
    }

    public static int quantCombos() {
        return combos.size();
    }

    public static int checaCombo(ArrayList<Produto> carrinho) {
        Set<Produto> car = new HashSet<>(carrinho);
        for (Combo combo : combos) {
            Set<Produto> comboVez = new HashSet<>(combo.getProdutos());
            if (car.equals(comboVez)) {
                return combo.getPorcentagemDesconto();
            }
        }
        return -1;
    }

    public static boolean compoeCombo(ArrayList<Produto> produtos, Produto produto) {
        for (Produto p : produtos) {
            if (produto == p) {
                System.out.println("| Esse produto já faz parte desse combo.");
                return true;
            }
        }
        return false;
    }

    public static void exibirCombos(ArrayList<Combo> combos) {
        int i = 0;
        for (Combo combo : combos) {
            System.out.println("Combo " + (i + 1) + " | " + combo.getPorcentagemDesconto() + "% de desconto");
            i++;
            for (Produto produto : combo.getProdutos()) {
                System.out.println(produto);
            }
            System.out.println();
        }
    }

    // INSTANCIAÇÃO PADRÃO
    public static void cadastrosPadrao() {
        DataBase.adicionarUsuario(new Administrador("Maria", "adm", "maria@gmail.com", "senha1", "Administrador"));
        DataBase.adicionarAtendente(new Atendente("Alex", "atd", "alex@gmail.com", "senha2", "Atendente"));
        DataBase.adicionarCliente(new Cliente("Paula", "clt", "paula@gmail.com", "senha3", "Rua X", "83955555555"));
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
}
