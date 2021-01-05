package br.com.films.exception;

public class ErrorMessage {
    String reason;

    public ErrorMessage(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
