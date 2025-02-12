package com.traini8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.traini8.model.TrainingCenter;
import com.traini8.service.TrainingCenterService;

import jakarta.validation.Valid;

/**
 * REST controller for managing training centers.
 */
@RestController
@RequestMapping("/api/training-centers")
@Validated
public class TrainingCenterController {

    private final TrainingCenterService trainingCenterService;

    /**
     * Constructor for TrainingCenterController.
     *
     * @param trainingCenterService service layer for training centers
     */
    @Autowired
    public TrainingCenterController(TrainingCenterService trainingCenterService) {
        this.trainingCenterService = trainingCenterService;
    }

    /**
     * Creates a new training center.
     * <p>
     * Accepts JSON data. The createdOn field in the request (if any) is ignored.
     * </p>
     *
     * @param trainingCenter the training center details
     * @return ResponseEntity with the created training center information
     */
    @PostMapping
    public ResponseEntity<TrainingCenter> createTrainingCenter(@Valid @RequestBody TrainingCenter trainingCenter) {
        TrainingCenter savedCenter = trainingCenterService.createTrainingCenter(trainingCenter);
        return new ResponseEntity<>(savedCenter, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of training centers, optionally filtered by query parameters.
     * <p>
     * Filters available:
     * <ul>
     *   <li>centerName (partial match, case-insensitive)</li>
     *   <li>city (exact match, case-insensitive)</li>
     *   <li>state (exact match, case-insensitive)</li>
     *   <li>course (partial match within coursesOffered, case-insensitive)</li>
     * </ul>
     * If no training centers exist, an empty list is returned.
     * </p>
     *
     * @param centerName optional filter by center name
     * @param city       optional filter by city
     * @param state      optional filter by state
     * @param course     optional filter by course offered
     * @return list of training centers matching the criteria
     */
    @GetMapping
    public ResponseEntity<List<TrainingCenter>> getTrainingCenters(
            @RequestParam(required = false) String centerName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String course) {

        List<TrainingCenter> centers = trainingCenterService.getTrainingCenters(centerName, city, state, course);
        return new ResponseEntity<>(centers, HttpStatus.OK);
    }
}
