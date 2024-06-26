package com.example.ProyectoIntegrador.config;

import com.example.ProyectoIntegrador.entity.Domicilio;
import com.example.ProyectoIntegrador.entity.Odontologo;
import com.example.ProyectoIntegrador.entity.Paciente;
import com.example.ProyectoIntegrador.entity.Turno;
import com.example.ProyectoIntegrador.repository.OdontologoRepository;
import com.example.ProyectoIntegrador.repository.PacienteRepository;
import com.example.ProyectoIntegrador.repository.TurnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class LoadDataOnStartUp {

    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        Domicilio domicilio1 = new Domicilio(
                1L,
                "Calle Falsa",
                123,
                "Montevideo",
                "Uruguay"
        );

        Paciente paciente1 = new Paciente(
                1L,
                "Avril",
                "Tihista",
                "123456",
                LocalDate.of(2024, 6, 22),
                domicilio1,
                "avril@admin.com"
        );

        Domicilio domicilio2 = new Domicilio(
                2L,
                "Calle Siempre Viva",
                456,
                "Springfield",
                "Ohio"
        );

        Paciente paciente2 = new Paciente(
                2L,
                "David",
                "Blanco",
                "654321",
                LocalDate.of(2024, 6, 22),
                domicilio2,
                "david@admin.com"
        );

        Domicilio domicilio3 = new Domicilio(
                3L,
                "Jiron Luzuriaga",
                153,
                "Lima",
                "Perú"
        );

        Paciente paciente3 = new Paciente(
                3L,
                "Victor",
                "Falconi",
                "789456",
                LocalDate.of(2024, 6, 22),
                domicilio3,
                "victor@admin.com"
        );

        pacienteRepository.save(paciente1);
        pacienteRepository.save(paciente2);
        pacienteRepository.save(paciente3);

        Odontologo odontologo1 = new Odontologo(
                1L,
                "ABC123",
                "Sammy",
                "Cantoral"
        );

        Odontologo odontologo2 = new Odontologo(
                2L,
                "DEF456",
                "Carol",
                "Velez"
        );

        Odontologo odontologo3 = new Odontologo(
                3L,
                "GHI789",
                "Pool",
                "Hijuela"
        );

        odontologoRepository.save(odontologo1);
        odontologoRepository.save(odontologo2);
        odontologoRepository.save(odontologo3);

        Turno turno1 = new Turno(
                1L,
                paciente1,
                odontologo1,
                LocalDate.of(2024, 6, 22)
        );

        Turno turno2 = new Turno(
                2L,
                paciente2,
                odontologo2,
                LocalDate.of(2024, 6, 23)
        );

        Turno turno3 = new Turno(
                3L,
                paciente3,
                odontologo3,
                LocalDate.of(2024, 6, 24)
        );

        turnoRepository.save(turno1);
        turnoRepository.save(turno2);
        turnoRepository.save(turno3);
    }
}
