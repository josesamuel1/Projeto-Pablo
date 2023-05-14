package Models.usuarios;

public class Administrador extends Funcionario {
    private double salario = 2000;

    public Administrador(String nome, String id, String email, String senha, String cargo) {
        super(nome, id, email, senha, cargo);
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void calcularHoraExtra() {
        if (super.getHorarioDeTrabalho() > 160) {
            this.salario = this.salario + ((super.getHorarioDeTrabalho() - 160) * 8);
        }
        System.out.println("O funcionário " + super.getNome() + "é RS " + this.salario);
    }
}
