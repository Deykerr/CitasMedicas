package com.siscitas.citasmedicas.controller.dto;


import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CitaResponse {
    private Long idCita; 
    private LocalDateTime fechaCita;


    private PacienteCitaResponse paciente; // DTO simplificado para paciente en la cita
    private MedicoCitaResponse medico;     // DTO simplificado para médico en la cita

    // NOTA: No incluimos el estado de la cita aquí ya que mencionaste que por ahora no lo tendrás en cuenta.
}