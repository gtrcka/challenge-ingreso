package com.alkemy.challengedisney.ingreso.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PeliculaSerieDTO {
    private Long id;
    private Long calificacion;
    private String fechaCreacion;
    private String titulo;
    private String imagen;
    private Long generoId;
    private Set<PersonajeDTO> personajes;
}
