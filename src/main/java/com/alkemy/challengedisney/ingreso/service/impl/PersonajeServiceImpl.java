package com.alkemy.challengedisney.ingreso.service.impl;

import com.alkemy.challengedisney.ingreso.dto.PersonajeDTO;
import com.alkemy.challengedisney.ingreso.dto.PersonajeFiltersDTO;
import com.alkemy.challengedisney.ingreso.entity.PersonajeEntity;
import com.alkemy.challengedisney.ingreso.exception.ParamNotFound;
import com.alkemy.challengedisney.ingreso.mapper.PersonajeMapper;
import com.alkemy.challengedisney.ingreso.repository.PersonajeRepository;
import com.alkemy.challengedisney.ingreso.repository.specifications.PersonajeSpecification;
import com.alkemy.challengedisney.ingreso.service.PeliculaSerieService;
import com.alkemy.challengedisney.ingreso.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class PersonajeServiceImpl implements PersonajeService {
    private PersonajeRepository personajeRepository;
    private PersonajeSpecification personajeSpecification;
    private PersonajeMapper personajeMapper;
    @Autowired
    private PeliculaSerieService peliculaSerieService;

    @Autowired
    public PersonajeServiceImpl(
        PersonajeRepository personajeRepository,
        PersonajeSpecification personajeSpecification,
        PersonajeMapper personajeMapper,
        PeliculaSerieService peliculaSerieService
    ){
        this.personajeRepository = personajeRepository;
        this.personajeSpecification = personajeSpecification;
        this.personajeMapper = personajeMapper;
        this.peliculaSerieService = peliculaSerieService;
    }

    public List<PersonajeDTO> getAll(){
        List<PersonajeEntity> entities = this.personajeRepository.findAll();
        List<PersonajeDTO> dtos = this.personajeMapper.personajeEntitySet2DTOList(entities, false);
        return dtos;
    }

    public PersonajeDTO getDetailsById(Long id){
        Optional<PersonajeEntity> entity = this.personajeRepository.findById(id);
        if (!entity.isPresent()){
            throw new ParamNotFound("Id personaje not found");
        }
        PersonajeDTO dto =this.personajeMapper.personajeEntity2DTO(entity.get(), true);
        return  dto;
    }

    public PersonajeEntity getEntityById(Long id){
        PersonajeDTO dto = this.getDetailsById(id);
        PersonajeEntity entity = this.personajeMapper.personajeDTO2Entity(dto);
        return entity;
    }

    public List<PersonajeDTO> getByFilters(String name, Long age, Double weight, Set<Long> movies){
        PersonajeFiltersDTO filters = new PersonajeFiltersDTO(name, age, weight, movies);
        List<PersonajeEntity> entities = this.personajeRepository.findAll(this.personajeSpecification.getByFilters(filters));
        List<PersonajeDTO> dtos = this.personajeMapper.personajeEntitySet2DTOList(entities, true);
        return dtos;
    }

    public PersonajeDTO save(PersonajeDTO dto){
        PersonajeEntity entity = this.personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity entitySaved = this.personajeRepository.save(entity);
        PersonajeDTO result = this.personajeMapper.personajeEntity2DTO(entitySaved, false);
        return result;
    }

    public PersonajeDTO update(Long id, PersonajeDTO dto){
        PersonajeEntity entity = this.personajeMapper.personajeDTO2Entity(this.getDetailsById(id));
        this.personajeMapper.personajeEntityRefreshValue(entity, dto);
        PersonajeEntity entitySaved = this.personajeRepository.save(entity);
        PersonajeDTO result = this.personajeMapper.personajeEntity2DTO(entitySaved, false);
        return result;
    }

    public void delete(Long id){
        this.personajeRepository.deleteById(id);
    }
}
