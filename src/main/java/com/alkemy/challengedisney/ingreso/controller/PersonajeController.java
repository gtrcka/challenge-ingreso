package com.alkemy.challengedisney.ingreso.controller;

import com.alkemy.challengedisney.ingreso.dto.PersonajeDTO;
import com.alkemy.challengedisney.ingreso.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class PersonajeController {

    private PersonajeService personajeService;

    @Autowired
    public PersonajeController(PersonajeService personajeService){ this.personajeService = personajeService;}

    @GetMapping("/all")
    public ResponseEntity<List<PersonajeDTO>> getAll(){
        List<PersonajeDTO> personajes = this.personajeService.getAll();
        return ResponseEntity.ok(personajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getDetailsById(@PathVariable Long id){
        PersonajeDTO dto = this.personajeService.getDetailsById(id);
        return  ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> getByFilters(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Long age,
        @RequestParam(required = false) Double weight,
        @RequestParam(required = false) Set<Long> movies
    ){
        List<PersonajeDTO> dtos = this.personajeService.getByFilters(name, age, weight, movies);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO dto){
        PersonajeDTO result = this.personajeService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping
    public  ResponseEntity<PersonajeDTO> update(@PathVariable Long id, @RequestBody PersonajeDTO dto){
        PersonajeDTO result = this.personajeService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
