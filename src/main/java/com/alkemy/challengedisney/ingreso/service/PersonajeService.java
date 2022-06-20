package com.alkemy.challengedisney.ingreso.service;

import com.alkemy.challengedisney.ingreso.dto.PersonajeDTO;
import com.alkemy.challengedisney.ingreso.entity.PersonajeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PersonajeService {
    List<PersonajeDTO> getAll();

    PersonajeDTO getDetailsById(Long id);

    List<PersonajeDTO> getByFilters(String name, Long age, Double weight, Set<Long> movies);

    PersonajeDTO save(PersonajeDTO dto);

    PersonajeDTO update(Long id, PersonajeDTO dto);

    void delete(Long id);

    PersonajeEntity getEntityById(Long idPersonaje);
}
