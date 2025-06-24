package com.siscitas.citasmedicas.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siscitas.citasmedicas.controller.dto.PacienteRequest;
import com.siscitas.citasmedicas.controller.dto.PacienteResponse;
import com.siscitas.citasmedicas.service.PacienteService;



@RestController
@RequestMapping("pacientes")
public class PacienteController {
	 @Autowired
	    PacienteService pacienteService;

	
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
	    
	    @Override
	    public Optional<PacienteResponse> findByDni(Integer dni) {
	        // El repositorio devolverá Optional<Paciente>
	        Optional<Paciente> pacienteOptional = pacienteRepository.findByDni(dni);
	        // Si el Optional contiene un paciente, lo mapea a PacienteResponse, de lo contrario, devuelve Optional.empty()
	        return pacienteOptional.map(pacienteMapper::toPacienteToPacienteResponse);
	    }

	    @Override
	    public List<PacienteResponse> findByNombreContainingIgnoreCase(String nombre) {
	        // El repositorio devolverá List<Paciente>
	        List<Paciente> pacientes = pacienteRepository.findByNombreContainingIgnoreCase(nombre);
	        return pacienteMapper.toListPacienteToPacienteResponse(pacientes);
	    }
}
