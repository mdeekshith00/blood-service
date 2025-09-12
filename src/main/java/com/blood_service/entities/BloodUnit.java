package com.blood_service.entities;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BloodUnit {
	
	    private UUID id; // Unique identifier (barcode/QR) for each blood unit
	    private BloodGroup bloodGroup; // A+, A-, B+, B-, O+, O-, AB+, AB-
	    private RhFactor rhFactor; // Positive/Negative
	    private int volumeML; // Amount collected in ml
	    private SourceType sourceType; // CAMP, HOSPITAL, DIRECT_DONOR, EMERGENCY_DRIVE
	    private UUID sourceId; // Reference to Camp, Hospital, or Donor
	    private Instant collectionDate; // Exact collection date & time
	    private Instant expiryDate; // Calculated based on blood type & storage rules
	    private Status status; // AVAILABLE, ASSIGNED, TRANSFERRED, USED, EXPIRED, DISCARDED, RESERVED
	    private UUID assignedTo; // Hospital/Camp/Request ID (nullable if unassigned)
	    private boolean verified; // Verified by Donation Service or Quality Check
	    private String storageLocation; // Fridge/Freezer/Storage code
	    private Map<String, Object> temperatureLogs; // Track storage temperature history
	    private Map<String, Object> bloodTestResults; // Hemoglobin, infectious disease tests, etc.
	    private PriorityLevel priorityLevel; // NORMAL, URGENT, CRITICAL
	    private boolean donorConsent; // Donor consent for use/transfer
	    private QuarantineStatus quarantineStatus; // NONE, QUARANTINED, CLEARED
	    private UUID createdBy; // User who registered the blood
	    private Instant createdAt; // Timestamp of creation
	    private UUID updatedBy; // Last user who updated
	    private Instant updatedAt; // Last update timestamp
	    private UUID auditReferenceId; // Reference to latest audit log entry
	    private Instant reservedUntil; // Optional hold until this timestamp
	    private String geoLocation; // Optional location tracking
	    private boolean expiryAlertSent; // Indicates if near-expiry alert was sent
	    private List<Map<String, Object>> usageHistory; // Past assignments or usage
	    private String encryptionKeyId; // Reference to encryption key for sensitive info
	    private AccessLevel accessLevel; // PUBLIC_VIEW, HOSPITAL_ONLY, ADMIN_ONLY

	    // Enums for specific fields
	    public enum BloodGroup {
	        A_POS, A_NEG, B_POS, B_NEG, O_POS, O_NEG, AB_POS, AB_NEG
	    }

	    public enum RhFactor {
	        POSITIVE, NEGATIVE
	    }

	    public enum SourceType {
	        CAMP, HOSPITAL, DIRECT_DONOR, EMERGENCY_DRIVE
	    }

	    public enum Status {
	        AVAILABLE, ASSIGNED, TRANSFERRED, USED, EXPIRED, DISCARDED, RESERVED
	    }

	    public enum PriorityLevel {
	        NORMAL, URGENT, CRITICAL
	    }

	    public enum QuarantineStatus {
	        NONE, QUARANTINED, CLEARED
	    }

	    public enum AccessLevel {
	        PUBLIC_VIEW, HOSPITAL_ONLY, ADMIN_ONLY
	    }

	    // Getters and setters can be generated using IDE or manually added
	


}
