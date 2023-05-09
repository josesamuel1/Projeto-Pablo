import Models.*;
import Repositorio.DataBase;

import java.util.Scanner;

public class MenusIniciais {
    private static Scanner input = new Scanner(System.in);

    public static void showMenuCliente(Cliente c) {
        System.out.println(c.getNome() + ", o que gostaria de fazer?");
        String opcao = input.nextLine();

        switch (opcao) {
            case "1" -> {
                System.out.println("CASO 1");
            }

            case "2" -> {
                System.out.println("CASO 2");
            }

            case "3" -> {
                System.out.println("CASO 3");
            }

            case "4" -> {
                System.out.println("CASO 4");
            }

            case "5" -> {
                System.out.println("CASO 5");
            }
        }
    }

    public static void showMenuFuncionario(Funcionario f) {
        System.out.println(f.getNome() + ", o que gostaria de fazer?");
        String opcao = input.nextLine();

        switch (opcao) {
            case "1" -> {
                System.out.println("CASO 1");
            }

            case "2" -> {
                System.out.println("CASO 2");
            }

            case "3" -> {
                System.out.println("CASO 3");
            }

            case "4" -> {
                System.out.println("CASO 4");
            }

            case "5" -> {
                System.out.println("CASO 5");
            }
        }
    }
}