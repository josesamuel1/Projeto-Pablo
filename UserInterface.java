// Essa classe contém todos os menus exibidos no programa
public class UserInterface {
    private static String menuInicial =
            "+---+--------------|\n" +
            "| 1 |   Login      |\n" +
            "+---+--------------|\n" +
            "| 2 |   Cadastro   |\n" +
            "+---+--------------|\n" +
            "| 3 |   Sair       |\n" +
            "+---+--------------|\n>>> ";

    private static String erroNoLogin =
            "Email ou senha incorretos ou usuário não cadastrado.\n" +
            "Gostaria de tentar novamente?\n" +
            "+---+-----+\n" +
            "| 1 | Sim |\n" +
            "+---+-----+\n" +
            "| 2 | Não |\n" +
            "+---+-----+\n>>> ";


    /*Getters*/
    public static String getMenuInicial() {
        return menuInicial;
    }
    public static String getErroNoLogin() {
        return erroNoLogin;
    }
}