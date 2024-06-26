package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.dto.PacienteDto;
import com.example.ProyectoIntegrador.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Pacientes", description = "Gestión de Pacientes en la aplicación Clínica Odontológica.")
public class PacienteController {

    private final PacienteService pacienteService;

    @Operation(summary = "Guardar un nuevo paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<PacienteDto> guardarPaciente(@RequestBody PacienteDto pacienteDto) {
        PacienteDto pacienteGuardado = pacienteService.guardarPaciente(pacienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteGuardado);
    }

    @Operation(summary = "Obtener todos los pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pacientes obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<PacienteDto>> buscarTodos() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @Operation(summary = "Buscar un paciente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacienteID(@PathVariable Long id) {
        return pacienteService.buscarPaciente(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar un paciente por Email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<PacienteDto> buscarPorEmail(@PathVariable String email) {
        return pacienteService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PutMapping
    public ResponseEntity<Void> actualizarPaciente(@RequestBody PacienteDto pacienteDto) {
        pacienteService.actualizarPaciente(pacienteDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Eliminar un paciente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok().build();
    }
}