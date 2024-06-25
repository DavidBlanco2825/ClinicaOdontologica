package com.example.ProyectoIntegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DomicilioDto {
    private Long id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
}
