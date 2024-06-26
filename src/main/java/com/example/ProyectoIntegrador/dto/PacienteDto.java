package com.example.ProyectoIntegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PacienteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private LocalDate fechaIngreso;
    private DomicilioDto domicilio;
    private String email;

    public PacienteDto(String nombre, String apellido, String cedula, LocalDate fechaIngreso, DomicilioDto domicilio, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }
}
