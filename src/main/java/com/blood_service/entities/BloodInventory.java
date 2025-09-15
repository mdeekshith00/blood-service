package com.blood_service.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.common.enums.AccessLevel;
import com.common.enums.BloodGroupType;
import com.common.enums.BloodUnitSourceType;
import com.common.enums.PriorityLevel;
import com.common.enums.QuarantineStatus;
import com.common.enums.RhFactor;
import com.common.enums.StorageType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.vladmihalcea.hibernate.type.json.JsonType;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "blood_inventory")
public class BloodInventory implements Serializable{
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer blood_inventory_id; 
	    
	    @Column(nullable = true)
	    private BloodGroupType bloodGroup; // A+, A-, B+, B-, O+, O-, AB+, AB-
	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private RhFactor rhFactor; // Positive/Negative
	    
	    @Column
	    private int volumeML; // Amount collected in ml
	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private BloodUnitSourceType sourceType; // CAMP, HOSPITAL, DIRECT_DONOR, EMERGENCY_DRIVE
	    
	    @Column(nullable = false)
	    private UUID sourceId; // Reference to Camp, Hospital, or Donor
	    
	    private Instant collectionDate; // Exact collection date & time
	    @Column(nullable = false)
	    private Instant expiryDate; // Calculated based on blood type & storage rules
	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private BloodUnitSourceType status; // AVAILABLE, ASSIGNED, TRANSFERRED, USED, EXPIRED, DISCARDED, RESERVED
	    
	    @Column(nullable = false)
	    private UUID assignedTo; // Hospital/Camp/Request ID (nullable if unassigned)
	    
	    @Column(nullable = false)
	    private boolean verified; // Verified by Donation Service or Quality Check

	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private PriorityLevel priorityLevel; // NORMAL, URGENT, CRITICAL
	    
	    private boolean donorConsent; // Donor consent for use/transfer
	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private QuarantineStatus quarantineStatus; // NONE, QUARANTINED, CLEARED
	    
	    @Column(nullable = false)
	    private UUID createdBy; // User who registered the blood
	    
	    @Column(nullable = false)
	    private Instant createdAt; // Timestamp of creation
	    
	    @Column(nullable = false)
	    private UUID updatedBy; // Last user who updated
	    
	    @Column(nullable = false)
	    private Instant updatedAt; // Last update timestamp
	    
	    private UUID auditReferenceId; // Reference to latest audit log entry
	    
	    private Instant reservedUntil; // Optional hold until this timestamp
	
	    private boolean expiryAlertSent; // Indicates if near-expiry alert was sent
	     
	    private String encryptionKeyId; // Reference to encryption key for sensitive info
	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private AccessLevel accessLevel; // PUBLIC_VIEW, HOSPITAL_ONLY, ADMIN_ONLY
	    
	    @OneToMany(mappedBy = "BloodInventory")
	    @JsonManagedReference
	    private List<BloodUnitHistory> bloodUnitHistory;
	    
	    
	   @OneToMany(mappedBy = "bloodInventory")
	   @JsonBackReference
	    private List<Location> location; 
	    
}
//private List<Map<String, Object>> usageHistory; // Past assignments or usage
//@Type(JsonType.class)
//@Column(columnDefinition = "jsonb") 
//private Map<String, Object> temperatureLogs; // Track storage temperature history
//
//@Type(JsonType.class)
//@Column(columnDefinition = "jsonb") 
//private Map<String, Object> bloodTestResults; // Hemoglobin, infectious disease tests, etc.