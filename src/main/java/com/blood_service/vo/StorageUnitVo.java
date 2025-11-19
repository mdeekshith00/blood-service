package com.blood_service.vo;

import com.common.enums.StorageType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageUnitVo {
	
//    private Integer storageUnitId;
//    private BloodBank bloodBank; // link to parent bank
    private StorageType storageType; // REFRIGERATOR, FREEZER, PLATELET_AGITATOR
    private String name; // e.g., Fridge1, FreezerA
    private String type; // REFRIGERATOR, FREEZER, PLASMA_STORAGE, PLATELET_STORAGE
    private Integer capacityML; // storage capacity
    private String temperatureLog; // link to temp sensors or logs
    private String metadata; // flexible JSON string for future data (IoT device, alarm settings)

}
