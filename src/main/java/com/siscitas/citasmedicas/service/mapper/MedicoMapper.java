package com.siscitas.citasmedicas.service.mapper;

import java.util.ArrayList;
import java.util.List; 

import org.springframework.stereotype.Component;

import com.siscitas.citasmedicas.controller.dto.MedicoRequest;
import com.siscitas.citasmedicas.controller.dto.MedicoResponse;
import com.siscitas.citasmedicas.model.Medico;

@Component
public class MedicoMapper {

    // Mapea una entidad Medico a un DTO MedicoResponse
    public MedicoResponse toMedicoToMedicoResponse(Medico medico) {
        MedicoResponse medicoResponse = new MedicoResponse();
        if (medico != null) {
            medicoResponse.setId(medico.getId());
            medicoResponse.setDni(medico.getDni());
            medicoResponse.setNombre(medico.getNombre());
            medicoResponse.setApellidos(medico.getApellidos());
            medicoResponse.setCelular(medico.getCelular());
            medicoResponse.setCorreo(medico.getCorreo());
            medicoResponse.setEspecialidad(medico.getEspecialidad());
        }
        return medicoResponse;
    }

    // Mapea una lista de entidades Medico a una lista de DTOs MedicoResponse
    public List<MedicoResponse> toListMedicoToMedicoResponse(List<Medico> listMedico) {
        List<MedicoResponse> listMedicoResponses = new ArrayList<>();
        if (listMedico != null && !listMedico.isEmpty()) {
            for (Medico medico : listMedico) {
                listMedicoResponses.add(toMedicoToMedicoResponse(medico)); // Reutiliza el mapeo individual
            }
        }
        return listMedicoResponses;
    }

    // Mapea un DTO MedicoRequest a una entidad Medico (para creación)
    public Medico toMedico(MedicoRequest medicoRequest) {
        if (medicoRequest == null) {
            return null;
        }
        Medico medico = new Medico();
        medico.setDni(medicoRequest.getDni());
        medico.setNombre(medicoRequest.getNombre());
        medico.setApellidos(medicoRequest.getApellidos());
        medico.setCelular(medicoRequest.getCelular());
        medico.setCorreo(medicoRequest.getCorreo());
        medico.setEspecialidad(medicoRequest.getEspecialidad());
        return medico;
    }

    // Actualiza una entidad Medico existente con datos de un DTO MedicoRequest
    public void updateMedicoFromDto(MedicoRequest medicoRequest, Medico medico) {
        if (medicoRequest == null || medico == null) {
            return;
        }
        // Asumiendo que todos los campos del DTO son actualizables
        //medico.setDni(medicoRequest.getDni());
        medico.setNombre(medicoRequest.getNombre());
        medico.setApellidos(medicoRequest.getApellidos());
        medico.setCelular(medicoRequest.getCelular());
        medico.setCorreo(medicoRequest.getCorreo());
        medico.setEspecialidad(medicoRequest.getEspecialidad());
    }
}