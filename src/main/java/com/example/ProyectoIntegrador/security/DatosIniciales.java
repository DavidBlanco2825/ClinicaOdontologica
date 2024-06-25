package com.example.ProyectoIntegrador.security;

import com.example.ProyectoIntegrador.entity.Usuario;
import com.example.ProyectoIntegrador.entity.UsuarioRole;
import com.example.ProyectoIntegrador.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatosIniciales implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {

        Usuario basicUser = new Usuario(
                "Avril Tihista",
                UsuarioRole.ROLE_USER,
                passwordEncoder.encode("user"),
                "avril@admin.com",
                "avril_tihista"
        );

        Usuario adminUser = new Usuario(
                "David Blanco",
                UsuarioRole.ROLE_ADMIN,
                passwordEncoder.encode("admin"),
                "david@admin.com",
                "david_blanco"
        );

        Usuario adminUser2 = new Usuario(
                "Victor Falconi",
                UsuarioRole.ROLE_ADMIN,
                passwordEncoder.encode("admin"),
                "victor@admin.com",
                "victor_falconi"
        );

        usuarioRepository.save(basicUser);
        usuarioRepository.save(adminUser);
        usuarioRepository.save(adminUser2);
    }
}
