package com.biblioteca.Ms_Usuario.Repository;

import com.biblioteca.Ms_Usuario.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByUsuarioId(Long usuarioId);


    @Query("SELECT m from Usuaeio m WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Usuario> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT m from Usuaeio m WHERE LOWER(m.nombreUsuario) LIKE LOWER(CONCAT('%', :nombreUsuario, '%'))")
    List<Usuario> buscarPorNombreUsuario(@Param("nombreUsuario") String nombreUsuario);

    @Query("SELECT m from email m WHERE LOWER(m.email) LIKE LOWER(CONCAT('%', :email, '%'))")
    List<Usuario> buscarPorEmail(@Param("email") String email);
}
