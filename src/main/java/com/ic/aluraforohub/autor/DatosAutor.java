package com.ic.aluraforohub.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutor(

        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @Email(message = "El correo debe ser válido")
        String correoElectronico,

        String password
) {
}
