package com.siscitas.citasmedicas.controller.dto;

import lombok.Data;

@Data
public class PacienteCitaResponse {
    private Long id;
    private Integer dni;
    private String nombre;
    private String apellidos;
 
}