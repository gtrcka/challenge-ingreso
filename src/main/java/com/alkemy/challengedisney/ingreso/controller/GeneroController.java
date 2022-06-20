package com.alkemy.challengedisney.ingreso.controller;

import com.alkemy.challengedisney.ingreso.dto.GeneroDTO;
import com.alkemy.challengedisney.ingreso.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genre")//Definimos la base de nuestro path para poder acceder al controller
public class GeneroController {
    @Autowired//Inicializa el servicio
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll(){
        List<GeneroDTO> generos = this.generoService.getAllGeneros();
        return ResponseEntity.ok().body(generos);
    }

    //Enpoint para guardar géneros
    @PostMapping // Indico el verbo del endpoint
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero){
        //save género
        GeneroDTO generoSaved = generoService.save(genero);

        //devolvemos 201 si fue creado y el género guardado
        return ResponseEntity.status(HttpStatus.CREATED).body(generoSaved);
    }
}
