package com.alkemy.challengedisney.ingreso.service;

import com.alkemy.challengedisney.ingreso.dto.GeneroDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GeneroService {
    GeneroDTO save(GeneroDTO dto);
    List<GeneroDTO> getAllGeneros();

}
