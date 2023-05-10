import Models.*;
import Repositorio.DataBase;

import java.util.Scanner;

public class Programa {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Instanciando o banco de dados;
        DataBase bancoDeDados = new DataBase();

        // Adicionando um administrador e um atendente ao banco;
        bancoDeDados
                .adicionarUsuario(new Administrador("Maria", "adm", "maria@gmail.com", "senha123", "Administrador"));
        bancoDeDados.adicionarUsuario(new Atendente("Alex", "atd", "alex@gmail.com", "senha321", "Atendente"));

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
                    if (usuario instanceof Administrador) { // SE o usuário for um admministrador;
                        MenusIniciais.showMenuAdministrador(usuario);
                    } else if (usuario instanceof Atendente) { // SE o usuário for um atendente;
                        MenusIniciais.showMenuAtendente(usuario);
                    } else if (usuario instanceof Cliente) { // SE o usuário for um cliente;
                        MenusIniciais.showMenuCliente(usuario);
                    }
                }
                case 2 -> {
                    Cadastro.showMenu();
                    // Pede as informações de cadastro do cliente
                }
                case 3 -> {
                    System.out.println("Volte sempre!");
                    sair = -1;
                }
            }
        }
    }
}
