package com.siscitas.citasmedicas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="medicos")
@Data
public class Medico {
 	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 	private Integer dni;
    private String nombre;
    private String apellidos;
    private Long celular;
    private String correo;
    private String especialidad;

    
    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;
}
