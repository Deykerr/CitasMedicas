package com.siscitas.citasmedicas.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siscitas.citasmedicas.controller.dto.PacienteRequest;
import com.siscitas.citasmedicas.controller.dto.PacienteResponse;
import com.siscitas.citasmedicas.model.Paciente;
import com.siscitas.citasmedicas.repository.PacienteRepository;
import com.siscitas.citasmedicas.service.PacienteService;
import com.siscitas.citasmedicas.service.mapper.PacienteMapper;



@RestController
@RequestMapping("pacientes")
public class PacienteController {
	 @Autowired
	    PacienteService pacienteService;
	    @Autowired
	    private PacienteMapper pacienteMapper;
	    @Autowired
	    private PacienteRepository pacienteRepository;

	
	    @GetMapping
	    public ResponseEntity<Collection<PacienteResponse>> getPacientes(){
	        return  ResponseEntity.ok(pacienteService.findAllPaciente());
	    }

	 
	    @GetMapping("/{id}")
	    public ResponseEntity<PacienteResponse> getPacientesById(@PathVariable Long id){
	        return  ResponseEntity.ok(pacienteService.findByIdPaciente(id));
	    }

	
	    @PostMapping 
	    public void saveClientesById(@RequestBody PacienteRequest request){
	    	pacienteService.savePaciente(request);
	    }

	
	    @PutMapping("/{id}") 
	    public void updateClientesById(@PathVariable Long id, @RequestBody PacienteRequest request){
	    	pacienteService.updatePaciente(id, request);
	    }

	    @DeleteMapping("/{id}") // 
	    public void deleteClientesById(@PathVariable Long id){
	    	pacienteService.deletePaciente(id);
	    }
	    
	  /*  @GetMapping("/search") 
	    public ResponseEntity<?> searchPacientes(
	            @RequestParam(required = false) Integer dni, 
	            @RequestParam(required = false) String nombre) { 

	        if (dni != null) {
	            PacienteResponse paciente = pacienteService.findByDni(dni);
	           
	            return paciente.map(ResponseEntity::ok)
	                           .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente con DNI " + dni + " no encontrado."));
	        } else if (nombre != null && !nombre.trim().isEmpty()) {
	            Collection<PacienteResponse> pacientes = pacienteService.findByNombreContainingIgnoreCase(nombre);
	          
	            return ResponseEntity.ok(pacientes);
	        } else {
	         
	            return ResponseEntity.badRequest().body("Se debe proporcionar al menos un 'dni' o 'nombre' para la búsqueda.");
	        }
	    }*/
}
