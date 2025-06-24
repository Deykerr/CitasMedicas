package com.siscitas.citasmedicas.controller.dto;

import java.time.LocalDateTime;



import lombok.Getter;
import lombok.Setter;
// Opcional: Importaciones para validación
// import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CitaRequest {
   // @NotNull(message = "El ID del paciente no puede ser nulo")
    private Long idPaciente; // Se espera el ID del paciente

   // @NotNull(message = "El ID del médico no puede ser nulo")
    private Long idMedico;   // Se espera el ID del médico

   // @NotNull(message = "La fecha y hora de la cita no pueden ser nulas")
    private LocalDateTime fechaCita;

    // NOTA: El estado de la cita no se incluye aquí, ya que se gestionaría internamente en el servicio
    // y no se espera que el cliente lo envíe al crear la cita.
}