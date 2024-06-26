package com.example.ProyectoIntegrador.mapper;

import com.example.ProyectoIntegrador.dto.DomicilioDto;
import com.example.ProyectoIntegrador.entity.Domicilio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomicilioMapper {
    DomicilioDto toDto(Domicilio domicilio);
    Domicilio toEntity(DomicilioDto domicilioDto);
}
