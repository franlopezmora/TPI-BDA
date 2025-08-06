package com.tpi.pruebas.exception;

/**
 * Lanzada cuando el interesado está restringido y no puede participar en la prueba.
 */
public class InteresadoRestringidoException extends RuntimeException {
    public InteresadoRestringidoException(Long id) {
        super("Interesado con id=" + id + " está restringido");
    }
}
