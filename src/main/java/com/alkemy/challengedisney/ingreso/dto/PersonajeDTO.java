package com.alkemy.challengedisney.ingreso.dto;

import com.alkemy.challengedisney.ingreso.entity.PeliculaSerieEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonajeDTO {
    private Long id;
    private Long edad;
    private String nombre;
    private String imagen;
    private String historia;
    private Double peso;
    private List<PeliculaSerieDTO> peliculaSerieDTOS;
}
