package com.ic.aluraforohub.domain.topico;

import java.time.LocalDate;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaCreacion,
        String nombreCurso,
        StatusTopico status,
        Long idAutor

) {

    public DatosDetalleTopico(Topico topico) {
        this(   topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getNombreCurso(),
                StatusTopico.valueOf(topico.getStatus().toString()),
                topico.getAutor().getId());
    }
}
