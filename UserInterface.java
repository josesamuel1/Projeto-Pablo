// Essa classe contém todos os menus exibidos no programa
public class UserInterface {
    private static final String menuCadastroLogin = """
            +-----+--------------+
            |  1  |   Login      |
            |  2  |   Cadastro   |
            |  3  |   Sair       |
            +---+----------------+
            >>>\s""";
    /*----------------------------------------------------------------------------------------------------------------*/
    private static final String menuAdministrador = """
            +-----+------------------------+
            |  1  | Gerenciar funcionários |
            |  2  | Gerenciar clientes     |
            |  3  | Gerenciar pedidos      |
            |  4  | Gerenciar produtos     |
            |  5  | Histórico de pedidos   |
            |  6  | Log out                |
            +-----+------------------------+
            >>>\s""";
    private static final String gerenciarFuncionarios = """
            +-----+----------------------------+
            |  1  | Listar funcionários        |
            |  2  | Cadastrar novo funcionário |
            |  3  | Remover funcionário        |
            |  4  | Calcular salário           |
            |  5  | Voltar                     |
            +-----+----------------------------+
            >>>\s""";
    private static final String gerenciarProdutos = """
            +-----+----------------------+
            |  1  | Cadastrar            |
            |  2  | Remover              |
            |  3  | Atualizar            |
            |  4  | Voltar               |
            +-----+----------------------+
            >>>\s""";
    /*----------------------------------------------------------------------------------------------------------------*/
    private static final String menuCliente = """
            +-----+----------------------------+
            |  1  | Ver cardápio               |
            |  2  | Fazer pedido               |
            |  3  | Pedir um kit pronto        |
            |  4  | Pedir um kit personalizado |
            |  5  | Ver seus pedidos           |
            |  6  | Log out                    |
            +-----+----------------------------+
            >>>\s""";
    /*----------------------------------------------------------------------------------------------------------------*/
    private static final String menuAtendente = """
            +-----+---------------------------+
            |  1  | Criar novo pedido         |
            |  2  | Gerenciar pedidos         |
            |  3  | Histórico de pedidos      |
            |  4  | Gerenciar clientes        |
            |  5  | Log out                   |
            +-----+---------------------------+
            >>>\s""";
    /*----------------------------------------------------------------------------------------------------------------*/
    private static final String erroNoLogin = """
            Email ou senha incorretos ou usuário não cadastrado.
            Gostaria de tentar novamente?
            +-----+-------+
            |  1  |  Sim  |
            |  2  |  Não  |
            +-----+-------+
            >>>\s""";

    /* Getters */

    // Menus
    public static String getMenuCadastroLogin() {
        return menuCadastroLogin;
    }

    public static String getMenuAdministrador() {
        return menuAdministrador;
    }

    public static String getMenuCliente() {
        return menuCliente;
    }

    public static String getMenuAtendente() {
        return menuAtendente;
    }

    public static String getGerenciarProdutos() {
        return gerenciarProdutos;
    }

    public static String getGerenciarFuncionarios() {
        return gerenciarFuncionarios;
    }

    public static String getErroNoLogin() {
        return erroNoLogin;
    }

}
