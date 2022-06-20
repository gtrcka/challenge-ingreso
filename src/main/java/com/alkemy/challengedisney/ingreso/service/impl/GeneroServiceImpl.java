package com.alkemy.challengedisney.ingreso.service.impl;

import com.alkemy.challengedisney.ingreso.dto.GeneroDTO;
import com.alkemy.challengedisney.ingreso.entity.GeneroEntity;
import com.alkemy.challengedisney.ingreso.mapper.GeneroMapper;
import com.alkemy.challengedisney.ingreso.repository.GeneroRepository;
import com.alkemy.challengedisney.ingreso.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroMapper generoMapper;
    @Autowired
    private GeneroRepository generoRepository;
    public GeneroDTO save(GeneroDTO dto){
        GeneroEntity entity = generoMapper.generoDTO2Entity(dto);
        GeneroEntity entitySaved = generoRepository.save(entity);
        GeneroDTO result = generoMapper.generoEntity2DTO(entitySaved);
        return result;
    }

    @Override
    public List<GeneroDTO> getAllGeneros() {
        List<GeneroEntity> entities = generoRepository.findAll();
        List<GeneroDTO> result = generoMapper.generoEntityList2DTOList(entities);
        return result;
    }
}
