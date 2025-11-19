package com.blood_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter 
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BloodBankDto {
	
	private String name; 
    private String type; 
    private Integer totalCapacityML; 
    private boolean active; 
    private String contactInfo;
    private String metadataJson;  
    private LocationDto location;
    private StorageUnitDto storageUnit;
    
}
