package com.example.ProyectoIntegrador.mapper;

import com.example.ProyectoIntegrador.dto.PacienteDto;
import com.example.ProyectoIntegrador.entity.Paciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteDto toDto(Paciente paciente);
    Paciente toEntity(PacienteDto pacienteDto);
}
