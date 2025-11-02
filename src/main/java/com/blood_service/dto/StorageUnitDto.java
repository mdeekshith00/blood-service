package com.blood_service.dto;

import com.blood_service.entities.BloodBank;
import com.common.enums.StorageType;
import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter 
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StorageUnitDto {
	
	    private Integer storageUnitId;
	    private String storageType; // REFRIGERATOR, FREEZER, PLATELET_AGITATOR
	    private String name; // e.g., Fridge1, FreezerA
	    private String type; // REFRIGERATOR, FREEZER, PLASMA_STORAGE, PLATELET_STORAGE
	    private Integer capacityML; // storage capacity
	    private String temperatureLog; // link to temp sensors or logs
	    private String metadata;
	    private BloodBankDto bloodBank;

}
