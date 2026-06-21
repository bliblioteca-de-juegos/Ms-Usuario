package com.biblioteca.Ms_Usuario.Service;

import com.biblioteca.Ms_Usuario.Dto.UsuarioRequestDTO;
import com.biblioteca.Ms_Usuario.Dto.UsuarioResponseDTO;
import com.biblioteca.Ms_Usuario.Modelo.Usuario;
import com.biblioteca.Ms_Usuario.Repository.UsuarioRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UsuarioService usuarioService;

    private final Faker faker = new Faker();

    @Test
    void guardarCodificaLaContrasenaYOcultaElHash() {
        String password = faker.internet().password();
        String hash = "$2a$10$hash-de-prueba";
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                password,
                faker.internet().username()
        );
        when(passwordEncoder.encode(password)).thenReturn(hash);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuario = invocation.getArgument(0);
            usuario.setId(1L);
            return usuario;
        });

        UsuarioResponseDTO resultado = usuarioService.guardar(dto);

        assertEquals(1L, resultado.getId());
        assertEquals(dto.getNombreUsuario(), resultado.getNombreUsuario());
        assertNull(resultado.getPassword());
        verify(passwordEncoder).encode(password);
    }

    @Test
    void obtenerPorIdRetornaVacioCuandoElUsuarioNoExiste() {
        Long id = faker.number().numberBetween(1L, 1000L);
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        Optional<UsuarioResponseDTO> resultado = usuarioService.obtenerPorId(id);

        assertFalse(resultado.isPresent());
    }
}
