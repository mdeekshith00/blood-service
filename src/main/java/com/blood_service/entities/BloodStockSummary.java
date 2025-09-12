package com.blood_service.entities;

import java.time.LocalDateTime;

import com.common.enums.BloodGroupType;

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
public class BloodStockSummary {
//	for fast Redis caching)


	    private BloodGroupType bloodGroup;      // Enum or String depending on implementation
	    private int availableUnits;    // Number of available units
	    private int nearExpiryUnits;   // Units expiring soon (e.g., 7 days)
	    private int assignedUnits;     // Units assigned but not yet used
	    private LocalDateTime lastUpdated;  // Last update timestamp

	
	

}
