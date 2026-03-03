package com.ic.aluraforohub.controller;


import com.ic.aluraforohub.autor.Autor;
import com.ic.aluraforohub.autor.AutorRepository;
import com.ic.aluraforohub.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosTopico datosTopico) {

        boolean yaExiste = topicoRepository.existsByTituloAndMensaje(datosTopico.titulo(), datosTopico.mensaje());

        if(yaExiste){
            throw  new RuntimeException("Ya existe un tópico con el mismo título y mensaje.");
        }

        Autor autor = autorRepository.findById(datosTopico.idAutor())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        topicoRepository.save(new Topico(datosTopico, autor));
    }

    @GetMapping
    public Page<DatosListadoTopico> datosListadoTopico(@PageableDefault(size=10,sort = {"fechaCreacion"}) Pageable paginacion){
        return  topicoRepository.findAll(paginacion)
                .map(DatosListadoTopico::new);
    }

    @GetMapping("/{id}")
    public DatosTopicoIndividual retornarDatosTopico(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }
       return new DatosTopicoIndividual(topicoOptional.get());

    }


    @Transactional
    @PutMapping("/{id}")
    public void actualizar(@PathVariable Long id, @RequestBody DatosActualizacionTopico datosActualizacionTopico){

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }

        var topico = topicoOptional.get();
        topico.actualizarInformaciones(datosActualizacionTopico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
