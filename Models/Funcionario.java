package Models;

public class Funcionario extends Usuario {
    private String cargo;
    private int horasDeTrabalho = 160;

    public Funcionario(String nome, String id, String email, String senha, String cargo) {
        super(nome, id, email, senha);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getHorarioDeTrabalho() {
        return horasDeTrabalho;
    }

    public void setHorasDeTrabalho(int horasDeTrabalho) {
        this.horasDeTrabalho = horasDeTrabalho;
    }
}
