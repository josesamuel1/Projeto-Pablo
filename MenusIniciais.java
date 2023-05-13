import Models.*;
import Repositorio.DataBase;

import java.io.IOException;
import java.util.Scanner;

public class MenusIniciais {
    private static Scanner input = new Scanner(System.in);

    public static void showMenuCliente(Usuario cliente) throws IOException, InterruptedException {
        System.out.println("Bem-vindo(a) " + cliente.getNome() + "!");
        int sair = 0;
        Pedido pedidoVez = new Pedido((Cliente) cliente);

        while (sair == 0) {
            System.out.print(UserInterface.getMenuCliente());
            String opcao = input.nextLine();
            limpaTela();
            switch (opcao) {
                // Ver cardapio
                case "1" -> {
                    DataBase.exibirCardapioDinamico();
                    System.out.println("Insira qualquer caractere para continuar.\n>>> ");
                    String xyz = input.nextLine();
                }

                /*--Fazer pedido--*/
                case "2" -> {
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
                            // Mostramos o sumario do pedido
                            limpaTela();
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

                                /*
                                 * Atualizamos o estoque com base no pedido e adicionamos ele
                                 * ao historico do cliente e ao banco de dados
                                 */
                                DataBase.atualizarEstoque(pedidoVez);
                                DataBase.adicionarPedido(pedidoVez);
                                ((Cliente) cliente).adicionarAoHistorico(pedidoVez);
                                loopPedido = false;
                            } else {
                                pedidoVez.esvaziarCarrinho();
                            }
                        } else if (option == -1 && pedidoVez.carrinhoEstaVazio()) { // Descartar pedido
                            loopPedido = false;
                        }
                    }
                }

                case "3" -> { // Pedir um kit pronto
                    System.out.println("CASO 3");
                }

                case "4" -> { // Pedir um kit personalizado
                    System.out.println("CASO 4");
                }

                case "5" -> { // Ver meus pedidos
                    pedidoVez.mostrarCarrinho();
                }

                case "6" -> { // Log Out
                    sair = -1;
                }
                default -> {
                    System.out.println("Opção não encontrada.");
                }
            }
        }
    }

    public static void showMenuAdministrador(Usuario administrador) throws IOException, InterruptedException {
        System.out.println("Bem-vindo(a) " + administrador.getNome() + "!");
        int sair = 0;

        while (sair == 0) {
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
                                System.out.print("Insira qualquer caractere para continuar.\n>>> ");
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
                    System.out.println("CASO 2");
                }

                // Gerenciar pedidos
                case "3" -> {
                    System.out.println("CASO 3");
                }

                // Gerenciar produtos
                case "4" -> {
                    boolean x = true;
                    while (x) {
                        System.out.println(UserInterface.getGerenciarProdutos());
                        System.out.print(">>> ");
                        int crudeProdutos = Integer.parseInt(input.nextLine());
                        switch (crudeProdutos) {
                            // CADASTRAR PRODUTOS
                            case (1) -> {
                                System.out.print("Quantos produtos deseja cadastrar? [0 para sair]\n>>> ");
                                int numCadastros = Integer.parseInt(input.nextLine());
                                if (numCadastros > 0) {
                                    for (int cadastroFeito = 0; cadastroFeito < numCadastros; cadastroFeito++) {
                                        Cadastro.cadastroProduto();
                                    }
                                }
                            }
                            // REMOVER PRODUTOS
                            case (2) -> {
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
                            case (3) -> {
                                System.out.println("Atualizar Produto");
                            }
                            // VOLTAR
                            case (4) -> {
                                x = false;
                            }
                        }
                    }
                }

                case "5" -> {
                    System.out.println("Histórico de pedidos");
                }
                case "6" -> {
                    sair = -1;
                }

                default -> {
                    System.out.println("Opção não encontrada.");
                }
            }
        }
    }

    public static void showMenuAtendente(Usuario atendente) throws IOException, InterruptedException {
        System.out.println("Bem-vindo(a) " + atendente.getNome() + "!");

        int sair = 0;
        while (sair == 0) {
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
        }
    }

    public static void limpaTela() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
