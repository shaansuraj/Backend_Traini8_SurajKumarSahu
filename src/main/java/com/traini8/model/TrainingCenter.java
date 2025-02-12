package com.traini8.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Represents a training center entity.
 */
@Entity
@Table(name = "training_centers")
public class TrainingCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Center Name is required.")
    @Size(max = 40, message = "Center Name cannot exceed 40 characters.")
    @Column(name = "center_name", nullable = false, length = 40)
    private String centerName;

    @NotBlank(message = "Center Code is required.")
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "Center Code must be exactly 12 alphanumeric characters.")
    @Column(name = "center_code", nullable = false, unique = true, length = 12)
    private String centerCode;

    @Valid
    @Embedded
    @NotNull(message = "Address is required.")
    private Address address;

    @Min(value = 0, message = "Student Capacity must be non-negative.")
    @Column(name = "student_capacity")
    private Integer studentCapacity;

    @ElementCollection
    @CollectionTable(name = "training_center_courses", joinColumns = @JoinColumn(name = "training_center_id"))
    @Column(name = "course")
    private List<@NotBlank(message = "Course name cannot be blank.") String> coursesOffered;

    /**
     * The createdOn field is generated on the server side.
     * It is marked as read-only so that any user input is ignored.
     */
    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "created_on")
    private Long createdOn;

    @Email(message = "Invalid email format.")
    @Column(name = "contact_email")
    private String contactEmail;

    @NotBlank(message = "Contact Phone is required.")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Contact Phone must be a valid phone number with 10 digits, optional country code.")
    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    // Default constructor
    public TrainingCenter() {}

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getStudentCapacity() {
        return studentCapacity;
    }

    public void setStudentCapacity(Integer studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    public List<String> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<String> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * Automatically sets the createdOn timestamp before persisting.
     * This ensures that any user-supplied value for createdOn is ignored.
     */
    @PrePersist
    public void prePersist() {
        this.createdOn = System.currentTimeMillis();
    }
}
