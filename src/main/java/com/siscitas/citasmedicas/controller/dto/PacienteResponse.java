package com.siscitas.citasmedicas.controller.dto;


import lombok.Data;

@Data
public class PacienteResponse {
    private Long id;
    private Integer dni;
    private String nombre;
    private String apellidos;
    private Long celular;
    private String correo;
}
