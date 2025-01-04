package com.springboot.app.utility;

import org.springframework.data.jpa.domain.Specification;

import com.springboot.app.dto.FilterDto;
import com.springboot.app.model.Program;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProgramSpecification implements Specification<Program> {

	private static final long serialVersionUID = 4205052571507975862L;

	private final FilterDto filterDTO;

    public ProgramSpecification(FilterDto filterDTO) {
        this.filterDTO = filterDTO;
    }

	@Override
	public Predicate toPredicate(Root<Program> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate predicate = criteriaBuilder.conjunction();

        if (filterDTO.getCategoryId() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category").get("id"), filterDTO.getCategoryId()));
        }
        if (filterDTO.getDifficultyId() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("difficulty").get("id"), filterDTO.getDifficultyId()));
        }
        if (filterDTO.getAttributeId() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("attribute").get("id"), filterDTO.getAttributeId()));
        }
        if (filterDTO.getPriceFrom() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filterDTO.getPriceFrom()));
        }
        if (filterDTO.getPriceTo() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("price"), filterDTO.getPriceTo()));
        }
        if (filterDTO.getDurationFrom() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("duration"), filterDTO.getDurationFrom()));
        }
        if (filterDTO.getDurationTo() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("duration"), filterDTO.getDurationTo()));
        }
        return predicate;
 
	}

}
