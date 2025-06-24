package com.siscitas.citasmedicas.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.siscitas.citasmedicas.controller.dto.CitaRequest;
import com.siscitas.citasmedicas.controller.dto.CitaResponse;
import com.siscitas.citasmedicas.controller.dto.MedicoCitaResponse;
import com.siscitas.citasmedicas.controller.dto.PacienteCitaResponse;
import com.siscitas.citasmedicas.model.Cita;
import com.siscitas.citasmedicas.model.Medico;
import com.siscitas.citasmedicas.model.Paciente;

@Component
public class CitaMapper {

    // Mapea un Paciente a su DTO simplificado PacienteCitaResponse
    public PacienteCitaResponse toPacienteCitaResponse(Paciente paciente) {
        if (paciente == null) {
            return null;
        }
        PacienteCitaResponse pacienteCitaResponse = new PacienteCitaResponse();
        pacienteCitaResponse.setId(paciente.getId());
        pacienteCitaResponse.setDni(paciente.getDni());
        pacienteCitaResponse.setNombre(paciente.getNombre());
        pacienteCitaResponse.setApellidos(paciente.getApellidos());
        return pacienteCitaResponse;
    }

    // Mapea un Medico a su DTO simplificado MedicoCitaResponse
    public MedicoCitaResponse toMedicoCitaResponse(Medico medico) {
        if (medico == null) {
            return null;
        }
        MedicoCitaResponse medicoCitaResponse = new MedicoCitaResponse();
        medicoCitaResponse.setId(medico.getId());
        medicoCitaResponse.setNombre(medico.getNombre());
        medicoCitaResponse.setApellidos(medico.getApellidos());
        medicoCitaResponse.setEspecialidad(medico.getEspecialidad());
        return medicoCitaResponse;
    }

    // Mapea una entidad Cita a un DTO CitaResponse
    // Este método dependerá de que las entidades Paciente y Medico estén cargadas en la Cita
    public CitaResponse toCitaToCitaResponse(Cita cita) {
        CitaResponse citaResponse = new CitaResponse();
        if (cita != null) {
            citaResponse.setIdCita(cita.getIdCita());
            citaResponse.setFechaCita(cita.getFechaCita());

            // Mapea las relaciones a sus DTOs anidados
            if (cita.getPaciente() != null) {
                citaResponse.setPaciente(toPacienteCitaResponse(cita.getPaciente()));
            }
            if (cita.getMedico() != null) {
                citaResponse.setMedico(toMedicoCitaResponse(cita.getMedico()));
            }
        }
        return citaResponse;
    }

    // Mapea una lista de entidades Cita a una lista de DTOs CitaResponse
    public List<CitaResponse> toListCitaToCitaResponse(List<Cita> listCita) {
        List<CitaResponse> listCitaResponses = new ArrayList<>();
        if (listCita != null && !listCita.isEmpty()) {
            for (Cita cita : listCita) {
                listCitaResponses.add(toCitaToCitaResponse(cita)); // Reutiliza el mapeo individual
            }
        }
        return listCitaResponses;
    }

    // Mapea un DTO CitaRequest a una entidad Cita (para creación)
    // NOTA: Este método solo mapea los campos directos de CitaRequest.
    // Las entidades Paciente y Medico se asociarán en la capa de servicio
    // buscando los IDs proporcionados.
    public Cita toCita(CitaRequest citaRequest) {
        if (citaRequest == null) {
            return null;
        }
        Cita cita = new Cita();
        // Los IDs del paciente y médico no se setean directamente aquí en la entidad Cita
        // porque la entidad Cita espera los objetos Paciente y Medico, no solo sus IDs.
        // La asignación de los objetos Paciente y Medico se hará en el servicio.
        cita.setFechaCita(citaRequest.getFechaCita());
        return cita;
    }

    // Actualiza una entidad Cita existente con datos de un DTO CitaRequest
    // Similar al toCita, solo actualiza los campos directos.
    // La actualización de paciente/medico para una cita existente (si fuera permitido)
    // requeriría lógica adicional en el servicio para buscar y reasignar las entidades.
    public void updateCitaFromDto(CitaRequest citaRequest, Cita cita) {
        if (citaRequest == null || cita == null) {
            return;
        }
        cita.setFechaCita(citaRequest.getFechaCita());
        // No actualizamos paciente ni medico aquí directamente.
        // Si el idPaciente o idMedico cambian en el request, la lógica para reasignar
        // las entidades Paciente/Medico debe residir en el servicio.
    }
}