public class Administrador extends Funcionario {
    private double salario = 2000;

    public Administrador(String nome, String id, String email, String senha, String cargo, double salario, int horasDeTrabalho) {
        super(nome, id, email, senha, cargo);
        this.salario = salario;
    }
}
