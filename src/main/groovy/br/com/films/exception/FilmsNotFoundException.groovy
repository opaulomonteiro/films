package br.com.films.exception

class FilmsNotFoundException extends RuntimeException {
    FilmsNotFoundException(String msg) {
        super(msg)
    }
}