package com.biblioteca.Ms_Usuario.Controller;


import com.biblioteca.Ms_Usuario.Dto.UsuarioRequestDTO;
import com.biblioteca.Ms_Usuario.Dto.UsuarioResponseDTO;
import com.biblioteca.Ms_Usuario.Service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/usuario", "/api/usuarios"})
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponseDTO> obtenerTodas() {
        return usuarioService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<UsuarioResponseDTO> obtenerPorNombre(@RequestParam String nombre) {
        return usuarioService.buscarPorNombre(nombre);
    }

    @GetMapping("/email")
    public List<UsuarioResponseDTO> obtenerPorEmail(@RequestParam String email) {
        return usuarioService.buscarPoremail(email);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO>  actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }




}
