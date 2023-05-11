// Essa classe contém todos os menus exibidos no programa
public class UserInterface {
    private static final String menuCadastroLogin = """
            +---+--------------+
            | 1 |   Login      |
            +---+--------------+
            | 2 |   Cadastro   |
            +---+--------------+
            | 3 |   Sair       |
            +---+--------------+
            >>>\s""";

    private static final String erroNoLogin = """
            Email ou senha incorretos ou usuário não cadastrado.
            Gostaria de tentar novamente?
            +---+-----+
            | 1 | Sim |
            +---+-----+
            | 2 | Não |
            +---+-----+
            >>>\s""";

    private static final String menuAdministrador = """
            +-----+------------------------+
            |  1  | Gerenciar funcionários |
            +-----+------------------------+
            |  2  | Gerenciar clientes     |
            +-----+------------------------+
            |  3  | Gerenciar pedidos      |
            +-----+------------------------+
            |  4  | Histórico de pedidos   |
            +-----+------------------------+
            |  5  | Log out                |
            +-----+------------------------+
            >>>\s""";

    private static final String menuCliente = """
            +-----+----------------------------+
            |  1  | Ver cardápio               |
            +-----+----------------------------+
            |  2  | Fazer pedido               |
            +-----+----------------------------+
            |  3  | Pedir um kit pronto        |
            +-----+----------------------------+
            |  4  | Pedir um kit personalizado |
            +-----+----------------------------+
            |  5  | Ver seus pedidos           |
            +-----+----------------------------+
            |  6  | Log out                    |
            +-----+----------------------------+
            >>>\s""";

    private static final String menuAtendente = """
            +-----+----------------------------+
            |  1  | Criar novo pedido          |
            +-----+----------------------------+
            |  2  | Gerenciar pedidos          |
            +-----+----------------------------+
            |  3  | Histórico de pedidos       |
            +-----+----------------------------+
            |  4  | Gerenciar clientes         |
            +-----+----------------------------+
            |  5  | Log out                    |
            +-----+----------------------------+
            >>>\s""";

    private static final String cardapioCliente = """
            +----------------------------------------------+
            |  Opção  |      Produtos     | Preço unitário |
            |                  SEÇÃO:COMIDA                |
            |                    Pipoca                    |
            |    1    | Caramelo        |     R$10.00      |
            |    2    | Manteiga        |     R$ 5.00      |
            |                 Algodão doce                 |
            |    3    | Morango         |     R$ 3.00      |
            |    4    | Laranja         |     R$ 3.00      |
            |                  Refrigerante                |
            |    5    | Coca-cola       |     R$12.00      |
            |    6    | Fanta Uva       |     R$10.00      |
            |                     Torta                    |
            |    7    | Chocolate       |     R$50.00      |
            |    8    | Frango          |     R$45.00      |
            |                SEÇÃO:DECORAÇÃO               |
            |                     Balões                   |
            |    9    | Festa           |     R$ 8,00      |
            |    10   | Formato Coração |     R$10,00      |
            |                Toalhas de mesa               |
            |    11   | Estampa Batman  |     R$10,00      |
            |    12   | Sem estampa     |     R$10,00      |
            |                     Velas                    |
            |    13   | Estrelinha      |     R$ 6,00      |
            |    14   | Cometa          |     R$10,00      |
            |              SEÇÃO:DESCARTÁVEIS              |
            |    15   | Kit branco      |     R$10,00      |
            |    16   | Kit colorido    |     R$10,00      |
            +----------------------------------------------+
            >>>\s""";

    private static final String gerenciarFuncionarios = """
            +-----+----------------------------+
            |  1  | Cadastrar novo funcionário |
            +-----+----------------------------+
            |  2  | Remover funcionário        |
            +-----+----------------------------+
            |  3  | Calcular salário           |
            +-----+----------------------------+
            |  4  | Voltar                     |
            +-----+----------------------------+
            """;

    /* Getters */
    public static String getMenuCadastroLogin() {
        return menuCadastroLogin;
    }

    public static String getErroNoLogin() {
        return erroNoLogin;
    }

    /* Menus */
    public static String getMenuAdministrador() {
        return menuAdministrador;
    }

    public static String getMenuCliente() {
        return menuCliente;
    }

    public static String getMenuAtendente() {
        return menuAtendente;
    }

    public static String getCardapioCliente() {
        return cardapioCliente;
    }

    public static String getGerenciarFuncionarios() {
        return gerenciarFuncionarios;
    }
}
