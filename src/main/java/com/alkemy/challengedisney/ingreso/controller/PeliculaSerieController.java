package com.alkemy.challengedisney.ingreso.controller;

import com.alkemy.challengedisney.ingreso.dto.PeliculaSerieBasicDTO;
import com.alkemy.challengedisney.ingreso.dto.PeliculaSerieDTO;
import com.alkemy.challengedisney.ingreso.entity.PeliculaSerieEntity;
import com.alkemy.challengedisney.ingreso.entity.PersonajeEntity;
import com.alkemy.challengedisney.ingreso.service.PeliculaSerieService;
import com.alkemy.challengedisney.ingreso.service.PersonajeService;
import com.alkemy.challengedisney.ingreso.service.impl.PeliculaSerieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class PeliculaSerieController {


    private PeliculaSerieService peliculaSerieService;

    @Autowired
    public PeliculaSerieController(PeliculaSerieService peliculaSerieService){this.peliculaSerieService = peliculaSerieService;}

    @GetMapping("/all")
    public ResponseEntity<List<PeliculaSerieBasicDTO>> getAll(){
    List<PeliculaSerieBasicDTO> movies =this.peliculaSerieService.getAll();
    return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaSerieDTO> getDetailsById(@PathVariable Long id){
        PeliculaSerieDTO movie =this.peliculaSerieService.getDetailsById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    public ResponseEntity<List<PeliculaSerieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String fechaCreacion,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false, defaultValue = "ASC") String order
            ){
        List<PeliculaSerieDTO> movies = this.peliculaSerieService.getByFilters(titulo, fechaCreacion, genero, order);
        return ResponseEntity.ok(movies);
    }

    @PostMapping
    public ResponseEntity<PeliculaSerieDTO> save(@RequestBody PeliculaSerieDTO movie){
        PeliculaSerieDTO result = this.peliculaSerieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{id}")
    public ResponseEntity<PeliculaSerieDTO> update(@PathVariable Long id, @RequestBody PeliculaSerieDTO movie){
        PeliculaSerieDTO result =this.peliculaSerieService.update(id, movie);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.peliculaSerieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
