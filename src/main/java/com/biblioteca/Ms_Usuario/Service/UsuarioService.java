package com.biblioteca.Ms_Usuario.Service;


import com.biblioteca.Ms_Usuario.Dto.UsuarioRequestDTO;
import com.biblioteca.Ms_Usuario.Dto.UsuarioResponseDTO;
import com.biblioteca.Ms_Usuario.Modelo.Usuario;
import com.biblioteca.Ms_Usuario.Repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private UsuarioResponseDTO mapToDto(Usuario u){
        return new UsuarioResponseDTO(u.getId(), u.getNombre(), u.getApellido(), u.getEmail(), u.getPassword(), u.getNombreUsuario());

    }

    public List<UsuarioResponseDTO> obtenerTodas(){
        return usuarioRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public Optional<UsuarioResponseDTO> obtenerPorId(Long id){
        return  usuarioRepository.findById(id).map(this::mapToDto);
    }

    public List<UsuarioResponseDTO> buscarPorNombre(String nombre){
        return usuarioRepository.buscarPorNombre(nombre).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<UsuarioResponseDTO> buscarPoremail(String email){
        return usuarioRepository.buscarPorEmail(email).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<UsuarioResponseDTO> buscarPorNombreUsuario(String nombreUsuario){
        return usuarioRepository.buscarPorNombreUsuario(nombreUsuario).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public UsuarioResponseDTO guardar(@Valid UsuarioRequestDTO dto){
        Usuario u = new Usuario(null, dto.getNombre(), dto.getApellido(), dto.getEmail(), dto.getPassword(), dto.getNombreUsuario());
        return  mapToDto(usuarioRepository.save(u));
    }

    public Optional<UsuarioResponseDTO> actualizar(Long id, @Valid UsuarioRequestDTO dto){
        return  usuarioRepository.findById(id).map(existente -> {
            existente.setNombre(dto.getNombre());
            existente.setApellido(dto.getApellido());
            existente.setEmail(dto.getEmail());
            existente.setPassword(dto.getPassword());
            existente.setNombreUsuario(dto.getNombreUsuario());
            return  mapToDto(usuarioRepository.save(existente));
        });
    }

    public void eliminar(Long id){usuarioRepository.deleteById(id);}
}
