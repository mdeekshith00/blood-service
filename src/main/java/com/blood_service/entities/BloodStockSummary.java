package com.blood_service.entities;

public class BloodStockSummary {
//	for fast Redis caching)


	    private String bloodGroup;      // Enum or String depending on implementation
	    private int availableUnits;    // Number of available units
	    private int nearExpiryUnits;   // Units expiring soon (e.g., 7 days)
	    private int assignedUnits;     // Units assigned but not yet used
	    private java.time.LocalDateTime lastUpdated;  // Last update timestamp

	    // Getters and setters can be added below if needed
	
	

}
