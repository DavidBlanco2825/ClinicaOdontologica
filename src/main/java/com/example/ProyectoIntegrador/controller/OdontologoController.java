package com.example.ProyectoIntegrador.controller;

import com.example.ProyectoIntegrador.dto.OdontologoDto;
import com.example.ProyectoIntegrador.service.OdontologoService;
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
public class OdontologoController {

    private final OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<OdontologoDto> registrarUnOdontologoDto(@RequestBody OdontologoDto odontologoDto) {
        OdontologoDto odontologoGuardado = odontologoService.guardarOdontologo(odontologoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoGuardado);
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDto>> listarTodosOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable("id") Long id) {
        return odontologoService.buscarOdontologo(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<OdontologoDto> buscarPorMatricula(@PathVariable String matricula) {
        return odontologoService.buscarPorMatricula(matricula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody OdontologoDto odontologo) {
        odontologoService.actualizarOdontologo(odontologo);
        return ResponseEntity.ok("Odontólogo actualizado con exito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologoPorId(@PathVariable("id") Long id) {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Odontologo eliminado con exito");
    }
}
