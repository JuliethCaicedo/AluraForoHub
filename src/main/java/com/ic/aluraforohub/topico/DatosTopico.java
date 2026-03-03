package com.ic.aluraforohub.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosTopico(


        @NotBlank(message = "El título no puede estar vacío")
        String titulo,

        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,

        @NotBlank(message = "El nombreCurso no puede estar vacío")
        String nombreCurso,

        @NotNull(message = "La fechaCreacion no puede estar vacío")
        LocalDate fechaCreacion,

        @NotNull(message = "El status no puede estar vacío")
        StatusTopico status,

        @Valid
        @NotNull(message = "El ID del autor no puede ser nulo")
        Long idAutor

        ) {
}
