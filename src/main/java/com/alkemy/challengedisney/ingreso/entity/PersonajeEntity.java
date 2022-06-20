package com.alkemy.challengedisney.ingreso.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity//indica que es una entidad
@Table(name="personaje")//indica tabla con la que se relaciona la entidad
@Getter
@Setter
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String nombre;

    private Long edad;

    private Double peso;

    private String historia;

    @ManyToMany(mappedBy = "personajes")
    private List<PeliculaSerieEntity> peliculasSeries = new ArrayList<>();

}
