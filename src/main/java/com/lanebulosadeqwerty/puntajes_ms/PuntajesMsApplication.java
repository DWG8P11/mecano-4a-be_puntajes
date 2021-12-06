package com.lanebulosadeqwerty.puntajes_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PuntajesMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuntajesMsApplication.class, args);
	}

	@GetMapping("/")
	public String darMensajeBienvenida() {
		return """
		<h1>La Nebulosa de Qwerty - Microservicio de Puntajes</h1> <br/>
		Hola! Estás en el backend del Microservicio de Puntajes de la aplicación La Nebulosa de Qwerty.
		""";
	}
}
