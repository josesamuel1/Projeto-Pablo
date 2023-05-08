import java.util.Scanner;

// classe para realizar login
public class Login {
    private static Scanner input = new Scanner(System.in);

    public static void showMenu() {
        // preenche email e senha
        System.out.print("Email: ");
        String email = input.nextLine();

        System.out.print("Senha: ");
        String senha = input.nextLine();

        for (Usuario usuario : DataBase.getusuariosCadastrados()) {
            if (usuario.getEmail() == email && usuario.getSenha() == senha) {
                System.out.println("USUARIO LOGADO");
            } else {
                System.out.println("EMAIL OU SENHA INCORRETA");
            }
        }
    }
}
