package com.siscitas.citasmedicas.service;

import java.util.List; 
import com.siscitas.citasmedicas.controller.dto.CitaRequest;
import com.siscitas.citasmedicas.controller.dto.CitaResponse;


public interface CitaService {
    // 1. Visualizar Citas
    List<CitaResponse> findAllCitas(); 

    CitaResponse findByIdCita(Long idCita);

    // 2. Programar Cita
    CitaResponse programarCita(CitaRequest request); 

    // 3. Actualizar Cita
    CitaResponse updateCita(Long id, CitaRequest request);

    // 4. Cancelar Cita (o eliminar)
    void deleteCita (Long idCita); 
}