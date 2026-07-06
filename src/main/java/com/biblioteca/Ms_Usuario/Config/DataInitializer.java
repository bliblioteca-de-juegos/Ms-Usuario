package com.biblioteca.Ms_Usuario.Config;

import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.Ms_Usuario.Modelo.Usuario;
import com.biblioteca.Ms_Usuario.Repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) {
        String nombreUsuario = "D4V1Cyberpunk";
        String password = passwordEncoder.encode("password");
        usuarioRepository.buscarExactoPorNombreUsuario(nombreUsuario)
                .ifPresentOrElse(usuario -> {
                    usuario.setPassword(password);
                    usuarioRepository.save(usuario);
                    log.info(">>>> Usuario semilla actualizado: {}", nombreUsuario);
                }, () -> {
                    usuarioRepository.save(new Usuario(null, "david", "Martinez", "email", password, nombreUsuario));
                    log.info(">>>> Usuario semilla cargado: {}", nombreUsuario);
                });
    }
}
