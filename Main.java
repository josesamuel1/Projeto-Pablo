import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        int sair = 0;

        while (sair == 0) {
            System.out.println("0 - Sair\n1 - Login\n2 - Cadastro");
            int x = Integer.parseInt(input.nextLine());

            switch (x) {
                case 0 -> {
                    System.out.println("Volte sempre!");
                    sair = -1;
                }
                case 1 -> {
                    Login.showMenu();
                    /*

                    Identificar se é cliente ou funcionário

                     Se for cliente, terá acesso para fazer pedido, montar um kit
                     aplicar cupons, escolher entre retirada no local ou entrega,

                     Se for funcionário, ele terá como cancelar um pedido, marcar como
                     enviado, cadastrar produto, cupons.
                     */
                }
                case 2 -> {
                    Cadastro.showMenu();
                /*
                Pede as informações de cadastro do funcionário ou cliente
                 */
                }
            }
        }
    }
}
