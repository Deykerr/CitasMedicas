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

   
    public CitaResponse toCitaToCitaResponse(Cita cita) {
        CitaResponse citaResponse = new CitaResponse();
        if (cita != null) {
            citaResponse.setIdCita(cita.getIdCita());
            citaResponse.setFechaCita(cita.getFechaCita());

            
            if (cita.getPaciente() != null) {
                citaResponse.setPaciente(toPacienteCitaResponse(cita.getPaciente()));
            }
            if (cita.getMedico() != null) {
                citaResponse.setMedico(toMedicoCitaResponse(cita.getMedico()));
            }
        }
        return citaResponse;
    }

   
    public List<CitaResponse> toListCitaToCitaResponse(List<Cita> listCita) {
        List<CitaResponse> listCitaResponses = new ArrayList<>();
        if (listCita != null && !listCita.isEmpty()) {
            for (Cita cita : listCita) {
                listCitaResponses.add(toCitaToCitaResponse(cita));
            }
        }
        return listCitaResponses;
    }


    public Cita toCita(CitaRequest citaRequest) {
        if (citaRequest == null) {
            return null;
        }
        Cita cita = new Cita();
      
        cita.setFechaCita(citaRequest.getFechaCita());
        return cita;
    }


    public void updateCitaFromDto(CitaRequest citaRequest, Cita cita) {
        if (citaRequest == null || cita == null) {
            return;
        }
        cita.setFechaCita(citaRequest.getFechaCita());
      
    }
}