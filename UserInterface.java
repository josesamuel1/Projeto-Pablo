// Essa classe contém todos os menus exibidos no programa
public class UserInterface {
    private static final String menuCadastroLogin = """
            +-----+--------------+
            |  1  |   Login      |
            |  2  |   Cadastro   |
            |  3  |   Sair       |
            +-----+--------------+
            >>>\s""";
    /*----------------------------------------------------------------------------------------------------------------*/
    private static final String menuAdministrador = """
            +-----+------------------------+
            |  1  | Gerenciar funcionários |
            |  2  | Gerenciar clientes     |
            |  3  | Gerenciar pedidos      |
            |  4  | Gerenciar estoque      |
            |  5  | Gerenciar combos       |
            |  6  | Histórico de pedidos   |
            |  7  | Log out                |
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
    private static final String gerenciarClientes = """
            +-----+----------------------------+
            |  1  | Listar clientes            |
            |  2  | Adicionar novo cliente     |
            |  3  | Remover cliente            |
            |  4  | Editar cliente             |
            |  5  | Voltar                     |
            +-----+----------------------------+
            >>>\s""";
    private static final String gerenciarEstoque = """
            +-----+----------------------+
            |  1  | Exibir estoque       |
            |  2  | Cadastrar produtos   |
            |  3  | Remover produtos     |
            |  4  | Atualizar produtos   |
            |  5  | Voltar               |
            +-----+----------------------+
            >>>\s""";
    private static final String gerenciarCombos = """
            +-----+----------------------+
            |  1  | Exibir combos        |
            |  2  | Criar combo          |
            |  3  | Remover combo        |
            |  4  | Voltar               |
             +-----+---------------------+
            >>>\s""";

    private static final String mudarStatusEntrega = """
            +------------------------------------+
            | Selecione o novo status do pedido  |
            | 1 - Aceito                         |
            | 2 - Negado                         |
            | 3 - Enviado                        |
            +------------------------------------+
            >>>\s""";
    private static final String mudarStatusRetirada = """
            +------------------------------------+
            | Selecione o novo status do pedido  |
            | 1 - Aceito                         |
            | 2 - Negado                         |
            | 3 - Pronto para retirada           |
            +------------------------------------+
            >>>\s""";
    /*----------------------------------------------------------------------------------------------------------------*/
    private static final String menuCliente = """
            +-----+----------------------------+
            |  1  | Ver cardápio               |
            |  2  | Fazer pedido               |
            |  3  | Gerenciar pedidos ativos   |
            |  4  | Histórico de Pedidos       |
            |  5  | Log out                    |
            +-----+----------------------------+
            >>>\s""";
    /*----------------------------------------------------------------------------------------------------------------*/
    private static final String menuAtendente = """
            +-----+---------------------------+
            |  1  | Gerenciar pedidos         |
            |  2  | Histórico de pedidos      |
            |  3  | Gerenciar clientes        |
            |  4  | Log out                   |
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

    // Outros
    public static String getGerenciarEstoque() {
        return gerenciarEstoque;
    }

    public static String getGerenciarFuncionarios() {
        return gerenciarFuncionarios;
    }

    public static String getGerenciarClientes() {
        return gerenciarClientes;
    }

    public static String getErroNoLogin() {
        return erroNoLogin;
    }

    public static String getGerenciarCombos() {
        return gerenciarCombos;
    }
    public static String getMudarStatusEntrega(){
        return mudarStatusEntrega;
    }
    public static String getMudarStatusRetirada(){
        return mudarStatusRetirada;
    }
}
