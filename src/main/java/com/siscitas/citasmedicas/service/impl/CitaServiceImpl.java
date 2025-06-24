package com.siscitas.citasmedicas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siscitas.citasmedicas.controller.dto.CitaRequest;
import com.siscitas.citasmedicas.controller.dto.CitaResponse;
import com.siscitas.citasmedicas.model.Cita;
import com.siscitas.citasmedicas.model.Medico;
import com.siscitas.citasmedicas.model.Paciente;
import com.siscitas.citasmedicas.repository.CitaRepository;
import com.siscitas.citasmedicas.repository.MedicoRepository;
import com.siscitas.citasmedicas.repository.PacienteRepository;
import com.siscitas.citasmedicas.service.CitaService;
import com.siscitas.citasmedicas.service.mapper.CitaMapper;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository; 

    @Autowired
    private MedicoRepository medicoRepository;   

    @Autowired
    private CitaMapper citaMapper;

    @Override
    public List<CitaResponse> findAllCitas() {
        List<Cita> citas = citaRepository.findAll();
        return citaMapper.toListCitaToCitaResponse(citas);
    }

    @Override
    public CitaResponse findByIdCita(Long idCita) {
        Cita cita = citaRepository.findById(idCita)
                            .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + idCita));
        return citaMapper.toCitaToCitaResponse(cita);
    }


    @Override
    public CitaResponse programarCita(CitaRequest request) {
    
        Paciente paciente = pacienteRepository.findById(request.getIdPaciente())
                                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + request.getIdPaciente()));

        Medico medico = medicoRepository.findById(request.getIdMedico())
                                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + request.getIdMedico()));

    
        Cita newCita = citaMapper.toCita(request);
        newCita.setPaciente(paciente);
        newCita.setMedico(medico);    


        Cita savedCita = citaRepository.save(newCita);

      
        return citaMapper.toCitaToCitaResponse(savedCita);
    }


    @Override
    public CitaResponse updateCita(Long id, CitaRequest request) {
        Cita existingCita = citaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID para actualizar: " + id));

    

      
        citaMapper.updateCitaFromDto(request, existingCita);

      
        Cita updatedCita = citaRepository.save(existingCita);
        return citaMapper.toCitaToCitaResponse(updatedCita);
    }

    @Override
    public void deleteCita(Long idCita) {
        Cita cita = citaRepository.findById(idCita)
                            .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID para eliminar: " + idCita));
        citaRepository.delete(cita);
    }
}