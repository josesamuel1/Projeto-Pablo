import Models.*;

import java.util.Scanner;

public class MenusIniciais {
    private static Scanner input = new Scanner(System.in);

    public static void showMenuCliente(Usuario user) {
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
            case "6" -> { // Sair
                System.out.println("CASO 6");
            }
        }
    }

    public static void showMenuAdministrador(Usuario user) {
        System.out.print(UserInterface.getMenuAdministrador());
        String opcao = input.nextLine();

        switch (opcao) {
            case "1" -> { // |  1  | Gerenciar funcionarios >> Cadastrar, remover, calcular salário
                System.out.println("CASO 1");
            }

            case "2" -> {
                System.out.println("CASO 2"); // |  2  | Gerenciar pedidos  >> Ver pedidos ativos, cancelar pedidos, mudar status do pedido
            }

            case "3" -> { // |  3  | Histórico de Pedidos
                System.out.println("CASO 3");
            }

            case "4" -> { // |  4  | Log out
                System.out.println("CASO 4");
            }
        }
    }

    public static void showMenuAtendente(Usuario user) {
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

            case "4" -> { // gerenciar cliente
                System.out.println("CASO 4");
            }

            case "5" -> { // Log out
                System.out.println("CASO 5");
            }
        }
    }
}
