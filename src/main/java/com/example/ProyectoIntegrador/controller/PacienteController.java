package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.dto.PacienteDto;
import com.example.ProyectoIntegrador.service.PacienteService;
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
@RequestMapping("/pacientes")
@AllArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDto> guardarPaciente(@RequestBody PacienteDto pacienteDto) {
        PacienteDto pacienteGuardado = pacienteService.guardarPaciente(pacienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteGuardado);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDto>> buscarTodos() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacienteID(@PathVariable Long id) {
        return pacienteService.buscarPaciente(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PacienteDto> buscarPorEmail(@PathVariable String email) {
        return pacienteService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody PacienteDto pacienteDto) {
        pacienteService.actualizarPaciente(pacienteDto);
        return ResponseEntity.ok("Paciente actualizado con exito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("paciente eliminado con exito");
    }
}