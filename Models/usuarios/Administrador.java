package Models.usuarios;

public class Administrador extends Funcionario {
    private double salario = 2000;

    public Administrador(String nome, String id, String email, String senha, String cargo) {
        super(nome, id, email, senha, cargo);
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSalario() {
        return this.salario;
    }

    public double calcularHoraExtra(int horas) {
        if (horas == 0) {
            this.salario = this.salario + ((super.getHorasDeTrabalho() - 160) * 8);
        } else if (horas != 0) {
            this.salario = this.salario + (((super.getHorasDeTrabalho() + horas) - 160) * 8);
        }

        System.out.println("O novo salário de " + super.getNome() + " é de R$ " + this.salario);
        return this.salario;
    }
}