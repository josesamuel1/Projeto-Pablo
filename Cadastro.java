import Models.*;
import Repositorio.DataBase;

import java.util.Scanner;

public class Cadastro {
    private static Scanner input = new Scanner(System.in);

    public static void showCadastroCliente() {
        DataBase bancoDeDados = new DataBase();

        // Receber dados obrigatorios
        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.print("Senha: ");
        String senha = input.nextLine();

        System.out.print("Endereço: ");
        String endereco = input.nextLine();

        System.out.print("Telefone: ");
        String fone = input.nextLine();

        System.out.println("Cadastro concluído com sucesso.");
        bancoDeDados.adicionarUsuario(new Cliente(nome, "clt", email, senha, endereco, fone));
    }

    public static void showCadastroFuncionario() {
        DataBase bancoDeDados = new DataBase();

        // Receber dados obrigatórios
        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.print("Senha: ");
        String senha = input.nextLine();

        System.out.print("Cargo: ");
        String cargo = input.nextLine();

        System.out.println("Cadastro concluído com sucesso.");
        bancoDeDados.adicionarUsuario(new Atendente(nome, "atd", email, senha, cargo));
    }
}
