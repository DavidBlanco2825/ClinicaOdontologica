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