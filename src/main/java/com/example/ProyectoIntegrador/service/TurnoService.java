package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.config.constants.ExceptionMessages;
import com.example.ProyectoIntegrador.config.constants.LogMessages;
import com.example.ProyectoIntegrador.dto.TurnoDto;
import com.example.ProyectoIntegrador.entity.Turno;
import com.example.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.example.ProyectoIntegrador.mapper.TurnoMapper;
import com.example.ProyectoIntegrador.repository.OdontologoRepository;
import com.example.ProyectoIntegrador.repository.PacienteRepository;
import com.example.ProyectoIntegrador.repository.TurnoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;
    private final TurnoMapper turnoMapper;

    public TurnoDto guardarTurno(TurnoDto turnoDto) {
        log.info(LogMessages.GUARDAR_TURNO, turnoDto.toString());
        Turno turno = turnoMapper.toEntity(turnoDto, pacienteRepository, odontologoRepository);
        Turno turnoGuardado = turnoRepository.save(turno);
        return turnoMapper.toDto(turnoGuardado);
    }


    public List<TurnoDto> buscarTodos() {
        log.info(LogMessages.BUSCAR_TODOS_TURNOS);
        return turnoRepository.findAll()
                .stream()
                .map(turnoMapper::toDto)
                .toList();
    }

    public Optional<TurnoDto> buscarTurno(Long id) {
        log.info(LogMessages.BUSCAR_TURNO_ID, id);
        return turnoRepository.findById(id)
                .map(turnoMapper::toDto);
    }

    public void actualizarTurno(TurnoDto turnoDto) {
        log.info(LogMessages.ACTUALIZAR_TURNO, turnoDto.getId());
        Turno turnoExistente = turnoRepository.findById(turnoDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        ExceptionMessages.TURNO_NO_ENCONTRADO + turnoDto.getId())
                );

        Turno turnoActualizado = turnoMapper.toEntity(turnoDto, pacienteRepository, odontologoRepository);
        turnoActualizado.setId(turnoExistente.getId());

        turnoRepository.save(turnoActualizado);
    }

    public void eliminarTurno(Long id) {
        log.info(LogMessages.ELIMINAR_TURNO, id);
        turnoRepository.deleteById(id);
    }
}