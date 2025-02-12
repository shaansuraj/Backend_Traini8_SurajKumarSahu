package com.traini8.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.traini8.model.TrainingCenter;

/**
 * Repository interface for TrainingCenter entities.
 */
public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long>, JpaSpecificationExecutor<TrainingCenter> {

    public List<TrainingCenter> findAll(Specification<TrainingCenter> spec);
    
}
