package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.dto.TurnoDto;
import com.example.ProyectoIntegrador.service.TurnoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turnos")
@AllArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping
    public ResponseEntity<TurnoDto> registrarUnTurno(@RequestBody TurnoDto turnoDto) {
        TurnoDto turnoGuardado = turnoService.guardarTurno(turnoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoGuardado);
    }

    @GetMapping
    public ResponseEntity<List<TurnoDto>> listarTodosTurnos() {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable("id") Long id) {
        return turnoService.buscarTurno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDto turnoDto) {
        turnoService.actualizarTurno(turnoDto);
        return ResponseEntity.ok("Turno actualizado con exito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurnoPorId(@PathVariable("id") Long id) {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Turno eiminado con exito");
    }
}