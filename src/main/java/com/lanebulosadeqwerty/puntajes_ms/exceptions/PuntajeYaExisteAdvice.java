package com.lanebulosadeqwerty.puntajes_ms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@ResponseBody
public class PuntajeYaExisteAdvice {
    @ResponseBody
    @ExceptionHandler(PuntajeYaExisteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String PuntajeYaExisteAdvice(PuntajeYaExisteException ex){
        return ex.getMessage();
    }
}
