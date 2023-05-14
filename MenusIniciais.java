import Enums.Status;
import Models.pedidos.Combo;
import Models.pedidos.ItemDePedido;
import Models.pedidos.Pedido;
import Models.pedidos.Produto;
import Models.usuarios.Cliente;
import Models.usuarios.Usuario;
import Repositorio.DataBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenusIniciais {
    private static Scanner input = new Scanner(System.in);

    public static void showMenuCliente(Usuario cliente) throws IOException, InterruptedException {
        System.out.println("Bem-vindo(a) " + cliente.getNome() + "!");
        int sair = 0;
        Pedido pedidoVez = new Pedido((Cliente) cliente);

        while (sair == 0) {
            try {
                System.out.print(UserInterface.getMenuCliente());
                String opcao = input.nextLine();
                limpaTela();
                switch (opcao) {
                    // Ver cardapio
                    case "1" -> {
                        DataBase.exibirCardapioDinamico();
                        System.out.print("Insira qualquer caractere para continuar.");
                        String xyz = input.nextLine();
                    }

                    case "2" -> { // Fazer pedido
                        pedidoVez.esvaziarCarrinho();
                        boolean loopPedido = true;
                        while (loopPedido) {
                            // Mostramos o Menu apenas com os produtos em estoque
                            DataBase.exibirCardapioDinamico();

                            // Captamos o id do produtos
                            System.out.print("Adicione um item ao carrinho. [-1 para confirmar]\nID: ");
                            int option = Integer.parseInt(input.nextLine());

                            // adicionar ao carrinho
                            if (option != -1) {
                                // Pegamos a referencia do Produto
                                Produto produtoEscolhido = DataBase.getProduto(option);

                                // A quantidade desejada
                                System.out.print("Quantidade: ");
                                int quantidade = Integer.parseInt(input.nextLine());

                                // Se o produto já estiver no carrinho, também levamos em consideração essa
                                // quantiddade
                                quantidade += pedidoVez.quantidadeProdutoCarrinho(produtoEscolhido);

                                limpaTela();
                                // Verificamos se a quantidade de deseja do produto escolhido está disponível em
                                // estoque
                                if (DataBase.verificaQuantidade(produtoEscolhido, quantidade)) {
                                    // Instanciamos e o item e o adicionamos ao carrinho
                                    pedidoVez.adicionarItemAoCarrinho(new ItemDePedido(produtoEscolhido, quantidade));
                                    System.out.println("| Produto adicionado");
                                } else {
                                    System.out.println("| Quantidade em estoque insuficiente");
                                }

                                pedidoVez.mostrarCarrinho();
                            }

                            // Sair
                            else if (option == -1) {
                                if (!pedidoVez.carrinhoEstaVazio())
                                    ; // Fechar pedido
                                limpaTela();

                                int desconto = DataBase.checaCombo(pedidoVez.getProdutosNoPedido());
                                if (desconto != -1) {
                                    double total = pedidoVez.total();
                                    double totalDescontado = ((double) desconto / 100) * total;
                                    System.out.println("| Desconto de combo aplicado: -R$" + totalDescontado);
                                    pedidoVez.setValorTotal(total - totalDescontado);
                                } else {
                                    pedidoVez.setValorTotal(pedidoVez.total());
                                }

                                System.out.println(pedidoVez.sumario());
                                pedidoVez.mostrarCarrinho();
                                // Caso deseje remover algum item do pedido -> remover até o usuário decidir
                                // sair

                                System.out.print("Deseja remover algum item? 1-Sim 2-Não\n>>> ");
                                int opt = Integer.parseInt(input.nextLine());
                                while (opt == 1) {
                                    opt = pedidoVez.editarCarrinho();
                                }
                                limpaTela();

                                // Mostramos o sumario do pedido
                                System.out.println(pedidoVez.sumario());
                                pedidoVez.mostrarCarrinho();

                                System.out.print("Gostaria de confirmar o pedido? 1-Sim 2-Não\n>>> ");
                                int confirmaPedido = Integer.parseInt(input.nextLine());
                                if (confirmaPedido == 1) {
                                    // Marcamos para retirada ou entrega
                                    System.out.println("1- Para entregar\n2- Para Retirar na loja.");
                                    int entregaOuRetirada = Integer.parseInt(input.nextLine());
                                    pedidoVez.entregaOuRetirada(entregaOuRetirada);
                                    System.out.println("Pedido Confirmado.");
                                    System.out.println("Tempo estimado de entrega: 1 hora\nMomento da entrega: "
                                            + pedidoVez.getData().plusHours(1).format(pedidoVez.getPadraoDiaHora()));
                                    /*
                                     * Atualizamos o estoque com base no pedido e adicionamos ele
                                     * ao historico do cliente e ao banco de dados
                                     */
                                    DataBase.atualizarEstoquePedidoConfirmado(pedidoVez);
                                    DataBase.adicionarPedido(pedidoVez);
                                    ((Cliente) cliente).adicionarAoHistorico(pedidoVez);
                                    loopPedido = false;
                                } else {
                                    System.out.println("Pedido descartado.");
                                    pedidoVez.esvaziarCarrinho();
                                }
                            } else if (option == -1 && pedidoVez.carrinhoEstaVazio()) { // Descartar pedido
                                loopPedido = false;
                            }
                        }
                    }

                    case "3" -> { // Ver meus pedidos ativos
                        while (true) {
                            ArrayList<Pedido> pedidos = ((Cliente) cliente).getHistPedidos();
                            if (!pedidos.isEmpty()) {
                                mostrarListaPedidosAtivos(pedidos);
                            } else {
                                System.out.println("Nenhum pedido cadastrado.");
                                break;
                            }

                            System.out.print("Deseja cancelar algum pedido?\n1 - Sim | 2 - Não\n>>> ");
                            int cancelarPedido = Integer.parseInt(input.nextLine());

                            if (cancelarPedido == 1) {
                                limpaTela();
                                mostrarListaPedidosAtivos(pedidos);
                                System.out.println("Selecione qual pedido deseja cancelar:\n>>> ");
                                int remover = Integer.parseInt(input.nextLine());
                                Pedido pedidoCancelado;

                                int i = 0;
                                for (Pedido p : pedidos) {
                                    if (remover == (i + 1)) {
                                        for (Pedido ped : DataBase.getPedidos()) {
                                            if (ped == p) {
                                                ped.setStatus(Enums.Status.valueOf("CANCELADO"));
                                                p.setStatus(Enums.Status.valueOf("CANCELADO"));
                                                DataBase.atualizarEstoquePedidoCancelado(ped);
                                                System.out.println("Pedido cancelado.");
                                                break;
                                            }
                                        }
                                    }
                                    i++;
                                }

                            } else if (cancelarPedido == 2) {
                                break;
                            }
                        }
                    }

                    case "4" -> { // Mostrar histórico de pedidos
                        DataBase.mostrarListaPedidos(((Cliente) cliente).getHistPedidos());
                    }

                    case "5" -> { // Log Out
                        sair = -1;
                    }

                    default -> {
                        System.out.println("Opção não encontrada.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Opção inválida.");
                continue;
            }
        }
    }

    public static void showMenuAdministrador(Usuario administrador) throws IOException, InterruptedException {
        System.out.println("Bem-vindo(a) " + administrador.getNome() + "!");
        int sair = 0;

        while (sair == 0) {
            try {
                System.out.print(UserInterface.getMenuAdministrador());
                String opcao = input.nextLine();

                switch (opcao) {
                    // Gerenciar funcionarios
                    case "1" -> {
                        int finish = 0;

                        while (finish == 0) {
                            System.out.print(UserInterface.getGerenciarFuncionarios());
                            int escolha = Integer.parseInt(input.nextLine());

                            if (escolha == 1) { // Listar funcionários
                                if (DataBase.listarFuncionarios()) {
                                    System.out.print("Insira qualquer caractere para continuar.");
                                    String xyz = input.nextLine();
                                }

                            } else if (escolha == 2) { // Cadastrar novo funcionário
                                Cadastro.showCadastroFuncionario();

                            } else if (escolha == 3) { // Remover funcionário
                                if (DataBase.listarFuncionarios()) {
                                    System.out.print("Insira o email do funcionário para removê-lo.\n>>> ");
                                    String email = input.nextLine();

                                    DataBase.removerFuncionario(email);
                                }

                            } else if (escolha == 4) { // Calcular salário
                                if (DataBase.listarFuncionarios()) {
                                    System.out.print("Insira o email do funcionário.\n>>> ");
                                    String email = input.nextLine();

                                    System.out.print("Insira as horas trabalhadas.\n>>> ");
                                    int horas = Integer.parseInt(input.nextLine());

                                    DataBase.recalcularSalario(email, horas);
                                }

                            } else if (escolha == 5) { // Voltar
                                finish = -1;

                            } else {
                                System.out.println("Opção não encontrada.");
                            }
                        }
                    }

                    // Gerenciar clientes
                    case "2" -> {
                        int finish = 0;

                        while (finish == 0) {
                            System.out.print(UserInterface.getGerenciarClientes());
                            int escolha = Integer.parseInt(input.nextLine());

                            if (escolha == 1) { // Listar clientes
                                if (DataBase.listarClientes()) {
                                    System.out.print("Insira qualquer caractere para continuar.");
                                    String xyz = input.nextLine();
                                }

                            } else if (escolha == 2) { // Cadastrar novo cliente
                                Cadastro.showCadastroCliente();

                            } else if (escolha == 3) { // Remover cliente
                                if (DataBase.listarClientes()) {
                                    System.out.print("Insira o email do cliente para removê-lo.\n>>> ");
                                    String email = input.nextLine();

                                    DataBase.removerCliente(email);
                                }

                            } else if (escolha == 4) { // Atualizar cliente
                                if (DataBase.listarClientes()) {
                                    System.out.print("Insira o email do cliente.\n>>> ");
                                    String email = input.nextLine();

                                    DataBase.atualizarCliente(email);
                                }

                            } else if (escolha == 5) { // Voltar
                                finish = -1;

                            } else {
                                System.out.println("Opção não encontrada.");
                            }
                        }
                    }

                    // Gerenciar pedidos
                    case "3" -> {
                        mostrarListaPedidosAtivos(DataBase.getPedidos());
                    }

                    // Gerenciar produtos
                    case "4" -> {
                        boolean x = true;
                        while (x) {
                            System.out.println(UserInterface.getGerenciarEstoque());
                            System.out.print(">>> ");
                            int crudeProdutos = Integer.parseInt(input.nextLine());
                            switch (crudeProdutos) {
                                case (1) -> {
                                    DataBase.exibirEstoque();
                                }
                                // CADASTRAR PRODUTOS
                                case (2) -> {
                                    System.out.print("Quantos produtos deseja cadastrar? [0 para sair]\n>>> ");
                                    int numCadastros = Integer.parseInt(input.nextLine());
                                    if (numCadastros > 0) {
                                        for (int cadastroFeito = 0; cadastroFeito < numCadastros; cadastroFeito++) {
                                            Cadastro.cadastroProduto();
                                        }
                                    }
                                }

                                // REMOVER PRODUTOS
                                case (3) -> {
                                    int y = 0;
                                    while (y != -1) {
                                        DataBase.exibirEstoque();
                                        System.out.println();
                                        System.out.print(
                                                "Escolha o ID do produto que deseja remover [-1 para concluir]\n>>> ");
                                        int id = Integer.parseInt(input.nextLine());
                                        if (id == -1) {
                                            y = id;
                                        } else {
                                            DataBase.removerDoEstoque(id);
                                        }
                                    }
                                }

                                // ATUALIZAR PRODUTOS
                                case (4) -> {
                                    int y = 0;

                                    while (y == 0) {
                                        DataBase.exibirEstoque();
                                        System.out.println();
                                        System.out.print(
                                                "Escolha o ID do produto que deseja atualizar [-1 para concluir]\n>>> ");
                                        int id = Integer.parseInt(input.nextLine());
                                        if (id == -1) {
                                            y = id;
                                        } else {
                                            DataBase.atualizarProdutos(id);
                                        }
                                    }
                                }
                                // VOLTAR
                                case (5) -> {
                                    x = false;
                                }
                            }
                        }
                    }

                    // Gerenciar combos
                    case "5" -> {
                        boolean on = true;
                        while (on) {
                            System.out.print(UserInterface.getGerenciarCombos());
                            int opt = Integer.parseInt(input.nextLine());
                            switch (opt) {
                                // Exibir combos
                                case (1) -> {
                                    if (DataBase.quantCombos() == 0) {
                                        System.out.println("Nenhum combo criado.");
                                    } else {
                                        exibirCombos(DataBase.getCombos());
                                    }
                                }
                                // Criar combo
                                case (2) -> {
                                    // Cria lista de produtos que compõe o combo
                                    ArrayList<Produto> produtos = new ArrayList<>();
                                    while (true) {
                                        DataBase.exibirProdutosCadastrados();
                                        System.out.println();
                                        System.out.println("| Produtos no combo");
                                        for (Produto produto : produtos) {
                                            System.out.println(produto);
                                        }
                                        System.out.print(
                                                "Escolha o ID do produto para compor o combo. [-1 para concluir]\n>>> ");
                                        int id = Integer.parseInt(input.nextLine());
                                        // Adicionar ao combo
                                        if (id != -1) {
                                            Produto produto = DataBase.pegaProduto(id);
                                            if (!compoeCombo(produtos, produto)) {
                                                produtos.add(produto);
                                                limpaTela();
                                                System.out.println("| Adicionado");
                                            } else if (produto == null) {
                                                limpaTela();
                                                System.out.println("| Produto não encontrado.");
                                            }
                                        } else if (id == -1 && !produtos.isEmpty()) {
                                            // Adicionar porcentagem de desconto
                                            System.out.print(
                                                    "Qual a porcentagem de desconto desse combo? [-1 para sair]\n>>> ");
                                            int porcentagem = Integer.parseInt(input.nextLine());
                                            if (porcentagem == -1) {
                                                break;
                                            }
                                            // Salvar no banco
                                            DataBase.adicionarCombo(new Combo(produtos, porcentagem));
                                            System.out.println("| Combo criado com sucesso");
                                            System.out.println("| Continuar criando combos?\n1- Sim\n2- Não");
                                            int continuar = Integer.parseInt(input.nextLine());
                                            if (continuar == 2) {
                                                break;
                                            }
                                            produtos.clear();
                                        }
                                        // Descartar
                                        else {
                                            break;
                                        }
                                    }
                                }

                                // Remover combo
                                case (3) -> {
                                    while (true) {
                                        DataBase.exibirCombosEnumerados();
                                        System.out.print(
                                                "Qual o número do combo que deseja excluir? [-1 para sair]\n>>> ");
                                        int removerCombo = Integer.parseInt(input.nextLine());
                                        if (removerCombo == -1) {
                                            break;
                                        } else {
                                            for (int i = 0; i < DataBase.quantCombos(); i++) {
                                                if (removerCombo == (i + 1)) {
                                                    DataBase.removerCombos(DataBase.getCombos().get(i));
                                                    System.out.println("| Combo removido.");
                                                }
                                            }
                                        }
                                    }
                                }
                                // Voltar
                                case (4) -> {
                                    on = false;
                                }
                            }
                        }
                    }

                    case "6" -> {
                        DataBase.mostrarListaPedidos(DataBase.getPedidos());
                    }
                    case "7" -> {
                        sair = -1;
                    }

                    default -> {
                        System.out.println("Opção não encontrada.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Opção inválida.");
            }
        }
    }

    public static void showMenuAtendente(Usuario atendente) throws IOException, InterruptedException {
        System.out.println("Bem-vindo(a) " + atendente.getNome() + "!");

        int sair = 0;
        while (sair == 0) {
            try {
                System.out.print(UserInterface.getMenuAtendente());
                String opcao = input.nextLine();

                switch (opcao) {
                    case "1" -> { // Criar novo pedido
                        System.out.println("CASO 1");
                    }

                    case "2" -> { // Gerenciar pedidos
                        System.out.println("CASO 2");
                    }

                    case "3" -> { // Histórico de pedidos
                        System.out.println("CASO 3");
                    }

                    case "4" -> { // Gerenciar cliente
                        System.out.println("CASO 4");
                    }

                    case "5" -> { // Log Out
                        sair = -1;
                    }

                    default -> {
                        System.out.println("Opção não encontrada.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Opção inválida.");
            }
        }
    }

    public static void limpaTela() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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

    public static void mostrarListaPedidosAtivos(ArrayList<Pedido> pedidos) {
        int i = 0;
        for (Pedido p : pedidos) {
            System.out.println("Pedido " + (i + 1));
            i++;
            Status statusPedido = p.getStatus();
            if (statusPedido == Status.ACEITO || statusPedido == Status.valueOf("PENDENTE")
                    || statusPedido == Status.PRODUCAO
                    || statusPedido == Status.PRONTO || statusPedido == Status.ENVIADO) {
                System.out.println(p);
            }
        }
    }
}
