package com.biblioteca.Ms_Usuario.Controller;

import com.biblioteca.Ms_Usuario.Dto.AuthResponseDTO;
import com.biblioteca.Ms_Usuario.Dto.LoginRequestDTO;
import com.biblioteca.Ms_Usuario.Dto.UsuarioRequestDTO;
import com.biblioteca.Ms_Usuario.Dto.UsuarioResponseDTO;
import com.biblioteca.Ms_Usuario.Repository.UsuarioRepository;
import com.biblioteca.Ms_Usuario.Security.JwtService;
import com.biblioteca.Ms_Usuario.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v2/auth")
@Tag(name = "Autenticacion", description = "Operaciones de registro e inicio de sesion")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @PostMapping("/register")
    @Operation(summary = "Registrar un usuario")
    public ResponseEntity<UsuarioResponseDTO> register(@Valid @RequestBody UsuarioRequestDTO dto) {
        if (usuarioRepository.buscarExactoPorNombreUsuario(dto.getNombreUsuario()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        UsuarioResponseDTO usuario = usuarioService.guardar(dto);
        log.info("Registro completado para usuario: {}", usuario.getNombreUsuario());
        return ResponseEntity.created(URI.create("/api/v2/usuarios/" + usuario.getId())).body(usuario);
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesion y obtener JWT")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getNombreUsuario(),
                dto.getPassword()));

        UserDetails usuario = userDetailsService.loadUserByUsername(dto.getNombreUsuario());
        String token = jwtService.generarToken(usuario);
        log.info("Login correcto para usuario: {}", dto.getNombreUsuario());
        return ResponseEntity.ok(new AuthResponseDTO(token, "Bearer"));
    }
}
