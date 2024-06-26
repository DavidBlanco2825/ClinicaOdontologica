package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.dto.DomicilioDto;
import com.example.ProyectoIntegrador.dto.OdontologoDto;
import com.example.ProyectoIntegrador.dto.PacienteDto;
import com.example.ProyectoIntegrador.dto.TurnoDto;
import com.example.ProyectoIntegrador.entity.Domicilio;
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
        DomicilioDto domicilio = new DomicilioDto(1L, "Calle Falsa", 123, "Montevideo", "Uruguay");
        PacienteDto paciente = new PacienteDto(
                1L,
                "Avril",
                "Tihista",
                "123456",
                LocalDate.of(2024, 6, 22),
                domicilio,
                "avriltest@admin.com"
        );
        pacienteService.guardarPaciente(paciente);

        OdontologoDto odontologo = new OdontologoDto(1L,"A123", "David", "Blanco");
        odontologoService.guardarOdontologo(odontologo);

        TurnoDto turno = new TurnoDto(1L, paciente.getId(), odontologo.getId(), LocalDate.of(2024, 6, 22));
        TurnoDto turnoGuardado = turnoService.guardarTurno(turno);

        assertNotNull(turnoGuardado);
        assertEquals(turno.getFechaHora(), turnoGuardado.getFechaHora());
    }

    @Test
    @Order(2)
    public void buscarTurnoPorId() {
        Long id = 1L;
        Optional<TurnoDto> turnoBuscado = turnoService.buscarTurno(id);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurnoTest() {
        Optional<TurnoDto> turnoBuscado = turnoService.buscarTurno(1L);
        if (turnoBuscado.isPresent()) {
            TurnoDto turno = turnoBuscado.get();
            turno.setFechaHora(LocalDate.of(2024, 7, 1));
            turnoService.actualizarTurno(turno);
            assertEquals(LocalDate.of(2024, 7, 1), turno.getFechaHora());
        } else {
            fail("Turno no encontrado");
        }
    }

    @Test
    @Order(4)
    public void buscarTodos() {
        List<TurnoDto> turnos = turnoService.buscarTodos();
        assertNotNull(turnos);
        assertFalse(turnos.isEmpty());
    }

    @Test
    @Order(5)
    public void eliminarTurno() {
        turnoService.eliminarTurno(1L);
        Optional<TurnoDto> turnoBuscado = turnoService.buscarTurno(1L);
        assertFalse(turnoBuscado.isPresent());
    }
}
