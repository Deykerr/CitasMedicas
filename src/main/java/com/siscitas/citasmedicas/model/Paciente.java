package com.siscitas.citasmedicas.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="pacientes")
@Data
public class Paciente {
 	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 	private Integer dni;
    private String nombre;
    private String apellidos;
    private Long celular;
    private String correo;

    
    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;
}
