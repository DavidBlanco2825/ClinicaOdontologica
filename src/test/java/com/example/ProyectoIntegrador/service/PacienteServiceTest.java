package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.dto.DomicilioDto;
import com.example.ProyectoIntegrador.dto.PacienteDto;
import com.example.ProyectoIntegrador.entity.Domicilio;
import com.example.ProyectoIntegrador.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteServiceTest(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Test
    @Order(1)
    public void guardarPaciente() {
        PacienteDto paciente = new PacienteDto(
                "Jorgito",
                "Pereyra",
                "111111",
                LocalDate.of(2024, 6, 19),
                new DomicilioDto("Calle falsa", 123, "La Rioja", "Argentina"),
                "jorgito@digitalhouse.com"
        );
        PacienteDto pacienteGuardado = pacienteService.guardarPaciente(paciente);
        assertEquals(4L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorId() {
        Long id = 1L;
        Optional<PacienteDto> pacienteBuscado = pacienteService.buscarPaciente(id);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarPacienteTest() {
        Optional<PacienteDto> pacienteBuscado = pacienteService.buscarPaciente(1L);
        pacienteBuscado.ifPresent(
                paciente -> paciente.setApellido("Perez")
        );
        pacienteService.actualizarPaciente(pacienteBuscado.get());
        assertEquals("Perez", pacienteBuscado.get().getApellido());
    }

    @Test
    @Order(4)
    public void buscarTodos() {
        List<PacienteDto> pacientes = pacienteService.buscarTodos();
        assertEquals(4, pacientes.size());
    }

    @Test
    @Order(5)
    public void eliminarPaciente() {
        pacienteService.eliminarPaciente(4L);
        Optional<PacienteDto> pacienteBuscado = pacienteService.buscarPaciente(4L);
        assertFalse(pacienteBuscado.isPresent());
    }
}
