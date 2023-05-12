import Models.*;
import Repositorio.DataBase;

import java.util.Scanner;

public class MenusIniciais {
    private static Scanner input = new Scanner(System.in);

    public static void showMenuCliente(Usuario cliente) {
        System.out.println("Bem-vindo(a) " + cliente.getNome() + "!");
        int sair = 0;
        while (sair == 0) {
            System.out.print(UserInterface.getMenuCliente());
            String opcao = input.nextLine();
            limpaTela();
            switch (opcao) {
                // Ver cardapio
                case "1" -> {
                    DataBase.exibirCardapioDinamico();
                }


                /*--Fazer pedido--*/
                case "2" -> {
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

                            // Se o produto já estiver no carrinho, também levamos em consideração essa quantiddade
                            quantidade += pedidoVez.quantidadeProdutoCarrinho(produtoEscolhido);

                            limpaTela();
                            // Verificamos se a quantidade de deseja do produto escolhido está disponível em estoque
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
                            if (!pedidoVez.carrinhoEstaVazio()) ; // Fechar pedido
                            //Mostramos o sumario do pedido
                            limpaTela();
                            System.out.println(pedidoVez.sumario());
                            pedidoVez.mostrarCarrinho();
                            // Caso deseje remover algum item do pedido -> remover até o usuário decidir sair

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

                                /*Atualizamos o estoque com base no pedido e adicionamos ele
                                ao historico do cliente e ao banco de dados*/
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
                } case "3" -> { //PEDIR UM KIT PRONTO
                    System.out.println("CASO 3");
                }

                // Pedir um kit personalizado
                case "4" -> {
                    System.out.println("CASO 4");
                }

                // Ver meus pedidos
                case "5" -> {
                    System.out.println("CASO 5");
                }

                // Log Out
                case "6" -> {
                    sair = -1;
                }
                default -> {
                    System.out.println("Opção não encontrada.");
                }
            }
        }

    }

    public static void showMenuAdministrador(Usuario administrador) {
        System.out.println("Bem-vindo(a) " + administrador.getNome() + "!");
        int sair = 0;
        String escolha;

        while (sair == 0) {
            System.out.print(UserInterface.getMenuAdministrador());
            String opcao = input.nextLine();

            switch (opcao) {
                // Gerenciar funcionarios
                case "1" -> {
                    int finish = 0;
                    while (finish == 0) {
                        UserInterface.getGerenciarFuncionarios();
                        escolha = input.nextLine();

                        if (escolha == "1") { // Cadastrar novo funcionário
                            System.out.println("OPCAO 1");
                        } else if (escolha == "2") { // Remover funcionário
                            System.out.println("OPCAO 2");
                            ;
                        } else if (escolha == "3") { // Calcular salário
                            System.out.println("OPCAO 3");
                        } else if (escolha == "4") { // Voltar
                            finish = -1;
                        } else {
                            System.out.println("Opção não encontrada.");
                        }
                    }
                }

                // Gerenciar clientes
                case "2" -> { // Gerenciar clientes >> ---
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
                                    System.out.print("Escolha o ID do produto que deseja remover [-1 para concluir]\n>>> ");
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

    public static void showMenuAtendente(Usuario atendente) {
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


    public static void limpaTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

}
