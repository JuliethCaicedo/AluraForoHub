package com.ic.aluraforohub.domain.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutentocation(

        @Email(message = "El correo debe ser válido")
        String login,

        String contrasena
) {
}
