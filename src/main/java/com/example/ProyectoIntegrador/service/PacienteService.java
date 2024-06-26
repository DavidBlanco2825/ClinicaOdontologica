package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.config.constants.ExceptionMessages;
import com.example.ProyectoIntegrador.config.constants.LogMessages;
import com.example.ProyectoIntegrador.dto.PacienteDto;
import com.example.ProyectoIntegrador.entity.Paciente;
import com.example.ProyectoIntegrador.exception.DataAlreadyExistsException;
import com.example.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.example.ProyectoIntegrador.mapper.PacienteMapper;
import com.example.ProyectoIntegrador.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Log4j2
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteDto guardarPaciente(PacienteDto pacienteDto) {
        log.info(LogMessages.GUARDAR_PACIENTE, pacienteDto.toString());
        checkIfEmailExists(pacienteDto.getEmail());

        Paciente paciente = pacienteMapper.toEntity(pacienteDto);
        Paciente pacienteGuardado = pacienteRepository.save(paciente);

        return pacienteMapper.toDto(pacienteGuardado);
    }

    private void checkIfEmailExists(String email) {
        if (buscarPorEmail(email).isPresent()) {
            throw new DataAlreadyExistsException(ExceptionMessages.PACIENTE_EMAIL_YA_EXISTE + email);
        }
    }

    public List<PacienteDto> buscarTodos() {
        log.info(LogMessages.BUSCAR_TODOS_PACIENTES);
        return pacienteRepository.findAll()
                .stream()
                .map(pacienteMapper::toDto)
                .toList();
    }

    public Optional<PacienteDto> buscarPaciente(Long id) {
        log.info(LogMessages.BUSCAR_PACIENTE_ID, id);
        return pacienteRepository.findById(id)
                .map(pacienteMapper::toDto);
    }

    public Optional<PacienteDto> buscarPorEmail(String email) {
        log.info(LogMessages.BUSCAR_PACIENTE_EMAIL, email);
        return pacienteRepository.findByEmail(email)
                .map(pacienteMapper::toDto);
    }

    public void actualizarPaciente(PacienteDto pacienteDto) {
        log.info(LogMessages.ACTUALIZAR_PACIENTE, pacienteDto.getId());
        checkIfPacienteExists(pacienteDto.getId());
        pacienteRepository.save(pacienteMapper.toEntity(pacienteDto));
    }

    private void checkIfPacienteExists(Long id) {
        if (buscarPaciente(id).isEmpty()) {
            throw new ResourceNotFoundException(ExceptionMessages.PACIENTE_NO_ENCONTRADO + id);
        }
    }

    public void eliminarPaciente(Long id) {
        log.info(LogMessages.ELIMINAR_PACIENTE, id);
        pacienteRepository.deleteById(id);
    }
}
