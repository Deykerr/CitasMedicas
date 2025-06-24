package com.siscitas.citasmedicas.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siscitas.citasmedicas.controller.dto.PacienteRequest;
import com.siscitas.citasmedicas.controller.dto.PacienteResponse;
import com.siscitas.citasmedicas.model.Paciente;
import com.siscitas.citasmedicas.repository.PacienteRepository;
import com.siscitas.citasmedicas.service.PacienteService;
import com.siscitas.citasmedicas.service.mapper.PacienteMapper;

@Service
public class PacienteServiceImpl implements PacienteService {
	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private PacienteMapper pacienteMapper;

	@Override
	public Collection<PacienteResponse> findAllPaciente() {
		Collection<Paciente> listPacienteResponses = pacienteRepository.findAll();
		return pacienteMapper.toListPacienteToPacienteResponse(listPacienteResponses);
	}

	@Override
	public PacienteResponse findByIdPaciente(Long idPaciente) {
		Paciente paciente = pacienteRepository.findById(idPaciente).orElse(null);
		return pacienteMapper.toPacienteToPacienteResponse(paciente);
	}

	@Override
	public void savePaciente(PacienteRequest request) {
		Paciente pacienteNew = pacienteMapper.toPaciente(request);

		pacienteRepository.save(pacienteNew);
	}

	@Override
	public void updatePaciente(Long id, PacienteRequest request) {
		Paciente paciente = pacienteRepository.findById(id).orElse(null);
		if (paciente != null) {
			pacienteMapper.updatePacienteFromDto(request, paciente);
			pacienteRepository.save(paciente);
		}
	}

	@Override
	public void deletePaciente(Long idPaciente) {
		Paciente paciente = pacienteRepository.findById(idPaciente).orElse(null);
		if (paciente != null) {
			pacienteRepository.delete(paciente);
		}
	}

	@Override
	public PacienteResponse findByDni(Integer dni) {
		Optional<Paciente> paciente = pacienteRepository.findByDni(dni);
		return paciente.map(pacienteMapper::toPacienteToPacienteResponse).orElse(null);

	}

	@Override
	public Collection<PacienteResponse> findByNombreContainingIgnoreCase(String nombre) {
		List<Paciente> pacientes = pacienteRepository.findByNombreContainingIgnoreCase(nombre);
		return pacienteMapper.toListPacienteToPacienteResponse(pacientes);
	}
}
