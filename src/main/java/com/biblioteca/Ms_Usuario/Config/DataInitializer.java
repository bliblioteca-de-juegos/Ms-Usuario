package com.biblioteca.Ms_Usuario.Config;

import com.biblioteca.Ms_Usuario.Modelo.Usuario;
import com.biblioteca.Ms_Usuario.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() > 0) {
            log.info(">>>> Usuario ya cargado");
            return;
        }
        log.info(">>>> cargando Usuario");
        usuarioRepository.save(new Usuario(null, "david", "Martinez", "email", passwordEncoder.encode("password"),"D4V1Cyberpunk"));
        log.info(">>>> Usuario cargado");
    }
}
