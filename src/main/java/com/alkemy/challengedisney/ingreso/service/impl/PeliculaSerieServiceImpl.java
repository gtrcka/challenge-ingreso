package com.alkemy.challengedisney.ingreso.service.impl;

import com.alkemy.challengedisney.ingreso.dto.MovieFiltersDTO;
import com.alkemy.challengedisney.ingreso.dto.PeliculaSerieBasicDTO;
import com.alkemy.challengedisney.ingreso.dto.PeliculaSerieDTO;
import com.alkemy.challengedisney.ingreso.entity.PeliculaSerieEntity;
import com.alkemy.challengedisney.ingreso.entity.PersonajeEntity;
import com.alkemy.challengedisney.ingreso.exception.ParamNotFound;
import com.alkemy.challengedisney.ingreso.mapper.PeliculaSerieMapper;
import com.alkemy.challengedisney.ingreso.repository.PeliculaSerieRepository;
import com.alkemy.challengedisney.ingreso.repository.specifications.MovieSpecification;
import com.alkemy.challengedisney.ingreso.service.PeliculaSerieService;
import com.alkemy.challengedisney.ingreso.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class PeliculaSerieServiceImpl implements PeliculaSerieService{

    private PeliculaSerieRepository movieRepository;
    private MovieSpecification movieSpecification;
    private PeliculaSerieService movieService;
    private PeliculaSerieMapper movieMapper;
    @Autowired
    private PersonajeService personajeService;

    @Autowired
    public PeliculaSerieServiceImpl(
            PeliculaSerieRepository movieRepository,
            MovieSpecification movieSpecification,
            PeliculaSerieMapper movieMapper
    ){
        this.movieSpecification = movieSpecification;
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public List<PeliculaSerieBasicDTO> getAll(){
        List<PeliculaSerieEntity> entities = this.movieRepository.findAll();
        List<PeliculaSerieBasicDTO> dtos = this.movieMapper.movieEntityList2DTOBasicList(entities, false);
        return dtos;
    }

    public List<PeliculaSerieDTO> getByFilters(String titulo, String fechaCreacion, Set<Long> generos, String order){
        MovieFiltersDTO filters = new MovieFiltersDTO(titulo, fechaCreacion, generos, order);
        List<PeliculaSerieEntity> entities = this.movieRepository.findAll(this.movieSpecification.getByFilters(filters));
        List<PeliculaSerieDTO> dtos = this.movieMapper.movieEntityList2DTOList(entities, true);
        return dtos;
    }

    public PeliculaSerieDTO save(PeliculaSerieDTO dto){
        PeliculaSerieEntity entity = this.movieMapper.movieDTO2Entity(dto);
        PeliculaSerieEntity entitySaved = this.movieRepository.save(entity);
        PeliculaSerieDTO result = this.movieMapper.movieEntity2DTO(entitySaved, true);
        return result;
    }

    public PeliculaSerieDTO update(Long id, PeliculaSerieDTO dto){
        PeliculaSerieEntity entity = this.movieMapper.movieDTO2Entity(this.getDetailsById(id));
        this.movieMapper.movieEntityRefreshValue(entity, dto);
        PeliculaSerieEntity entitySaved = this.movieRepository.save(entity);
        PeliculaSerieDTO result = this.movieMapper.movieEntity2DTO(entitySaved, false);
        return result;
    }

    public PeliculaSerieDTO getDetailsById(Long id){
        Optional<PeliculaSerieEntity> entity = this.movieRepository.findById(id);
        if (!entity.isPresent()){
            throw new ParamNotFound("Id movie not found");
        }
        PeliculaSerieDTO movieDTO =this.movieMapper.movieEntity2DTO(entity.get(), true);
        return movieDTO;
    }

    @Override
    public List<PeliculaSerieDTO> getByFilters(String titulo, String fechaCreacion, String genero, String order) {
        return null;
    }

    public void delete(Long id){this.movieRepository.deleteById(id);}

    public void addPersonaje(Long id, Long idPersonaje){
        PeliculaSerieEntity entity = this.movieRepository.getById(id);
        entity.getPersonajes().size();
        PersonajeEntity personajeEntity = this.personajeService.getEntityById(idPersonaje);
        entity.addPersonaje(personajeEntity);
        this.movieRepository.save(entity);
    }

    public void removePersonaje(Long id, Long idPersonaje){
        PeliculaSerieEntity entity = this.movieRepository.getById(id);
        entity.getPersonajes().size();
        PersonajeEntity paisEntity = this.personajeService.getEntityById(idPersonaje);
        entity.removePersonaje(paisEntity);
        this.movieRepository.save(entity);
    }
}
