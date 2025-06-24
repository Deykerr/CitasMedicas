package com.siscitas.citasmedicas.service.impl;

import java.util.List;
import java.util.Optional; // Necesario para manejar el Optional del repositorio

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siscitas.citasmedicas.controller.dto.MedicoRequest;
import com.siscitas.citasmedicas.controller.dto.MedicoResponse;
import com.siscitas.citasmedicas.model.Medico;
import com.siscitas.citasmedicas.repository.MedicoRepository;
import com.siscitas.citasmedicas.service.MedicoService;
import com.siscitas.citasmedicas.service.mapper.MedicoMapper;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoMapper medicoMapper;

    @Override
    public List<MedicoResponse> findAllMedico() {
        List<Medico> medicos = medicoRepository.findAll();
        return medicoMapper.toListMedicoToMedicoResponse(medicos);
    }

    @Override
    public MedicoResponse findByIdMedico(Long idMedico) {
        // Mejor manejo de Optional: lanzar una excepción si no se encuentra
        Medico medico = medicoRepository.findById(idMedico)
                            .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + idMedico));
        return medicoMapper.toMedicoToMedicoResponse(medico);
    }

    @Override
    public void saveMedico(MedicoRequest request) {
        Medico medicoNew = medicoMapper.toMedico(request);
        medicoRepository.save(medicoNew);
    }

    @Override
    public void updateMedico(Long id, MedicoRequest request) {
        Medico medico = medicoRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID para actualizar: " + id));
        medicoMapper.updateMedicoFromDto(request, medico);
        medicoRepository.save(medico);
    }

    @Override
    public void deleteMedico(Long idMedico) {
        Medico medico = medicoRepository.findById(idMedico)
                            .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID para eliminar: " + idMedico));
        medicoRepository.delete(medico);
    }

    @Override
    public Optional<MedicoResponse> findByDni(Integer dni) {
        Optional<Medico> medico = medicoRepository.findByDni(dni);
        // Mapea el Optional<Medico> a Optional<MedicoResponse>
        return medico.map(medicoMapper::toMedicoToMedicoResponse);
    }

    @Override
    public List<MedicoResponse> findByNombreContainingIgnoreCase(String nombre) {
        List<Medico> medicos = medicoRepository.findByNombreContainingIgnoreCase(nombre);
        return medicoMapper.toListMedicoToMedicoResponse(medicos);
    }
}