package Enums;

public enum Status {
    ACEITO("Aceito", 0),
    NEGADO("Negado", 1),
    CANCELADO("Cancelado", 2),
    PRODUCAO("Em produção", 3),
    PRONTO("Pronto para retirada", 4),
    ENVIADO("Enviado", 5),
    PENDENTE("Pendente", 6);
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

