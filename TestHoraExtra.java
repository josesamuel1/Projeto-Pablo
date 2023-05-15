import Models.usuarios.Atendente;

public class TestHoraExtra {
    private Administrador adm;

    // testar o cálculo de horas extras do administrador
    public void TestarHoraExtraF() {
        Administrador adm = new Administrador();
        int resultadoEsperado = 2000;

        int resultado = Administrador.calcularHoraExtra();
        if (resultado == resultadoEsperado) {
            System.out.println("Teste ok!");
        } else {
            System.out.println("Teste Falhou!");
        }
    }

    private Atendente atd;

    // testar o cálculo de horas extras do atendente
    public void TestarHoraExtraA() {
        Atendente atd = new Atentedente();
        int resultadoEsperado = 1330;

        int resultado = Atedente.calcularHoraExtra(160);
        if (resultado == resultadoEsperado) {
            System.out.println("Teste ok!");
        } else {
            System.out.println("Teste Negado!");
        }
    }
}
