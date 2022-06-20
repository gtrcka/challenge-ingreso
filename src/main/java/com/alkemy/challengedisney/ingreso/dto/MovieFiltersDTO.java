package com.alkemy.challengedisney.ingreso.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MovieFiltersDTO {

    private String titulo;
    private String fechaCreacion;
    private Set<Long> generos;
    private String order;

    public MovieFiltersDTO(String titulo, String fechaCreacion, Set<Long> generos, String order){
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.generos = generos;
        this.order = order;
    }

    public boolean isASC(){return this.order.compareToIgnoreCase("ASC")==0;}
    public boolean isDESC(){return this.order.compareToIgnoreCase("DESC")==0;}
}
