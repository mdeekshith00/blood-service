package com.blood_service.entities;

import java.time.Instant;
import java.util.UUID;

public class BloodUnitHistory {


	    private UUID id;                     // Unique log ID
	    private UUID bloodUnitId;            // Reference to BloodUnit
	    private Action action;               // Enum for actions
	    private UUID performedBy;           // User ID performing the action
	    private Instant performedAt;        // Timestamp when action occurred
	    private String sourceLocation;      // Optional: from which location/camp/hospital
	    private String destination;         // Optional: assigned hospital/camp
	    private String notes;               // Optional: reason or extra info

	    // Enum definition for action
	    public enum Action {
	        CREATED,
	        VERIFIED,
	        ASSIGNED,
	        TRANSFERRED,
	        USED,
	        EXPIRED,
	        DISCARDED
	    }

	    // Getters and setters can be added here
	

}
