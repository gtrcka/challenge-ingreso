package com.alkemy.challengedisney.ingreso.mapper;

import com.alkemy.challengedisney.ingreso.dto.GeneroDTO;
import com.alkemy.challengedisney.ingreso.entity.GeneroEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component//Componenete de tipo genérico que permite inyectarlo en la clase
public class GeneroMapper {
    public GeneroEntity generoDTO2Entity(GeneroDTO dto){
        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setNombre(dto.getNombre());
        generoEntity.setImagen(dto.getImagen());
        return  generoEntity;
    }

    public GeneroDTO generoEntity2DTO(GeneroEntity entity){
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(entity.getId());
        generoDTO.setNombre(entity.getNombre());
        generoDTO.setImagen(entity.getImagen());
        return  generoDTO;
    }

    public List<GeneroDTO> generoEntityList2DTOList(List<GeneroEntity> entities) {
        List<GeneroDTO> dtos = new ArrayList<>();
        for (GeneroEntity entity : entities) {
            dtos.add(this.generoEntity2DTO(entity));
        }
        return dtos;
    }
}
