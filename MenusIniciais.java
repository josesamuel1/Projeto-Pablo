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
        while (sair == 0) {
            try {
                System.out.print(UserInterface.getMenuCliente());
                String opcao = input.nextLine();
                limpaTela();
                switch (opcao) {
                    // Ver cardapio
                    case "1" -> {
                        limpaTela();
                        DataBase.exibirCardapioDinamico();
                        System.out.print("Insira qualquer caractere para continuar.");
                        String xyz = input.nextLine();
                    }

                    case "2" -> { // Fazer pedido
                        limpaTela();
                        Pedido pedidoVez = new Pedido((Cliente) cliente);
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
                                System.out.println("| Total: R$" + pedidoVez.total());
                            }

                            // Sair
                            else if (option == -1) {
                                if (!pedidoVez.carrinhoEstaVazio()) {
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
                                    System.out.println("Total a pagar: R$" + pedidoVez.total());
                                    // Caso deseje remover algum item do pedido -> remover até o usuário decidir
                                    // sair
                                    System.out.println();
                                    System.out.print("Deseja remover algum item?\n1-Sim\n2-Não\n>>> ");
                                    int opt = Integer.parseInt(input.nextLine());
                                    while (opt == 1) {
                                        opt = pedidoVez.editarCarrinho();
                                    }
                                    limpaTela();

                                    // Mostramos o sumario do pedido
                                    System.out.println(pedidoVez.sumario());
                                    pedidoVez.mostrarCarrinho();

                                    System.out.print("Gostaria de confirmar o pedido?\n1-Sim\n2-Não\n>>> ");
                                    int confirmaPedido = Integer.parseInt(input.nextLine());
                                    if (confirmaPedido == 1) {
                                        // Marcamos para retirada ou entrega
                                        System.out.print("1- Para entregar\n2- Para Retirar na loja.\n>>> ");
                                        int entregaOuRetirada = Integer.parseInt(input.nextLine());

                                        if (entregaOuRetirada == 1) {
                                            pedidoVez.setMarcadoParaEntrega(true);
                                        } else {
                                            System.out.println("Opção não encontrada");
                                        }

                                        limpaTela();
                                        if (pedidoVez.isMarcadoParaEntrega()) {
                                            System.out.println("Tempo estimado de entrega: 1 hora\nMomento da entrega: "
                                                               + pedidoVez.getData().plusHours(1)
                                                                       .format(pedidoVez.getPadraoDiaHora()));
                                        }

                                        System.out.println("Pedido Confirmado.");
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
                                System.out.print("Selecione qual pedido deseja cancelar:\n>>> ");
                                int remover = Integer.parseInt(input.nextLine());
                                Pedido pedidoCancelado;

                                int i = 0;
                                for (Pedido p : pedidos) {
                                    if (remover == i) {
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
                        ((Cliente) cliente).mostrarHistoricoPedidos();
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
                limpaTela();
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
                                    limpaTela();
                                    System.out.println("Funcionario removido");
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
                        while (true) {
                            ArrayList<Pedido> pedidos = DataBase.getPedidos();
                            if (!pedidos.isEmpty()) {
                                mostrarListaPedidosAtivos(pedidos);
                            } else {
                                System.out.println("Nenhum pedido cadastrado.");
                                break;
                            }
                            System.out.println();
                            System.out.print("| Deseja atualizar o status de algum pedido?\n| 1- Sim\n| 2- Não\n>>> ");
                            int atualizar = Integer.parseInt(input.nextLine());

                            if (atualizar == 1) {
                                limpaTela();
                                mostrarListaPedidosAtivos(pedidos);
                                System.out.print("Selecione qual pedido deseja atualizar. [-1 para sair]\n>>> ");
                                int p = Integer.parseInt(input.nextLine());

                                if (p == -1) {
                                    break;
                                }

                                for (int i = 0; i < pedidos.size(); i++) {
                                    Pedido pedidoVez = pedidos.get(i);
                                    System.out.println("Pedido " + i);
                                    if (p == i) {
                                        if (!pedidoVez.isMarcadoParaEntrega()) {
                                            System.out.print(UserInterface.getMudarStatusRetirada());
                                        } else {
                                            System.out.print(UserInterface.getMudarStatusEntrega());
                                        }
                                        int op = Integer.parseInt(input.nextLine());
                                        limpaTela();
                                        pedidoVez.atualizaStatus(pedidoVez, op);
                                        System.out.println("| Status do pedido atualizado.");
                                        System.out.println();
                                    } else {
                                        limpaTela();
                                        System.out.println("| Pedido não encontrado");
                                        System.out.println();
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                    }

                    // Gerenciar produtos
                    case "4" -> {
                        boolean x = true;
                        while (x) {
                            System.out.println(UserInterface.getGerenciarEstoque());
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
                                        DataBase.exibirCombos(DataBase.getCombos());
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
                                            if (!DataBase.compoeCombo(produtos, produto)) {
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
                                        DataBase.exibirCombos(DataBase.getCombos());
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

                    // Historico de pedidos
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
                continue;
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
                    case "1" -> { // Gerenciar pedidos
                        while (true) {
                            limpaTela();
                            ArrayList<Pedido> pedidos = DataBase.getPedidos();
                            if (!pedidos.isEmpty()) {
                                mostrarListaPedidosAtivos(pedidos);
                            } else {
                                System.out.println("Nenhum pedido cadastrado.");
                                break;
                            }
                            System.out.println();
                            System.out.print("| Deseja atualizar o status de algum pedido?\n| 1- Sim\n| 2- Não\n>>> ");
                            int atualizar = Integer.parseInt(input.nextLine());

                            if (atualizar == 1) {
                                limpaTela();
                                mostrarListaPedidosAtivos(pedidos);
                                System.out.print("Selecione qual pedido deseja atualizar. [-1 para sair]\n>>> ");
                                int p = Integer.parseInt(input.nextLine());

                                if (p == -1) {
                                    break;
                                }

                                for (int i = 0; i < pedidos.size(); i++) {
                                    Pedido pedidoVez = pedidos.get(i);
                                    System.out.println("Pedido " + i);
                                    if (p == i) {
                                        if (!pedidoVez.isMarcadoParaEntrega()) {
                                            System.out.print(UserInterface.getMudarStatusRetirada());
                                        } else {
                                            System.out.print(UserInterface.getMudarStatusEntrega());
                                        }
                                        int op = Integer.parseInt(input.nextLine());
                                        pedidoVez.atualizaStatus(pedidoVez, op);
                                        System.out.println("Status do pedido atualizado.");
                                    } else {
                                        System.out.println("Pedido não encontrado");
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                    }

                    case "2" -> { // Histórico de pedidos
                        DataBase.mostrarListaPedidos(DataBase.getPedidos());
                    }

                    case "3" -> { // Gerenciar cliente
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

                    case "4" -> { // Log Out
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

    public static void limpaTela() throws IOException, InterruptedException {
        for (int i = 0; i < 25; i++){
            System.out.println();
        }
    }

    public static void mostrarListaPedidosAtivos(ArrayList<Pedido> pedidos) {
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            Status statusPedido = p.getStatus();
            if (statusPedido == Status.ACEITO || statusPedido == Status.PENDENTE
                || statusPedido == Status.PRONTO) {
                System.out.println("Pedido " + i);
                System.out.println(p);
            }
        }
    }
}
