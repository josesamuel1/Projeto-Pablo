import Models.usuarios.*;

public class TestHoraExtra {
    // testar o cálculo de horas extras do administrador
    public void TestarHoraExtraF() {
        Administrador adm = new Administrador("Samuel", "adm", "samuel@gmail.com", "senha123", "Admin");
        int resultadoEsperado = 2000;

        double resultado = adm.calcularHoraExtra(160);
        if (resultado == resultadoEsperado) {
            System.out.println("Teste ok!");
        } else {
            System.out.println("Teste Falhou!");
        }
    }

    // testar o cálculo de horas extras do atendente
    public void TestarHoraExtraA() {
        Atendente atd = new Atendente("Samuel", "atd", "samuel@gmail.com", "senha123", "Atendente");
        int resultadoEsperado = 1330;

        double resultado = atd.calcularHoraExtra(160);
        if (resultado == resultadoEsperado) {
            System.out.println("Teste ok!");
        } else {
            System.out.println("Teste Falhou!");
        }
    }
}
