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
    private PacienteRepository pacienteRepository; // Necesario para buscar el paciente por ID

    @Autowired
    private MedicoRepository medicoRepository;     // Necesario para buscar el médico por ID

    @Autowired
    private CitaMapper citaMapper;

    // Métodos de Visualización
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

    // Programar Cita - Lógica simplificada
    @Override
    public CitaResponse programarCita(CitaRequest request) {
        // 1. Verificar si paciente existe
        Paciente paciente = pacienteRepository.findById(request.getIdPaciente())
                                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + request.getIdPaciente()));

        // 2. Verificar si médico existe
        Medico medico = medicoRepository.findById(request.getIdMedico())
                                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + request.getIdMedico()));

        // ** Lógica de verificación de disponibilidad del médico IGNORADA POR AHORA **
        // Esto se añadiría más adelante si es necesario, buscando citas existentes para el médico
        // en el rango de fecha/hora de la nueva cita.

        // 3. Mapear request a entidad Cita y asociar paciente/medico
        Cita newCita = citaMapper.toCita(request);
        newCita.setPaciente(paciente); // Asigna el objeto Paciente
        newCita.setMedico(medico);     // Asigna el objeto Medico

        // 4. Guardar la cita
        Cita savedCita = citaRepository.save(newCita);

        // 5. Retornar el DTO de respuesta
        return citaMapper.toCitaToCitaResponse(savedCita);
    }

    // Actualizar Cita - Lógica simplificada
    @Override
    public CitaResponse updateCita(Long id, CitaRequest request) {
        Cita existingCita = citaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID para actualizar: " + id));

        // ** Lógica de re-verificación de disponibilidad del médico IGNORADA POR AHORA **
        // Si la fecha de la cita cambia, normalmente se debería re-validar la disponibilidad.
        // Esto se añadiría más adelante.

        // Mapear los campos actualizables (solo fechaCita en el mapper)
        citaMapper.updateCitaFromDto(request, existingCita);

        // Opcional: Si los IDs de paciente o médico pueden cambiar en una actualización,
        // tendrías que buscar y reasignar aquí, similar a `programarCita`.
        // Por simplicidad actual, asumimos que no cambian en `updateCita`.
        // Si (request.getIdPaciente() != null && !request.getIdPaciente().equals(existingCita.getPaciente().getId())) { ... }
        // Si (request.getIdMedico() != null && !request.getIdMedico().equals(existingCita.getMedico().getId())) { ... }

        Cita updatedCita = citaRepository.save(existingCita);
        return citaMapper.toCitaToCitaResponse(updatedCita);
    }

    // Cancelar Cita
    @Override
    public void deleteCita(Long idCita) {
        Cita cita = citaRepository.findById(idCita)
                            .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID para eliminar: " + idCita));
        citaRepository.delete(cita);
    }
}