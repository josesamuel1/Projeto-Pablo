import Models.*;
import Repositorio.DataBase;

import java.util.Scanner;

public class Cadastro {
    private static Scanner input = new Scanner(System.in);

    public static void showMenu() {
        DataBase bancoDeDados = new DataBase();

        // Receber dados obrigatorios
        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.print("Senha: ");
        String senha = input.nextLine();

        System.out.println("Você é Models.Cliente ou Funcionário?");
        String id = input.nextLine().toUpperCase();

        switch (id) {
            case "C" -> {
                System.out.print("Endereço: ");
                String endereco = input.nextLine();

                System.out.print("Telefone: ");
                String fone = input.nextLine();

                System.out.println("Cadastro concluído com sucesso.");
                bancoDeDados.adicionarUsuario(new Cliente(nome, id, email, senha, endereco, fone));
            }

            case "F" -> {
                System.out.print("Cargo: ");
                String cargo = input.nextLine();

                System.out.println("Cadastro concluído com sucesso.");
                bancoDeDados.adicionarUsuario(new Funcionario(nome, id, email, senha, cargo));
            }
        }
    }
}
