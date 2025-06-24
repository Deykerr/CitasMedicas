package com.siscitas.citasmedicas.controller.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteRequest {

	private Integer dni;
	private String nombre;
	private String apellidos;
	private Long celular;
	private String correo;
	
}
