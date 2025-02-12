package com.traini8.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Represents the address details for a training center.
 */
@Embeddable
public class Address {

    @NotBlank(message = "Detailed address is required.")
    @Size(max = 100, message = "Detailed address cannot be more than 100 characters.")
    private String detailedAddress;

    @NotBlank(message = "City is required.")
    @Size(max = 50, message = "City cannot be more than 50 characters.")
    private String city;

    @NotBlank(message = "State is required.")
    @Size(max = 50, message = "State cannot be more than 50 characters.")
    private String state;

    @NotBlank(message = "Pincode is required.")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits.")
    private String pincode;

    // Default constructor
    public Address() {}

    // Parameterized constructor
    public Address(String detailedAddress, String city, String state, String pincode) {
        this.detailedAddress = detailedAddress;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    // Getters and Setters

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
