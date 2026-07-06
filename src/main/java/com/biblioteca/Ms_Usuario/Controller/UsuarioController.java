package com.biblioteca.Ms_Usuario.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.biblioteca.Ms_Usuario.Dto.UsuarioRequestDTO;
import com.biblioteca.Ms_Usuario.Dto.UsuarioResponseDTO;
import com.biblioteca.Ms_Usuario.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping({"/api/v2/usuario", "/api/v2/usuarios"})
@Tag(name = "Usuarios", description = "Operaciones de gestion de usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    @Operation(summary = "Listar todos los usuarios")
    public List<UsuarioResponseDTO> obtenerTodas() {
        return usuarioService.obtenerTodas();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por ID")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/buscar")
    @Operation(summary = "Buscar usuarios por nombre")
    public List<UsuarioResponseDTO> obtenerPorNombre(@RequestParam String nombre) {
        return usuarioService.buscarPorNombre(nombre);
    }
    @GetMapping("/email")
    @Operation(summary = "Buscar usuarios por email")
    public List<UsuarioResponseDTO> obtenerPorEmail(@RequestParam String email) {
        return usuarioService.buscarPoremail(email);
    }
    @PostMapping
    @Operation(summary = "Crear un usuario")
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(dto));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario")
    public ResponseEntity<UsuarioResponseDTO>  actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario")
    public ResponseEntity<UsuarioResponseDTO> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
