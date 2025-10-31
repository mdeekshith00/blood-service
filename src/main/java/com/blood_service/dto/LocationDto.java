package com.blood_service.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDto {
	
	private Double latitude;

    private Double longitude;
    private String addressLine1;
    private String addressLine2;

    private String city;

    private String state;
    private String country;
    private String postalCode;
    private String metadataJson; // store flexible JSON metadata
    private String storageLocation;
    private Instant createdAt;
    private Instant updatedAt;

}
