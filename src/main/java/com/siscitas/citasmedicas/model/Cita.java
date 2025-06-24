package com.siscitas.citasmedicas.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "citas")
@Data
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita") 
    private Long idCita; 

    private LocalDateTime fechaCita;

   
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false) 
    private Paciente paciente; 

    // Relación ManyToOne con Medico
  
    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false) 
    private Medico medico;

}
