package com.alkemy.challengedisney.ingreso.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity//indica que es una entidad
@Table(name="genero")//indica tabla con la que se relaciona la entidad
@Getter
@Setter
public class GeneroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//defino estrategia para el id, sequense 1,2,3,...n
    private Long id;

    private String nombre;

    private String imagen;
}
