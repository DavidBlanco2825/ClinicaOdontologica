package com.example.ProyectoIntegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TurnoDto {
    private Long id;
    private PacienteDto paciente;
    private OdontologoDto odontologo;
    private LocalDate fechaHora;
}