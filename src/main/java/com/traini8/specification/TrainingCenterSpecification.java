package com.traini8.specification;

import org.springframework.data.jpa.domain.Specification;

import com.traini8.model.TrainingCenter;

import jakarta.persistence.criteria.Join;

/**
 * Specifications for dynamic filtering of TrainingCenter entities.
 */
public class TrainingCenterSpecification {

    /**
     * Specification for filtering by centerName containing a substring (case-insensitive).
     *
     * @param centerName substring to filter center names
     * @return Specification for centerName filtering
     */
    public static Specification<TrainingCenter> centerNameContains(String centerName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("centerName")), "%" + centerName.toLowerCase() + "%");
    }

    /**
     * Specification for filtering by city in the embedded address (case-insensitive).
     *
     * @param city city to filter by
     * @return Specification for city filtering
     */
    public static Specification<TrainingCenter> cityEquals(String city) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("address").get("city")), city.toLowerCase());
    }

    /**
     * Specification for filtering by state in the embedded address (case-insensitive).
     *
     * @param state state to filter by
     * @return Specification for state filtering
     */
    public static Specification<TrainingCenter> stateEquals(String state) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("address").get("state")), state.toLowerCase());
    }

    /**
     * Specification for filtering by courses offered containing a specific course (case-insensitive).
     *
     * @param course course substring to filter by
     * @return Specification for course filtering
     */
    public static Specification<TrainingCenter> courseOfferedContains(String course) {
        return (root, query, criteriaBuilder) -> {
            // Since coursesOffered is an ElementCollection, we need to join the collection.
            Join<TrainingCenter, String> coursesJoin = root.join("coursesOffered");
            return criteriaBuilder.like(criteriaBuilder.lower(coursesJoin), "%" + course.toLowerCase() + "%");
        };
    }
}
