package com.ic.aluraforohub.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
        String titulo,
        String mensaje,
        String nombreCurso

        ) {
}
