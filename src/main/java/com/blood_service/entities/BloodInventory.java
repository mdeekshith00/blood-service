package com.blood_service.entities;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.common.enums.AccessLevel;
import com.common.enums.BloodGroupType;
import com.common.enums.BloodInventoryStatusType;
import com.common.enums.BloodUnitSourceType;
import com.common.enums.ComponentType;
import com.common.enums.PriorityLevel;
import com.common.enums.QuarantineStatus;
import com.common.enums.RhFactor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	    
		@Enumerated(EnumType.STRING) 
	    @Column(nullable = true)
	    private BloodGroupType bloodGroup; // A+, A-, B+, B-, O+, O-, AB+, AB-
	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private RhFactor rhFactor; // Positive/Negative
	    
	    @Column
	    private Double volumeML; // Amount collected in ml
	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private BloodUnitSourceType sourceType; // CAMP, HOSPITAL, DIRECT_DONOR, EMERGENCY_DRIVE
	    
	    @Column(nullable = false)
	    private Integer sourceId; // Reference to Camp, Hospital, or Donor
	    
	    private LocalDateTime collectionDate; // Exact collection date & time
	    @Column(nullable = false)
	    private Instant expiryDate; // Calculated based on blood type & storage rules
	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private BloodInventoryStatusType status; // AVAILABLE, ASSIGNED, TRANSFERRED, USED, EXPIRED, DISCARDED, RESERVED
	    
	    @Column(nullable = false)
	    private String assignedTo; // Hospital/Camp/Request ID (nullable if unassigned)
	    
	    @Column(nullable = false)
	    private boolean verified; // Verified by Donation Service or Quality Check

	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private PriorityLevel priorityLevel; // NORMAL, URGENT, CRITICAL
	    
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private ComponentType componentType; // WHOLE_BLOOD, RBC, PLASMA, PLATELETS

	    
	    private boolean donorConsent; // Donor consent for use/transfer
	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private QuarantineStatus quarantineStatus; // NONE, QUARANTINED, CLEARED
	    
	    @Column(nullable = false)
	    private String bloodFrom; // User who registered the blood
	    
	    @Column(nullable = false)
	    private LocalDateTime createdAt; // Timestamp of creation
	    
	    @Column(nullable = false)
	    private String updatedBy; // Last user who updated
	    
	    @Column(nullable = false)
	    private LocalDateTime updatedAt; // Last update timestamp
	    
	    private UUID auditReferenceId; // Reference to latest audit log entry
	    
	    private LocalDateTime reservedUntil; // Optional hold until this timestamp
	
	    private boolean expiryAlertSent; // Indicates if near-expiry alert was sent
	     
	    private String encryptionKeyId; // Reference to encryption key for sensitive info
	    
	    private String metadata;
	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private AccessLevel accessLevel; // PUBLIC_VIEW, HOSPITAL_ONLY, ADMIN_ONLY
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "bloodInventories")
	    private StorageUnit storageUnit;
	    
	    @OneToMany(mappedBy = "BloodInventory")
	    @JsonManagedReference
	    private List<BloodUnitHistory> bloodUnitHistory;


	    
}
