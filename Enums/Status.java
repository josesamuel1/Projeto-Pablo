package Enums;

import Models.pedidos.Pedido;

public enum Status {
    ACEITO("Aceito", 0),
    NEGADO("Negado", 1),
    CANCELADO("Cancelado", 2),
    PRONTO("Pronto para retirada", 3),
    ENVIADO("Enviado", 4),
    PENDENTE("Pendente", 5);
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

    public void statusEntrega(Pedido pedido){
        if (pedido.isMarcadoParaEntrega()){

        }
    }
}

