package com.siscitas.citasmedicas.service.mapper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.siscitas.citasmedicas.controller.dto.PacienteRequest;
import com.siscitas.citasmedicas.controller.dto.PacienteResponse;
import com.siscitas.citasmedicas.model.Paciente;


@Component
public class PacienteMapper {

	public Collection<PacienteResponse> toListPacienteToPacienteResponse(Collection<Paciente> listPaciente) {
		Collection<PacienteResponse> listPacienteResponses = new ArrayList<>();

		if (listPaciente != null && !listPaciente.isEmpty()) {
			for (Paciente paciente : listPaciente) {
				PacienteResponse pacienteResponse = new PacienteResponse();
				pacienteResponse.setId(paciente.getId());
				pacienteResponse.setDni(paciente.getDni());
				pacienteResponse.setNombre(paciente.getNombre());
				pacienteResponse.setApellidos(paciente.getApellidos());
				pacienteResponse.setCelular(paciente.getCelular());
				pacienteResponse.setCorreo(paciente.getCorreo());
				listPacienteResponses.add(pacienteResponse);
			}
		}

		return listPacienteResponses;
	}

	public PacienteResponse toPacienteToPacienteResponse(Paciente paciente) {
		PacienteResponse pacienteResponse = new PacienteResponse();
		if (paciente != null) {
			pacienteResponse.setId(paciente.getId());
			pacienteResponse.setNombre(paciente.getNombre());
			pacienteResponse.setDni(paciente.getDni());
			pacienteResponse.setApellidos(paciente.getApellidos());
			pacienteResponse.setCelular(paciente.getCelular());
			pacienteResponse.setCorreo(paciente.getCorreo());

		}
		return pacienteResponse;
	}

//Añadir
	public Paciente toPaciente(PacienteRequest pacienteRequest) {
		if (pacienteRequest == null) {
			return null;
		}
		Paciente paciente = new Paciente();
		paciente.setDni(pacienteRequest.getDni());
		paciente.setNombre(pacienteRequest.getNombre());
		paciente.setApellidos(pacienteRequest.getApellidos());
		paciente.setCelular(pacienteRequest.getCelular());
		paciente.setCorreo(pacienteRequest.getCorreo());
		return paciente;
	}

	// Para actualizar un paciente existente
	public void updatePacienteFromDto(PacienteRequest pacienteRequest, Paciente paciente) {
		if (pacienteRequest == null || paciente == null) {
			return;
		}
		//paciente.setDni(pacienteRequest.getDni());
		paciente.setNombre(pacienteRequest.getNombre());
		paciente.setApellidos(pacienteRequest.getApellidos());
		paciente.setCelular(pacienteRequest.getCelular());
		paciente.setCorreo(pacienteRequest.getCorreo());
		
	}
}
