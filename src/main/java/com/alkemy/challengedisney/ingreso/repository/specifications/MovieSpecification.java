package com.alkemy.challengedisney.ingreso.repository.specifications;

import com.alkemy.challengedisney.ingreso.dto.MovieFiltersDTO;
import com.alkemy.challengedisney.ingreso.entity.GeneroEntity;
import com.alkemy.challengedisney.ingreso.entity.PeliculaSerieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class MovieSpecification {

    public Specification<PeliculaSerieEntity> getByFilters(MovieFiltersDTO filtersDTO){
        return((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getTitulo())){
                predicates.add(
                        criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("titulo")),
                            "%" + filtersDTO.getTitulo().toLowerCase(Locale.ROOT) + "%"
                        )
                );
            }
            if (StringUtils.hasLength(filtersDTO.getFechaCreacion())){
                //TODO: reuse this in a function
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaCreacion = LocalDate.parse(filtersDTO.getFechaCreacion(), formatter);

                predicates.add(
                        criteriaBuilder.equal(root.<LocalDate>get("fechaCreacion"), fechaCreacion)
                );
            }

            if (!CollectionUtils.isEmpty(filtersDTO.getGeneros())){
                Join<GeneroEntity, PeliculaSerieEntity> join = root.join("generos", JoinType.INNER);
                Expression<String> generosId =join.get("id");
                predicates.add(generosId.in(filtersDTO.getGeneros()));
            }

            query.distinct(true);

            String orderByField = "titulo";
            query.orderBy(
                    filtersDTO.isASC()?
                    criteriaBuilder.asc(root.get(orderByField)):
                    criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
