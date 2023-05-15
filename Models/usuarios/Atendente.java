package Models.usuarios;

public class Atendente extends Funcionario {
    private double salario = 1300;

    public Atendente(String nome, String id, String email, String senha, String cargo) {
        super(nome, id, email, senha, cargo);
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double getSalario() {
        return this.salario;
    }
    public void calcularHoraExtra(int horas) {
        if (horas == 0) {
            this.salario = this.salario + ((super.getHorarioDeTrabalho() - 160) * 8);
        } else if (horas != 0) {
            this.salario = this.salario + (((super.getHorarioDeTrabalho() + horas) - 160) * 8);
        }

        System.out.println("O novo salário de " + super.getNome() + " é de R$ " + this.salario);
    }
}