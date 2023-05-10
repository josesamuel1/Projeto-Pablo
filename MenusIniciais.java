import Models.*;

import java.util.Scanner;

public class MenusIniciais {
    private static Scanner input = new Scanner(System.in);

    public static void showMenuCliente(Usuario user) {
        int sair = 0;

        while (sair == 0) {
            System.out.print(UserInterface.getMenuCliente());
            String opcao = input.nextLine();

            switch (opcao) {
                case "1" -> { // Ver cardapio
                    System.out.println("CASO 1");
                }

                case "2" -> { // Fazer pedido
                    System.out.println("CASO 2");
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

        while (sair == 0) {
            System.out.print(UserInterface.getMenuAdministrador());
            String opcao = input.nextLine();

            switch (opcao) {
                case "1" -> { // Gerenciar funcionarios >> Cadastrar, remover, calcular salário
                    System.out.println("CASO 1");
                }

                case "2" -> {
                    System.out.println("CASO 2"); // Gerenciar pedidos >> Ver pedidos ativos, cancelar pedidos,
                                                  // mudar status do pedido
                }

                case "3" -> { // Histórico de Pedidos
                    System.out.println("CASO 3");
                }

                case "4" -> { // Log Out
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
