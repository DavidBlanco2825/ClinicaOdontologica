package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.dto.TurnoDto;
import com.example.ProyectoIntegrador.service.TurnoService;
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
@RequestMapping("/turnos")
@AllArgsConstructor
@Tag(name = "Turnos", description = "Gestión de Turnos en la aplicación Clínica Odontológica.")
public class TurnoController {

    private final TurnoService turnoService;

    @Operation(summary = "Guardar un nuevo turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<TurnoDto> registrarUnTurno(@RequestBody TurnoDto turnoDto) {
        TurnoDto turnoGuardado = turnoService.guardarTurno(turnoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoGuardado);
    }

    @Operation(summary = "Obtener todos los turnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de turnos obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<TurnoDto>> listarTodosTurnos() {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @Operation(summary = "Buscar un turno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable("id") Long id) {
        return turnoService.buscarTurno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDto turnoDto) {
        turnoService.actualizarTurno(turnoDto);
        return ResponseEntity.ok("Turno actualizado con exito");
    }

    @Operation(summary = "Eliminar un turno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurnoPorId(@PathVariable("id") Long id) {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Turno eiminado con exito");
    }
}