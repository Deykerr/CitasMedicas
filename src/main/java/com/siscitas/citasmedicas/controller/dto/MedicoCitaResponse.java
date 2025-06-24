package com.siscitas.citasmedicas.controller.dto;

import lombok.Data;

@Data
public class MedicoCitaResponse {
    private Long id;
    private String nombre;
    private String apellidos;
    private String especialidad;
   
}