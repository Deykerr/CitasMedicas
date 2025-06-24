package com.siscitas.citasmedicas.controller.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CitaResponse {
	private Long idCita;
	private LocalDateTime fechaCita;

	private PacienteCitaResponse paciente;
	private MedicoCitaResponse medico;
}