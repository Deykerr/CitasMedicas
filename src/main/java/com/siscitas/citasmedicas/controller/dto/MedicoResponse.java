package com.siscitas.citasmedicas.controller.dto;

import lombok.Data;

@Data
public class MedicoResponse {
    private Long id;
    private Integer dni;
    private String nombre;
    private String apellidos;
    private Long celular;
    private String correo;
    private String especialidad;
}