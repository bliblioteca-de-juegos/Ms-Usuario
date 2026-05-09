package com.biblioteca.Ms_Usuario.Dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacio")
    private String email;

    @NotBlank(message = "El password no puede estar vacio")
    private String password;

    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    private String nombreUsuario;

}
