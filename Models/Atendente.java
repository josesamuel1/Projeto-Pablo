package Models;

public class Atendente extends Funcionario {
    private double salario = 1300;

    public Atendente(String nome, String id, String email, String senha, String cargo) {
        super(nome, id, email, senha, cargo);
    }
}