package com.alkemy.challengedisney.ingreso.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity//indica que es una entidad
@Table(name="peliculaserie")//indica tabla con la que se relaciona la entidad
@Getter
@Setter
@SQLDelete(sql= "UPDATE peliculaserie SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")//filtro para traer aquellos registros que "no fueron" borrados
public class PeliculaSerieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String titulo;

    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;

    private boolean deleted = Boolean.FALSE;

    private Long calificacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private GeneroEntity genero;

    @Column(name = "genero_id", nullable = false)
    private Long generoId;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "personaje_peliculaserie",
            joinColumns = @JoinColumn(name = "peliculaserie_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private Set<PersonajeEntity> personajes = new HashSet<>();

    //Add and Remove paises
    public void addPersonaje(PersonajeEntity pais){this.personajes.add(pais);}
    public void removePersonaje(PersonajeEntity pais){this.personajes.remove(pais);}
}
