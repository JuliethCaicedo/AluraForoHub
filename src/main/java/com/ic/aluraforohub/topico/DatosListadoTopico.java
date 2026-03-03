package com.ic.aluraforohub.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosListadoTopico(

        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        String nombreCurso,
        StatusTopico status,
        Long idAutor

) {

    public DatosListadoTopico(Topico topico) {
        this(   topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getNombreCurso(),
                StatusTopico.valueOf(topico.getStatus().toString()),
                topico.getAutor().getId());
    }
}