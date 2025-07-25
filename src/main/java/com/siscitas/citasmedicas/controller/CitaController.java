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
    public ResponseEntity<CitaResponse> programarCita( @RequestBody CitaRequest request) {
       
        CitaResponse newCita = citaService.programarCita(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCita); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaResponse> updateCita(@PathVariable Long id,  @RequestBody CitaRequest request) {
   
        CitaResponse updatedCita = citaService.updateCita(id, request);
        return ResponseEntity.ok(updatedCita); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return ResponseEntity.noContent().build();
    }
}