package com.lanebulosadeqwerty.puntajes_ms.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import java.util.ArrayList;

import com.lanebulosadeqwerty.puntajes_ms.exceptions.FiltroParaBorradoRequeridoException;
import com.lanebulosadeqwerty.puntajes_ms.exceptions.PuntajeNoEncontradoException;
import com.lanebulosadeqwerty.puntajes_ms.exceptions.PuntajeYaExisteException;
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
    Puntaje crearPuntaje(@Valid @RequestBody Puntaje puntaje) {
        // Error: Si se esta tratando de sobreescribir un puntaje existente.
        Puntaje puntajeIgual = puntajesRepositorio.findById(puntaje.getId()).orElse(null);

        if (puntajeIgual != null && !puntajeIgual.equals(puntaje.getId())) {
            throw new PuntajeYaExisteException("No es posible actualizar un puntaje si un puntaje con el mismo id ya existe.");
        }

        // Error: Puntuacion invalida
        if (puntaje.getCpm_e() < 0 || puntaje.getPrecision() < 0 || puntaje.getPrecision() > 1 || puntaje.getSegundos() < 0) {
            throw new PuntuacionInvalidaException("Los puntajes reportados no se encuentran dentro del rango válido.");
        }

        return puntajesRepositorio.save(puntaje);
    }

    @PutMapping("/aprende/puntajes/{idViejo}")
    Puntaje actualizarPuntaje(@PathVariable String idViejo, @Valid @RequestBody Puntaje puntajeNuevo) {
        // Error: Si se está tratando de modificar un puntaje inexistente
        Puntaje puntajeViejo = puntajesRepositorio.findById(idViejo).orElse(null);

        if (puntajeViejo == null) {
            throw new PuntajeNoEncontradoException("No se puede modificar un puntaje inexistente.");
        }

        // Error: Si se esta tratando de sobreescribir un puntaje existente.
        Puntaje puntajeIgual = puntajesRepositorio.findById(puntajeNuevo.getId()).orElse(null);

        if (puntajeIgual != null && !idViejo.equals(puntajeNuevo.getId())) {
            System.out.println(idViejo);
            System.out.println(puntajeNuevo.getId());
            throw new PuntajeYaExisteException("No es posible actualizar un puntaje si un puntaje con el mismo id ya existe.");
        }

        // Borrar el puntaje antiguo si sus ids son distintos
        if (!idViejo.equals(puntajeNuevo.getId())) {
            puntajesRepositorio.delete(puntajeViejo);
        }
        
        // Guardar puntaje actualizado
        return puntajesRepositorio.save(puntajeNuevo);
    }

    @DeleteMapping("/aprende/puntajes/{idPuntaje}")
    String borrarPuntaje(@PathVariable String idPuntaje){
        Puntaje puntaje = puntajesRepositorio.findById(idPuntaje).orElse(null);

        if (puntaje == null) {
            throw new PuntajeNoEncontradoException("El puntaje pedido no fue encontrado. Rectifique el id.");
        }

        puntajesRepositorio.delete(puntaje);

        return idPuntaje;
    }

    @DeleteMapping("/aprende/puntajes")
    List<String> borrarPuntajes(@RequestParam(required = false) String usuario, @RequestParam(required = false) String idLeccion) {
        /**
         * Retorna lista de puntajes, con posible filtro por usuario y/o por id de leccion
         */

        List<String> puntajesBorrados = new ArrayList<String> ();

        if (usuario == null && idLeccion == null) {
            throw new FiltroParaBorradoRequeridoException("No es posible borrar todos las puntuaciones con una sola petición.");
        }
        if (usuario != null && idLeccion == null) {
            // puntajesRepositorio.deleteAllByUsuario(Arrays.asList(new String[] {usuario}));
            for (Puntaje puntajeUsuario : puntajesRepositorio.findAllByUsuario(usuario)) {
                puntajesBorrados.add(puntajeUsuario.getId());
                puntajesRepositorio.delete(puntajeUsuario);
            }
        }
        if (usuario == null && idLeccion != null) {
            // puntajesRepositorio.deleteAllByLeccionId(Arrays.asList(new String[] {idLeccion}));
            for (Puntaje puntajeLeccion : puntajesRepositorio.findAllByLeccionId(idLeccion)) {
                puntajesBorrados.add(puntajeLeccion.getId());
                puntajesRepositorio.delete(puntajeLeccion);
            }
        }

        // Borrar todos los puntajes del usuario indicado que están en la lección indicada
        for (Puntaje puntajeUsuario : puntajesRepositorio.findAllByUsuario(usuario)) {
            if (puntajeUsuario.getLeccionId() != null && puntajeUsuario.getLeccionId().equals(idLeccion)) {
                puntajesBorrados.add(puntajeUsuario.getId());
                puntajesRepositorio.delete(puntajeUsuario);
            }
        }

        return puntajesBorrados;
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
