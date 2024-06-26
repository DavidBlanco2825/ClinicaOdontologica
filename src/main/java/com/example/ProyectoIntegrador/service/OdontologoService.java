package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.dto.OdontologoDto;
import com.example.ProyectoIntegrador.entity.Odontologo;
import com.example.ProyectoIntegrador.exception.DataAlreadyExistsException;
import com.example.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.example.ProyectoIntegrador.mapper.OdontologoMapper;
import com.example.ProyectoIntegrador.repository.OdontologoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class OdontologoService {

    private final OdontologoRepository odontologoRepository;
    private final OdontologoMapper odontologoMapper;

    public OdontologoDto guardarOdontologo(OdontologoDto odontologoDto) {
        checkIfMatriculaExists(odontologoDto.getMatricula());

        Odontologo odontologo = odontologoMapper.toEntity(odontologoDto);
        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);

        return odontologoMapper.toDto(odontologoGuardado);
    }

    private void checkIfMatriculaExists(String matricula) {
        if (buscarPorMatricula(matricula).isPresent()) {
            throw new DataAlreadyExistsException("Ya existe un paciente con correo: " + matricula);
        }
    }

    public List<OdontologoDto> buscarTodos() {
        return odontologoRepository.findAll()
                .stream()
                .map(odontologoMapper::toDto)
                .toList();
    }

    public Optional<OdontologoDto> buscarOdontologo(Long id) {
        return odontologoRepository.findById(id)
                .map(odontologoMapper::toDto);
    }

    public Optional<OdontologoDto> buscarPorMatricula(String matricula) {
        return odontologoRepository.findByMatricula(matricula)
                .map(odontologoMapper::toDto);
    }

    public void actualizarOdontologo(OdontologoDto odontologoDto) {
        Odontologo odontologoExistente = odontologoRepository.findById(odontologoDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Odontologo no encontrado"));
        odontologoRepository.save(odontologoMapper.toEntity(odontologoDto));
    }

    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }
}
