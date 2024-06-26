package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.dto.PacienteDto;
import com.example.ProyectoIntegrador.entity.Domicilio;
import com.example.ProyectoIntegrador.entity.Paciente;
import com.example.ProyectoIntegrador.exception.DataAlreadyExistsException;
import com.example.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.example.ProyectoIntegrador.mapper.DomicilioMapper;
import com.example.ProyectoIntegrador.mapper.PacienteMapper;
import com.example.ProyectoIntegrador.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;
    private final DomicilioMapper domicilioMapper;

    public PacienteDto guardarPaciente(PacienteDto pacienteDto) {
        checkIfEmailExists(pacienteDto.getEmail());

        Paciente paciente = pacienteMapper.toEntity(pacienteDto);

        Paciente pacienteGuardado = pacienteRepository.save(paciente);

        return pacienteMapper.toDto(pacienteGuardado);
    }

    private void checkIfEmailExists(String email) {
        if (buscarPorEmail(email).isPresent()) {
            throw new DataAlreadyExistsException("Ya existe un paciente con correo: " + email);
        }
    }

    public List<PacienteDto> buscarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(pacienteMapper::toDto)
                .toList();
    }

    public Optional<PacienteDto> buscarPaciente(Long id) {
        return pacienteRepository.findById(id)
                .map(pacienteMapper::toDto);
    }

    public Optional<PacienteDto> buscarPorEmail(String email) {
        return pacienteRepository.findByEmail(email)
                .map(pacienteMapper::toDto);
    }

    public void actualizarPaciente(PacienteDto pacienteDto) {
        Paciente pacienteExistente = pacienteRepository.findById(pacienteDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        pacienteRepository.save(pacienteMapper.toEntity(pacienteDto));
    }

    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}
