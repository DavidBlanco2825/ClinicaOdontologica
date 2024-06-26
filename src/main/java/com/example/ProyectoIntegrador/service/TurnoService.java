package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.dto.TurnoDto;
import com.example.ProyectoIntegrador.entity.Turno;
import com.example.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.example.ProyectoIntegrador.mapper.TurnoMapper;
import com.example.ProyectoIntegrador.repository.OdontologoRepository;
import com.example.ProyectoIntegrador.repository.PacienteRepository;
import com.example.ProyectoIntegrador.repository.TurnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;
    private final TurnoMapper turnoMapper;

    public TurnoDto guardarTurno(TurnoDto turnoDto) {
        Turno turno = turnoMapper.toEntity(turnoDto, pacienteRepository, odontologoRepository);
        Turno turnoGuardado = turnoRepository.save(turno);
        return turnoMapper.toDto(turnoGuardado);
    }


    public List<TurnoDto> buscarTodos() {
        return turnoRepository.findAll()
                .stream()
                .map(turnoMapper::toDto)
                .toList();
    }

    public Optional<TurnoDto> buscarTurno(Long id) {
        return turnoRepository.findById(id)
                .map(turnoMapper::toDto);
    }

    public void actualizarTurno(TurnoDto turnoDto) {
        Turno turnoExistente = turnoRepository.findById(turnoDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado"));

        Turno turnoActualizado = turnoMapper.toEntity(turnoDto, pacienteRepository, odontologoRepository);
        turnoActualizado.setId(turnoExistente.getId());
        turnoRepository.save(turnoActualizado);
    }

    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }
}