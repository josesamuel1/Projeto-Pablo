import Models.*;
import Repositorio.DataBase;

import java.util.Scanner;

public class Login {
    private static Scanner input = new Scanner(System.in);
    public static Usuario menu() {
        // Condição de parada
        boolean usuarioValido = false;
        while (!usuarioValido) {
            // Coleta de dados referentes ao login utilizando métodos auxiliares
            String email = lerEmail();
            String senha = lerSenha();

            // Verificamos se o usuario está cadastrado
            for (Usuario usuario : DataBase.getUsuarios()) {
                // Se estiver retornamos o usuario;
                if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                    System.out.println("Bem-vindo " + usuario.getNome() + "!");
                    return usuario;
                }
            }

            /*Se o usuário não estiver cadastrado ou email/senha estiverem incorretos, verificamos se o usuário quer
            continuar tentando logar ou sair*/
            System.out.println(UserInterface.getErroNoLogin());
            String opcao = lerOpcao();
            if (opcao.equals("2")){ // Se não for o loop será quebrado e o retorno será null (fora do escopo do while)
                usuarioValido = true; // Ativamos a condição de parada
            }
        }
        return null;
    }

    /*Métodos auxiliares -- Nesses métodos ocorre o tratamento de exceção*/
    private static String lerEmail(){
        System.out.print("Email: ");
        String email = input.nextLine();
        return email;
    }
    private static String lerSenha(){
        System.out.print("Senha: ");
        String senha = input.nextLine();
        return senha;
    }
    private static String lerOpcao(){
        String opcao = input.nextLine();
        return opcao;
    }
}
