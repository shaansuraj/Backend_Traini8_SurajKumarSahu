package com.traini8.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.traini8.model.TrainingCenter;
import com.traini8.repository.TrainingCenterRepository;
import com.traini8.specification.TrainingCenterSpecification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Service class for managing training centers.
 */
@Service
public class TrainingCenterService {

    private final TrainingCenterRepository trainingCenterRepository;

    /**
     * Constructor for TrainingCenterService.
     *
     * @param trainingCenterRepository the repository for training centers
     */
    @Autowired
    public TrainingCenterService(TrainingCenterRepository trainingCenterRepository) {
        this.trainingCenterRepository = trainingCenterRepository;
    }

    /**
     * Creates and saves a new training center.
     * The createdOn field is automatically populated.
     *
     * @param trainingCenter the training center to be saved
     * @return the saved training center
     */
    public TrainingCenter createTrainingCenter(TrainingCenter trainingCenter) {
        // createdOn is set automatically by the @PrePersist method.
        return trainingCenterRepository.save(trainingCenter);
    }

    /**
     * Retrieves a list of training centers based on provided filters.
     *
     * @param centerName optional filter by center name (partial match)
     * @param city       optional filter by city (exact match)
     * @param state      optional filter by state (exact match)
     * @param course     optional filter by course offered (partial match within courses list)
     * @return list of matching training centers
     */
    public List<TrainingCenter> getTrainingCenters(String centerName, String city, String state, String course) {
        // Initializing a Specification with an anonymous inner class
        Specification<TrainingCenter> spec = new Specification<TrainingCenter>() {
            @Override
            public Predicate toPredicate(Root<TrainingCenter> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.conjunction();
            }
        };

        if (centerName != null && !centerName.isEmpty()) {
            spec = spec.and(TrainingCenterSpecification.centerNameContains(centerName));
        }
        if (city != null && !city.isEmpty()) {
            spec = spec.and(TrainingCenterSpecification.cityEquals(city));
        }
        if (state != null && !state.isEmpty()) {
            spec = spec.and(TrainingCenterSpecification.stateEquals(state));
        }
        if (course != null && !course.isEmpty()) {
            spec = spec.and(TrainingCenterSpecification.courseOfferedContains(course));
        }

        return trainingCenterRepository.findAll(spec);
    }
}
