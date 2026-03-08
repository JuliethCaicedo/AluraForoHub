package com.ic.aluraforohub.domain.topico;

import java.time.LocalDate;

public record DatosTopicoIndividual(

        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        String nombreCurso,
        StatusTopico status,
        Long idAutor
) {
    public DatosTopicoIndividual(Topico topico) {
        this(   topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getNombreCurso(),
                StatusTopico.valueOf(topico.getStatus().toString()),
                topico.getAutor().getId());
    }
}
