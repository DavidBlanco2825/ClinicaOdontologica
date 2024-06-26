package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.dto.OdontologoDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoServiceTest(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @Test
    @Order(1)
    public void guardarOdontologo() {
        OdontologoDto odontologo = new OdontologoDto("A123", "David", "Blanco");
        OdontologoDto odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        assertEquals(4L, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorId() {
        Long id = 1L;
        Optional<OdontologoDto> odontologoBuscado = odontologoService.buscarOdontologo(id);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologoTest() {
        Optional<OdontologoDto> odontologoBuscado = odontologoService.buscarOdontologo(1L);
        odontologoBuscado.ifPresent(
                odontologo -> odontologo.setApellido("Perez")
        );
        odontologoService.actualizarOdontologo(odontologoBuscado.get());
        assertEquals("Perez", odontologoBuscado.get().getApellido());
    }

    @Test
    @Order(4)
    public void buscarTodos() {
        List<OdontologoDto> odontologos = odontologoService.buscarTodos();
        assertEquals(4, odontologos.size());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo() {
        odontologoService.eliminarOdontologo(4L);
        Optional<OdontologoDto> odontologoBuscado = odontologoService.buscarOdontologo(4L);
        assertFalse(odontologoBuscado.isPresent());
    }
}

