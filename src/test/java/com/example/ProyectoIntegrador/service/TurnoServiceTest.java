package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.entity.Domicilio;
import com.example.ProyectoIntegrador.entity.Odontologo;
import com.example.ProyectoIntegrador.entity.Paciente;
import com.example.ProyectoIntegrador.entity.Turno;
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
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {

    private final TurnoService turnoService;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoServiceTest(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Test
    @Order(1)
    public void guardarTurno() {
        Domicilio domicilio = new Domicilio(1L, "Calle Falsa", 123, "Montevideo", "Uruguay");
        Paciente paciente = new Paciente(1L, "Avril", "Tihista", "123456", LocalDate.of(2024, 6, 22), domicilio, "avril@admin.com");
        pacienteService.guardarPaciente(paciente);

        Odontologo odontologo = new Odontologo("A123", "David", "Blanco");
        odontologoService.guardarOdontologo(odontologo);

        Turno turno = new Turno(1L, paciente, odontologo, LocalDate.of(2024, 6, 22));
        Turno turnoGuardado = turnoService.guardarTurno(turno);

        assertNotNull(turnoGuardado);
        assertEquals(turno.getFechaHora(), turnoGuardado.getFechaHora());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId() {
        Long id = 1L;
        Optional<Turno> turnoBuscado = turnoService.buscarTurno(id);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurnoTest() {
        Optional<Turno> turnoBuscado = turnoService.buscarTurno(1L);
        if (turnoBuscado.isPresent()) {
            Turno turno = turnoBuscado.get();
            turno.setFechaHora(LocalDate.of(2024, 7, 1));
            turnoService.actualizar(turno);
            assertEquals(LocalDate.of(2024, 7, 1), turno.getFechaHora());
        } else {
            fail("Turno not found");
        }
    }

    @Test
    @Order(4)
    public void buscarTodos() {
        List<Turno> turnos = turnoService.buscarTodos();
        assertNotNull(turnos);
        assertFalse(turnos.isEmpty());
    }

    @Test
    @Order(5)
    public void eliminarTurno() {
        turnoService.eliminarTurno(1L);
        Optional<Turno> turnoBuscado = turnoService.buscarTurno(1L);
        assertFalse(turnoBuscado.isPresent());
    }
}
