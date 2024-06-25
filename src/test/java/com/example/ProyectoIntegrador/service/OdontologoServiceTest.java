package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.entity.Odontologo;
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

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo() {
        Odontologo odontologo = new Odontologo("A123", "David", "Blanco");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        assertEquals(4L, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorId() {
        Long id = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologoTest() {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(1L);
        if (odontologoBuscado.isPresent()) {
            odontologoBuscado.get().setApellido("Perez");
        }
        odontologoService.actualizar(odontologoBuscado.get());
        assertEquals("Perez", odontologoBuscado.get().getApellido());
    }

    @Test
    @Order(4)
    public void buscarTodos() {
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        assertEquals(3, odontologos.size());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo() {
        odontologoService.eliminarOdontologo(1L);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(1L);
        assertFalse(odontologoBuscado.isPresent());
    }
}

