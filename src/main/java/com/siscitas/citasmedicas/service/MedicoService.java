package com.siscitas.citasmedicas.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.siscitas.citasmedicas.controller.dto.MedicoRequest;
import com.siscitas.citasmedicas.controller.dto.MedicoResponse;


public interface MedicoService {
	List<MedicoResponse> findAllMedico ();
	
	MedicoResponse findByIdMedico(Long idMedico);

	void saveMedico(MedicoRequest request);

	void updateMedico(Long id, MedicoRequest request);
	
	void deleteMedico (Long idMedico);
	
	Optional<MedicoResponse> findByDni(Integer dni);
	Collection<MedicoResponse> findByNombreContainingIgnoreCase(String nombre);
}
