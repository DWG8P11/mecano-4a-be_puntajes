package com.lanebulosadeqwerty.puntajes_ms.exceptions;

/*
 * Si se trata de agregar una precision, o un cpm_e, o un un tiempo menor a 0
 */
public class PuntuacionInvalidaException  extends RuntimeException {
    public PuntuacionInvalidaException(String message) {
        super(message);
    }
}
