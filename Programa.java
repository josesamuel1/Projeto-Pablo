import Models.usuarios.Administrador;
import Models.usuarios.Atendente;
import Models.usuarios.Cliente;
import Models.usuarios.Usuario;
import Repositorio.DataBase;

import java.util.Scanner;
import java.io.IOException;

public class Programa {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        DataBase.instanciacaoPadrao();

        int sair = 0;
        while (sair == 0) {
            try {
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
}
