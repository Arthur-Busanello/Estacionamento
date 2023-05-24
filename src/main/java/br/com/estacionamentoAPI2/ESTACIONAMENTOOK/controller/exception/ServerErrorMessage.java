package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller.exception;

public class ServerErrorMessage {
    private String message;
    private String table;
    private String detail;

    public void serverErrorMessage(String message, String table, String detail) {
        this.message = message;
        this.table = table;
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTable() {
        return table;
    }

    public void serverErrorMessage() {
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
