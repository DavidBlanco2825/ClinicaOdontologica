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
        String passSinCifrar = "admin";
        String passCifrado = passwordEncoder.encode(passSinCifrar);
        Usuario usuario = new Usuario("jorgito", UsuarioRole.ROLE_USER, passCifrado, "admin@admin.com", "jpereyradh");
        Usuario usuario2 = new Usuario("Avril", UsuarioRole.ROLE_ADMIN, passCifrado, "avril@admin.com", "Avril");
        System.out.println("pass cifrado: " + passCifrado);
        usuarioRepository.save(usuario);
        usuarioRepository.save(usuario2);
    }
}
