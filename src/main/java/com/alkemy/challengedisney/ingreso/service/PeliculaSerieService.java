package com.alkemy.challengedisney.ingreso.service;

import com.alkemy.challengedisney.ingreso.dto.PeliculaSerieBasicDTO;
import com.alkemy.challengedisney.ingreso.dto.PeliculaSerieDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PeliculaSerieService {
    List<PeliculaSerieBasicDTO> getAll();//

    PeliculaSerieDTO getDetailsById(Long id);//

    List<PeliculaSerieDTO> getByFilters(String titulo, String fechaCreacion, String genero, String order);//

    PeliculaSerieDTO save(PeliculaSerieDTO movie);//

    PeliculaSerieDTO update(Long id, PeliculaSerieDTO movie);//

    void delete(Long id);
}
