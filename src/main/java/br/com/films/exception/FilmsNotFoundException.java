package br.com.films.exception;

public class FilmsNotFoundException extends RuntimeException {
    public FilmsNotFoundException(String msg) {
        super(msg);
    }
}