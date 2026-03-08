package com.ic.aluraforohub.controller;


import com.ic.aluraforohub.domain.autor.Autor;
import com.ic.aluraforohub.domain.autor.AutorRepository;
import com.ic.aluraforohub.domain.topico.*;
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
import org.springframework.web.util.UriComponentsBuilder;


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
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosTopico datosTopico, UriComponentsBuilder uriComponentsBuilder) {

        boolean yaExiste = topicoRepository.existsByTituloAndMensaje(datosTopico.titulo(), datosTopico.mensaje());

        if(yaExiste){
            throw  new RuntimeException("Ya existe un tópico con el mismo título y mensaje.");
        }

        Autor autor = autorRepository.findById(datosTopico.idAutor())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Topico topico = new Topico(datosTopico, autor);
        topicoRepository.save(topico);

        var uri =uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> datosListadoTopico(@PageableDefault(size=10,sort = {"fechaCreacion"}) Pageable paginacion){

        var page =  topicoRepository.findAll(paginacion)
                .map(DatosListadoTopico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosTopicoIndividual> retornarDatosTopico(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }
       var topicoIndividual = new DatosTopicoIndividual(topicoOptional.get());

        return  ResponseEntity.ok(topicoIndividual);
    }


    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody DatosActualizacionTopico datosActualizacionTopico){

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }
        var topico = topicoOptional.get();
        topico.actualizarInformaciones(datosActualizacionTopico);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
