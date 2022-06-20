package com.alkemy.challengedisney.ingreso.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PersonajeFiltersDTO {
    private String name;
    private Long age;
    private Double weight;
    private Set <Long> movies;

    public PersonajeFiltersDTO(String name, Long age, Double weight, Set <Long> movies) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.movies = movies;
    }
}
