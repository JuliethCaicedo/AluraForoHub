package com.ic.aluraforohub.domain.topico;

import com.ic.aluraforohub.domain.autor.Autor;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "topicos")
@Entity(name = "topico")
@EqualsAndHashCode(of = "id")

public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fechaCreacion;
    private String nombreCurso;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;


    public Topico(DatosTopico datosTopico, Autor autor) {
        this.titulo = datosTopico.titulo();
        this.mensaje = datosTopico.mensaje();
        this.nombreCurso = datosTopico.nombreCurso();
        this.status = datosTopico.status();
        this.autor = autor;
    }


    public void actualizarInformaciones(@Valid DatosActualizacionTopico datosActualizacionTopico) {

        if (datosActualizacionTopico.nombreCurso() != null){
            this.nombreCurso = datosActualizacionTopico.nombreCurso();
        }
        if (datosActualizacionTopico.titulo() != null){
            this.titulo = datosActualizacionTopico.titulo();
        }
        if (datosActualizacionTopico.mensaje() != null){
            this.mensaje = datosActualizacionTopico.mensaje();
        }
        if(datosActualizacionTopico.status() != null){
            this.status = datosActualizacionTopico.status();
        }
    }

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDate.now();
    }

}
