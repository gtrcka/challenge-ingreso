package com.alkemy.challengedisney.ingreso.mapper;

import com.alkemy.challengedisney.ingreso.dto.PeliculaSerieDTO;
import com.alkemy.challengedisney.ingreso.dto.PersonajeDTO;
import com.alkemy.challengedisney.ingreso.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonajeMapper {
    @Autowired
    PeliculaSerieMapper movieMapper;
    public List<PersonajeDTO> personajeEntitySet2DTOList(Collection<PersonajeEntity> entities, boolean loadPersonajes) {
        List<PersonajeDTO> dtos = new ArrayList<>();
        for (PersonajeEntity entity:entities) {
            dtos.add(this.personajeEntity2DTO(entity, loadPersonajes));
        }
        return dtos;
    }

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean loadMovies) {
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setImagen(entity.getImagen());
        dto.setHistoria(entity.getHistoria());
        if (loadMovies){
            List<PeliculaSerieDTO> moviesDTO = this.movieMapper.movieEntityList2DTOList(entity.getPeliculasSeries(), false);
            dto.setPeliculaSerieDTOS(moviesDTO);
        }
        return  dto;
    }

    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto) {
        PersonajeEntity entity = new PersonajeEntity();
        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());
        return entity;
    }

    public void personajeEntityRefreshValue(PersonajeEntity entity, PersonajeDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());
    }

    public Set<PersonajeEntity> personajeDTOSet2EntitySet(Set<PersonajeDTO> dtos) {
        Set<PersonajeEntity> entities = new HashSet<>();
        for (PersonajeDTO dto:dtos) {
            entities.add(this.personajeDTO2Entity(dto));
        }
        return entities;
    }
}
