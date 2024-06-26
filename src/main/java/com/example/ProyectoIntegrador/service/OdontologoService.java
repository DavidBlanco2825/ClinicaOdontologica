package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.config.constants.ExceptionMessages;
import com.example.ProyectoIntegrador.config.constants.LogMessages;
import com.example.ProyectoIntegrador.dto.OdontologoDto;
import com.example.ProyectoIntegrador.entity.Odontologo;
import com.example.ProyectoIntegrador.exception.DataAlreadyExistsException;
import com.example.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.example.ProyectoIntegrador.mapper.OdontologoMapper;
import com.example.ProyectoIntegrador.repository.OdontologoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Log4j2
public class OdontologoService {

    private final OdontologoRepository odontologoRepository;
    private final OdontologoMapper odontologoMapper;

    public OdontologoDto guardarOdontologo(OdontologoDto odontologoDto) {
        log.info(LogMessages.GUARDAR_ODONTOLOGO, odontologoDto.toString());
        checkIfMatriculaExists(odontologoDto.getMatricula());

        Odontologo odontologo = odontologoMapper.toEntity(odontologoDto);
        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);

        return odontologoMapper.toDto(odontologoGuardado);
    }

    private void checkIfMatriculaExists(String matricula) {
        if (buscarPorMatricula(matricula).isPresent()) {
            throw new DataAlreadyExistsException(ExceptionMessages.ODONTOLOGO_MATRICULA_YA_EXISTE + matricula);
        }
    }

    public List<OdontologoDto> buscarTodos() {
        log.info(LogMessages.BUSCAR_TODOS_ODONTOLOGOS);
        return odontologoRepository.findAll()
                .stream()
                .map(odontologoMapper::toDto)
                .toList();
    }

    public Optional<OdontologoDto> buscarOdontologo(Long id) {
        log.info(LogMessages.BUSCAR_ODONTOLOGO_ID, id);
        return odontologoRepository.findById(id)
                .map(odontologoMapper::toDto);
    }

    public Optional<OdontologoDto> buscarPorMatricula(String matricula) {
        log.info(LogMessages.BUSCAR_ODONTOLOGO_MATRICULA, matricula);
        return odontologoRepository.findByMatricula(matricula)
                .map(odontologoMapper::toDto);
    }

    public void actualizarOdontologo(OdontologoDto odontologoDto) {
        log.info(LogMessages.ACTUALIZAR_ODONTOLOGO, odontologoDto.getId());
        checkIfOdontologoExists(odontologoDto.getId());
        odontologoRepository.save(odontologoMapper.toEntity(odontologoDto));
    }

    private void checkIfOdontologoExists(Long id) {
        if (buscarOdontologo(id).isEmpty()) {
            throw new ResourceNotFoundException(ExceptionMessages.ODONTOLOGO_NO_ENCONTRADO + id);
        }
    }

    public void eliminarOdontologo(Long id) {
        log.info(LogMessages.ELIMINAR_ODONTOLOGO, id);
        odontologoRepository.deleteById(id);
    }
}
