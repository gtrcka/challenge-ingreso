package com.alkemy.challengedisney.challengedisney.entity;

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

    private int edad;

    private double peso;

    private String historia;

    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<PeliculaSerieEntity> peliculasSeries = new ArrayList<>();

}
