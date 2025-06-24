package com.siscitas.citasmedicas.controller;

import java.util.Collection;
import java.util.List; // O Collection si prefieres
import java.util.Optional; // Para el findByDni

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
import org.springframework.web.bind.annotation.RequestParam; // Para buscar por DNI/nombre
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.siscitas.citasmedicas.controller.dto.MedicoRequest;
import com.siscitas.citasmedicas.controller.dto.MedicoResponse;
import com.siscitas.citasmedicas.service.MedicoService;



@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> getAllMedicos() {
        return ResponseEntity.ok(medicoService.findAllMedico());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> getMedicoById(@PathVariable Long id) {
     
        return ResponseEntity.ok(medicoService.findByIdMedico(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMedicos(
            @RequestParam(required = false) Integer dni,
            @RequestParam(required = false) String nombre) {

        if (dni != null) {
            Optional<MedicoResponse> medico = medicoService.findByDni(dni);
        
            return medico.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
        } else if (nombre != null) {
            Collection<MedicoResponse> medicos = medicoService.findByNombreContainingIgnoreCase(nombre);
            return ResponseEntity.ok(medicos);
        } else {
            return ResponseEntity.badRequest().body("Se requiere DNI o nombre para la búsqueda.");
        }
    }


    @PostMapping
    public ResponseEntity<Void> createMedico(@RequestBody MedicoRequest request) {
        medicoService.saveMedico(request); 

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMedico(@PathVariable Long id, @RequestBody MedicoRequest request) {
        medicoService.updateMedico(id, request);
    
        return ResponseEntity.ok().build(); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        medicoService.deleteMedico(id);
        return ResponseEntity.noContent().build(); 
    }
}
