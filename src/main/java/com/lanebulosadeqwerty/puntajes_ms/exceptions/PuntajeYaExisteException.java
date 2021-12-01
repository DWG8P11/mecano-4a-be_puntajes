package com.lanebulosadeqwerty.puntajes_ms.exceptions;

/*
 * Si se trata de CREAR un numero de nivel que ya existe
 */

public class PuntajeYaExisteException  extends RuntimeException {
    public PuntajeYaExisteException(String message) {
        super(message);
    }
}
