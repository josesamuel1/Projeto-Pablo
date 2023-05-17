import Models.pedidos.Produto;
import Models.usuarios.Atendente;
import Models.usuarios.Cliente;
import Repositorio.DataBase;

import java.util.Scanner;

public class Cadastro {
    private static Scanner input = new Scanner(System.in);

    public static void showCadastroCliente() {
        // Receber dados obrigatorios
        String nome, email, senha = null;
        while (true) {
            System.out.print("Nome: ");
            nome = input.nextLine();
            if (!nome.isEmpty()) {
                break;
            }
        }

        while (true) {
            System.out.print("Email: ");
            email = input.nextLine();
            if (!email.isEmpty()) {
                break;
            }
        }

        while (true) {
        System.out.print("Senha: ");
        senha = input.nextLine();
            if (!senha.isEmpty()) {
                break;
            }
        }
        System.out.print("Endereço: ");
        String endereco = input.nextLine();

        System.out.print("Telefone: ");
        String fone = input.nextLine();

        System.out.println("Cadastro concluído com sucesso.");
        DataBase.adicionarCliente(new Cliente(nome, "clt", email, senha, endereco, fone));
    }

    public static void showCadastroFuncionario() {
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
        DataBase.adicionarAtendente(new Atendente(nome, "atd", email, senha, cargo));
    }

    public static void cadastroProduto() {
        System.out.println("| Cadastro de produto");
        System.out.print("| Nome: ");
        String nome = input.nextLine();

        System.out.print("| Tipo/Sabor: ");
        String tipo = input.nextLine();

        System.out.print("| Preço Unitário: ");
        double preco = Double.parseDouble(input.nextLine());

        System.out.print("| Quantidade em estoque: ");
        int quantidadeEstoque = Integer.parseInt(input.nextLine());

        DataBase.adicionarAoEstoque(new Produto(nome, tipo, preco), quantidadeEstoque);
        System.out.println("| Produto cadastrado.");
    }
}
