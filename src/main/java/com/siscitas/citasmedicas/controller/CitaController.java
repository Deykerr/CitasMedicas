package com.siscitas.citasmedicas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.siscitas.citasmedicas.controller.dto.CitaRequest;
import com.siscitas.citasmedicas.controller.dto.CitaResponse;
import com.siscitas.citasmedicas.service.CitaService;

// Opcional: import jakarta.validation.Valid;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<CitaResponse>> getAllCitas() {
        return ResponseEntity.ok(citaService.findAllCitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> getCitaById(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.findByIdCita(id));
    }

    @PostMapping
    public ResponseEntity<CitaResponse> programarCita(/*@Valid*/ @RequestBody CitaRequest request) {
        // Aquí el servicio ya retorna CitaResponse, lo que es ideal para POST
        CitaResponse newCita = citaService.programarCita(request);

        // Construir la URI del recurso creado para el encabezado Location (HTTP 201 Created)
        // Esto es una buena práctica RESTful
        // URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        //         .path("/{id}")
        //         .buildAndExpand(newCita.getIdCita()) // Asumiendo que getIdCita() existe en CitaResponse
        //         .toUri();
        // return ResponseEntity.created(location).body(newCita); // HTTP 201 Created con el cuerpo

        // Si prefieres una respuesta más simple por ahora sin Location header:
        return ResponseEntity.status(HttpStatus.CREATED).body(newCita); // HTTP 201 Created con el cuerpo
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaResponse> updateCita(@PathVariable Long id, /*@Valid*/ @RequestBody CitaRequest request) {
        // Aquí el servicio ya retorna CitaResponse, lo que es ideal para PUT
        CitaResponse updatedCita = citaService.updateCita(id, request);
        return ResponseEntity.ok(updatedCita); // HTTP 200 OK con el cuerpo actualizado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}