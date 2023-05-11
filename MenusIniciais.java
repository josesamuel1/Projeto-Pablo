import Models.*;

import java.util.Scanner;

public class MenusIniciais {
    private static Scanner input = new Scanner(System.in);

    public static void showMenuCliente(Usuario user) {
        int sair = 0;
        int qntd = 0;
        String xyz, escolha;

        while (sair == 0) {
            System.out.print(UserInterface.getMenuCliente());
            String opcao = input.nextLine();

            switch (opcao) {
                case "1" -> { // Ver cardapio
                    UserInterface.getCardapioCliente();
                    System.out.print("Insira qualquer caractere para sair.");
                    xyz = input.nextLine();
                }

                case "2" -> { // Fazer pedido
                    //
                    // Ainda em produção
                    //
                    int finish = 0;

                    while (finish == 0) {
                        UserInterface.getCardapioCliente();
                        escolha = input.nextLine();

                        System.out.print("Quantidade: ");
                        qntd = input.nextInt();

                        System.out.println(
                                "Item adicionado ao carrinho, deseja adicionar algo a mais?\n1 - Sim | 2 - Não\n>>> ");
                        opcao = input.nextLine();

                        if (opcao == "1") {
                            continue;
                        } else if (opcao == "2") {
                            finish = -1;
                        } else {
                            System.out.println("Opção não encontrada.");
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
                    System.out.println("CASO 5");
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

    public static void showMenuAdministrador(Usuario user) {
        int sair = 0;
        String escolha;

        while (sair == 0) {
            System.out.print(UserInterface.getMenuAdministrador());
            String opcao = input.nextLine();

            switch (opcao) {
                case "1" -> { // Gerenciar funcionarios >> Cadastrar, remover, calcular salário
                    //
                    // Ainda em produção
                    //
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

                case "2" -> { // Gerenciar clientes >> ---
                    System.out.println("CASO 2");
                }

                case "3" -> { // Gerenciar pedidos >> Ver pedidos ativos, cancelar pedidos, mudar status do
                              // pedido
                    System.out.println("CASO 3");
                }

                case "4" -> { // Histórico de Pedidos
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

    public static void showMenuAtendente(Usuario user) {
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
}
