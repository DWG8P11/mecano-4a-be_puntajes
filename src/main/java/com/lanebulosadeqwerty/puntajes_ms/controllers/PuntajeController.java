package com.lanebulosadeqwerty.puntajes_ms.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import java.util.ArrayList;

import com.lanebulosadeqwerty.puntajes_ms.exceptions.PuntajeNoEncontradoException;
import com.lanebulosadeqwerty.puntajes_ms.exceptions.PuntuacionInvalidaException;
import com.lanebulosadeqwerty.puntajes_ms.models.Puntaje;
import com.lanebulosadeqwerty.puntajes_ms.repositories.PuntajeRepository;

@RestController
public class PuntajeController {
    private final PuntajeRepository puntajesRepositorio;

    public PuntajeController(PuntajeRepository puntajesRepositorio) {
        this.puntajesRepositorio = puntajesRepositorio;
    }

    @GetMapping("/aprende/puntajes")
    List<Puntaje> traerPuntajes(@RequestParam(required = false) String usuario, @RequestParam(required = false) String idLeccion) {
        /**
         * Retorna lista de puntajes, con posible filtro por usuario y/o por id de leccion
         */
        if (usuario == null && idLeccion == null) {
            return puntajesRepositorio.findAll();
        }

        // Procesar filtros
        if (usuario != null && idLeccion == null) {
            return puntajesRepositorio.findAllByUsuario(usuario);
        }
        if (usuario == null && idLeccion != null) {
            return puntajesRepositorio.findAllByLeccionId(idLeccion);
        }

        // Nota: basado en metodo ayudante traerPuntajesUsuarioLeccion, que devuelve excepcion si la leccion no existe
        return traerPuntajesUsuarioLeccion(usuario, idLeccion); 
    }

    @GetMapping("/aprende/puntajes/{idPuntaje}")
    Puntaje traerPuntaje(@PathVariable String idPuntaje) {
        Puntaje puntaje = puntajesRepositorio.findById(idPuntaje).orElse(null);

        if (puntaje == null) {
            throw new PuntajeNoEncontradoException("El puntaje pedido no fue encontrado. Rectifique el id.");
        }

        return puntaje;
    }


    @PostMapping("/aprendizaje/puntajes")
    Puntaje nuevoPuntaje(@Valid @RequestBody Puntaje puntaje) {
        // Error: Puntuacion invalida
        if (puntaje.getCpm_e() < 0 || puntaje.getPrecision() < 0 || puntaje.getPrecision() > 1 || puntaje.getSegundos() < 0) {
            throw new PuntuacionInvalidaException("Los puntajes reportados no se encuentran dentro del rango válido.");
        }

        return puntajesRepositorio.save(puntaje);
    }




    /*
     * Metodos ayudantes
     */
    private List<Puntaje> traerPuntajesUsuarioLeccion(String usuario, String idLeccion) {
        /**
         * Devuelve la lista de puntajes del usuario en la leccion identificada por idLeccion.
         */

        // Lista de puntajes solo de la leccion deseada
        List<Puntaje> listaPuntajesUsuario = puntajesRepositorio.findAllByUsuario(usuario);
        List<Puntaje> rta = new ArrayList<>();

        for (Puntaje puntajeUsuario : listaPuntajesUsuario) {
            if (puntajeUsuario.getLeccionId() != null && puntajeUsuario.getLeccionId().equals(idLeccion)){
                rta.add(puntajeUsuario);
            }
        }

        return rta;
    }
}
