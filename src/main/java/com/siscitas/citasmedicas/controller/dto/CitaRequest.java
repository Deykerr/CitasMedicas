package com.siscitas.citasmedicas.controller.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitaRequest {

	private Long idPaciente;

	private Long idMedico;

	private LocalDateTime fechaCita;

}