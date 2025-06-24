package com.siscitas.citasmedicas.service;

import java.util.Collection;

import com.siscitas.citasmedicas.controller.dto.PacienteRequest;
import com.siscitas.citasmedicas.controller.dto.PacienteResponse;

public interface PacienteService {

	Collection<PacienteResponse> findAllPaciente ();
	
	PacienteResponse findByIdPaciente(Long idPaciente);

	void savePaciente (PacienteRequest request);

	void updatePaciente(Long id, PacienteRequest request);
	
	void deletePaciente (Long idPaciente);
	
	PacienteResponse findByDni(Integer dni);
	Collection<PacienteResponse> findByNombreContainingIgnoreCase(String nombre);
}
