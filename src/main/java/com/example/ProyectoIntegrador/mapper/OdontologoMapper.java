package com.example.ProyectoIntegrador.mapper;

import com.example.ProyectoIntegrador.dto.OdontologoDto;
import com.example.ProyectoIntegrador.entity.Odontologo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OdontologoMapper {
    OdontologoDto toDto(Odontologo odontologo);
    Odontologo toEntity(OdontologoDto odontologoDto);
}
