import Models.*;
import Repositorio.DataBase;

import java.util.Scanner;
import java.io.IOException;

public class Programa {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {

        // Adicionando um administrador e um atendente ao banco;
        DataBase.cadastrosPadrao();
        DataBase.estoquePadrao();

        int sair = 0;
        while (sair == 0) {
            // Mostramos o menu inicial e coletamos a opção;
            System.out.print(UserInterface.getMenuCadastroLogin());
            int opcao = Integer.parseInt(input.nextLine());
            switch (opcao) {
                // OPÇÃO: LOGIN;
                case 1 -> {
                    // Login.menu() deve retornar um usuário cadastrado ou null;
                    Usuario usuario = Login.menu();
                    limpaTela();
                    if (usuario instanceof Administrador) {// SE o usuário for um admministrador;
                        MenusIniciais.showMenuAdministrador((Administrador) usuario);
                    } else if (usuario instanceof Atendente) { // SE o usuário for um atendente;
                        MenusIniciais.showMenuAtendente((Atendente) usuario);
                    } else if (usuario instanceof Cliente) { // SE o usuário for um cliente;
                        MenusIniciais.showMenuCliente((Cliente) usuario);
                    }
                }

                case 2 -> {
                    Cadastro.showCadastroCliente();
                    // Pede as informações de cadastro do cliente
                }

                case 3 -> {
                    System.out.println("Volte sempre!");
                    sair = -1;
                }
            }
        }
    }

    public static void limpaTela() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
