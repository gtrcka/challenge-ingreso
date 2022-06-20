package com.alkemy.challengedisney.ingreso.mapper;

import com.alkemy.challengedisney.ingreso.dto.PeliculaSerieBasicDTO;
import com.alkemy.challengedisney.ingreso.dto.PeliculaSerieDTO;
import com.alkemy.challengedisney.ingreso.entity.PeliculaSerieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaSerieMapper {

    @Autowired
    private PersonajeMapper personajeMapper;

    public PeliculaSerieDTO movieEntity2DTO(PeliculaSerieEntity entity, boolean loadPersonaje) {
        PeliculaSerieDTO dto = new PeliculaSerieDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setImagen(entity.getImagen());
        dto.setFechaCreacion(String.valueOf(entity.getFechaCreacion()));
        dto.setCalificacion((entity.getCalificacion()));
        dto.setGeneroId(entity.getGeneroId());

        return dto;
    }

    public List<PeliculaSerieDTO> movieEntityList2DTOList(List<PeliculaSerieEntity> entities, boolean b) {
        List<PeliculaSerieDTO> dtos = new ArrayList<>();
        for (PeliculaSerieEntity entity:entities) {
            dtos.add(this.movieEntity2DTO(entity, false));
        }
        return dtos;
    }

    private LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public PeliculaSerieEntity movieDTO2Entity(PeliculaSerieDTO dto) {
        PeliculaSerieEntity entity = new PeliculaSerieEntity();
        entity.setTitulo(dto.getTitulo());
        entity.setImagen(dto.getImagen());
        entity.setCalificacion(dto.getCalificacion());
        entity.setFechaCreacion(this.string2LocalDate(dto.getFechaCreacion()));
        entity.setGeneroId(dto.getGeneroId());
        entity.setPersonajes(this.personajeMapper.personajeDTOSet2EntitySet(dto.getPersonajes()));
        return entity;
    }

    public void movieEntityRefreshValue(PeliculaSerieEntity entity, PeliculaSerieDTO dto) {
        entity.setTitulo(dto.getTitulo());
        entity.setImagen(dto.getImagen());
        entity.setCalificacion(dto.getCalificacion());
        entity.setFechaCreacion(this.string2LocalDate(dto.getFechaCreacion()));
        entity.setGeneroId(dto.getGeneroId());
    }

    public List<PeliculaSerieBasicDTO> movieEntityList2DTOBasicList(List<PeliculaSerieEntity> entities, boolean b) {
        List<PeliculaSerieBasicDTO> dtos = new ArrayList<>();
        for (PeliculaSerieEntity entity:entities) {
            PeliculaSerieBasicDTO dto = new PeliculaSerieBasicDTO();
            dto.setImagen(entity.getImagen());
            dto.setTitulo(entity.getTitulo());
            dto.setFechaCreacion(String.valueOf(entity.getFechaCreacion()));
            dtos.add(dto);
        }
        return dtos;
    }
}
