package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.dto.OdontologoDto;
import com.example.ProyectoIntegrador.service.OdontologoService;
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
@RequestMapping("/odontologos")
@AllArgsConstructor
@Tag(name = "Odontólogos", description = "Gestión de Odontólogos en la aplicación Clínica Odontológica.")
public class OdontologoController {

    private final OdontologoService odontologoService;

    @Operation(summary = "Guardar un nuevo odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<OdontologoDto> registrarUnOdontologoDto(@RequestBody OdontologoDto odontologoDto) {
        OdontologoDto odontologoGuardado = odontologoService.guardarOdontologo(odontologoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoGuardado);
    }

    @Operation(summary = "Obtener todos los odontólogos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de odontólogos obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<OdontologoDto>> listarTodosOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @Operation(summary = "Buscar un odontólogo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable("id") Long id) {
        return odontologoService.buscarOdontologo(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar un odontólogo por Matricula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado")
    })
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<OdontologoDto> buscarPorMatricula(@PathVariable String matricula) {
        return odontologoService.buscarPorMatricula(matricula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PutMapping
    public ResponseEntity<Void> actualizarOdontologo(@RequestBody OdontologoDto odontologo) {
        odontologoService.actualizarOdontologo(odontologo);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Eliminar un odontólogo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOdontologoPorId(@PathVariable("id") Long id) {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok().build();
    }
}
