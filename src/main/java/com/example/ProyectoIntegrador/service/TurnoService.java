package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.entity.Turno;
import com.example.ProyectoIntegrador.repository.TurnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TurnoService {

    private final TurnoRepository turnoRepository;

    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    public Optional<Turno> buscarTurno(Long id) {
        return turnoRepository.findById(id);
    }

    public void actualizar(Turno turno) {
        turnoRepository.save(turno);
    }

    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }
}