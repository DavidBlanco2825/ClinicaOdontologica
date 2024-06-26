package com.example.ProyectoIntegrador.mapper;

import com.example.ProyectoIntegrador.dto.TurnoDto;
import com.example.ProyectoIntegrador.entity.Odontologo;
import com.example.ProyectoIntegrador.entity.Paciente;
import com.example.ProyectoIntegrador.entity.Turno;
import com.example.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.example.ProyectoIntegrador.repository.OdontologoRepository;
import com.example.ProyectoIntegrador.repository.PacienteRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {PacienteRepository.class, OdontologoRepository.class})
public interface TurnoMapper {

    @Mapping(source = "paciente.id", target = "pacienteId")
    @Mapping(source = "odontologo.id", target = "odontologoId")
    TurnoDto toDto(Turno turno);

    @Mapping(source = "pacienteId", target = "paciente", qualifiedByName = "mapPacienteIdToPaciente")
    @Mapping(source = "odontologoId", target = "odontologo", qualifiedByName = "mapOdontologoIdToOdontologo")
    Turno toEntity(TurnoDto turnoDto, @Context PacienteRepository pacienteRepository, @Context OdontologoRepository odontologoRepository);

    @Named("mapPacienteIdToPaciente")
    default Paciente mapPacienteIdToPaciente(Long pacienteId, @Context PacienteRepository pacienteRepository) {
        return pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
    }

    @Named("mapOdontologoIdToOdontologo")
    default Odontologo mapOdontologoIdToOdontologo(Long odontologoId, @Context OdontologoRepository odontologoRepository) {
        return odontologoRepository.findById(odontologoId)
                .orElseThrow(() -> new ResourceNotFoundException("Odontologo no encontrado"));
    }

    @AfterMapping
    default void toEntity(@MappingTarget Turno turno, TurnoDto turnoDto, @Context PacienteRepository pacienteRepository, @Context OdontologoRepository odontologoRepository) {
        turno.setPaciente(mapPacienteIdToPaciente(turnoDto.getPacienteId(), pacienteRepository));
        turno.setOdontologo(mapOdontologoIdToOdontologo(turnoDto.getOdontologoId(), odontologoRepository));
    }
}
