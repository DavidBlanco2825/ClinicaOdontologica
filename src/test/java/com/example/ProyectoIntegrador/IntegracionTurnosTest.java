package com.example.ProyectoIntegrador;

import com.example.ProyectoIntegrador.dto.DomicilioDto;
import com.example.ProyectoIntegrador.dto.OdontologoDto;
import com.example.ProyectoIntegrador.dto.PacienteDto;
import com.example.ProyectoIntegrador.dto.TurnoDto;
import com.example.ProyectoIntegrador.entity.Domicilio;
import com.example.ProyectoIntegrador.entity.Odontologo;
import com.example.ProyectoIntegrador.entity.Paciente;
import com.example.ProyectoIntegrador.entity.Turno;
import com.example.ProyectoIntegrador.service.OdontologoService;
import com.example.ProyectoIntegrador.service.PacienteService;
import com.example.ProyectoIntegrador.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarDatos() {
        PacienteDto pacienteGuardado = pacienteService.guardarPaciente(
                new PacienteDto("Jorgito", "Pereyra", "111111", LocalDate.of(2024, 6, 19),
                        new DomicilioDto("Calle falsa", 123, "La Rioja", "Argentina"),
                        "jorgito@digitalhouse.com"));
        OdontologoDto odontologoGuardado = odontologoService.guardarOdontologo(new OdontologoDto("MP10", "Ivan", "Bustamante"));
        TurnoDto turnoGuardado = turnoService.guardarTurno(new TurnoDto(pacienteGuardado.getId(), odontologoGuardado.getId(), LocalDate.of(2024, 6, 19)));
    }

    @Test
    public void listarTodosLosTurnos() throws Exception {
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
