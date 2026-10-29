package br.com.estudo.veiculosapi;


public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(Long id) {
        super("Recurso " + id + " não encontrado");
    }
}
