package com.blood_service.vo;

import java.time.Instant;

import com.common.enums.StorageType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationVo {
	
	@NotNull
	private Double latitude;
	@NotNull
    private Double longitude;
    private String addressLine1;
    private String addressLine2;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;
    @NotNull
    private String postalCode;
    private String metadataJson; // store flexible JSON metadata
    @Enumerated(EnumType.STRING)
    private StorageType storageLocation;
    private Instant createdAt;
    private Instant updatedAt;

}
