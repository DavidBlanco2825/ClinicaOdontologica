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
    private Long pacienteId;
    private Long odontologoId;
    private LocalDate fechaHora;

    public TurnoDto(Long pacienteId, Long odontologoId, LocalDate fechaHora) {
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
        this.fechaHora = fechaHora;
    }
}