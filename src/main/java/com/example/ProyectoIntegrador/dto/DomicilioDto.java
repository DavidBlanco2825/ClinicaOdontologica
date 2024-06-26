package com.example.ProyectoIntegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DomicilioDto {
    private Long id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;

    public DomicilioDto(String calle, Integer numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
}
