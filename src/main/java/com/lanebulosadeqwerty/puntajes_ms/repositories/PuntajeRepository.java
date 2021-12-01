package com.lanebulosadeqwerty.puntajes_ms.repositories;

import java.util.List;

import com.lanebulosadeqwerty.puntajes_ms.models.Puntaje;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PuntajeRepository extends MongoRepository<Puntaje, String> {
    List<Puntaje> findAllByUsuario(String usuario);
    List<Puntaje> findAllByLeccionId(String leccionId);
}
