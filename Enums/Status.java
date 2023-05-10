package Enums;

public enum Status {
    ACEITO("Aceito", 1),
    NEGADO("Negado", 2),
    CANCELADO("Cancelado", 3),
    PRODUCAO("Em produção", 4),
    PRONTO("Pronto para retirada", 5),
    ENVIADO("Enviado", 6);
    private String status;
    private int id;

    Status(String status, int id) {
        this.status = status;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
}

