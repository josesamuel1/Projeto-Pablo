package Models;

public class Administrador extends Funcionario {
    private double salario = 2000;

    public Administrador(String nome, String id, String email, String senha, String cargo) {
        super(nome, id, email, senha, cargo);
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
}
