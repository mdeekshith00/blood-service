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

//
///**
// * Enhanced BloodInventory for greater flexibility, searchability and auditability.
// */
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@TypeDef(name = "json", typeClass = JsonType.class)
//@Table(
//    name = "blood_inventory",
//    indexes = {
//        @Index(name = "idx_blood_group", columnList = "blood_group"),
//        @Index(name = "idx_status", columnList = "status"),
//        @Index(name = "idx_expiry", columnList = "expiry_date"),
//        @Index(name = "idx_barcode", columnList = "barcode")
//    }
//)
//public class BloodInventory implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer bloodInventoryId;
//
//    // Blood typing
//    @Enumerated(EnumType.STRING)
//    @Column(name = "blood_group", nullable = true)
//    private BloodGroupType bloodGroup;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "rh_factor", nullable = false)
//    private RhFactor rhFactor;
//
//    // Unit details
//    @Column(name = "volume_ml")
//    private int volumeML;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "component_type", nullable = false)
//    private BloodComponentType componentType; // e.g. RED_CELL, PLASMA, PLATELET, CRYOPRECIPITATE
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "storage_type")
//    private StorageType storageType; // FREEZER, REFRIGERATOR, ROOM_TEMP
//
//    @Column(name = "storage_temp_min_c")
//    private Double storageTempMinC;
//
//    @Column(name = "storage_temp_max_c")
//    private Double storageTempMaxC;
//
//    // Provenance & linking
//    @Enumerated(EnumType.STRING)
//    @Column(name = "source_type", nullable = false)
//    private BloodUnitSourceType sourceType; // CAMP, HOSPITAL, DONOR, etc.
//
//    @Column(name = "source_id", nullable = true)
//    private UUID sourceId; // reference to donor/camp/hospital
//
//    @Column(name = "unit_number", unique = true)
//    private String unitNumber; // human-friendly ID (e.g., "U-2025-000123")
//
//    @Column(name = "barcode", unique = true)
//    private String barcode; // or QR code payload
//
//    @Column(name = "lot_number")
//    private String lotNumber; // manufacturing/processing batch
//
//    @Column(name = "parent_donation_id")
//    private UUID parentDonationId; // if this unit originated from a donation entity
//
//    @Column(name = "pooled")
//    private boolean pooled; // if this unit is pooled from multiple donations
//
//    // status & lifecycle
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status", nullable = false)
//    private InventoryStatus status; // AVAILABLE, ASSIGNED, TRANSFERRED, USED, EXPIRED, DISCARDED, RESERVED
//
//    @Column(name = "assigned_to") // could be hospital id, request id etc.
//    private UUID assignedTo;
//
//    @Column(name = "collection_date")
//    private Instant collectionDate;
//
//    @Column(name = "expiry_date", nullable = false)
//    private Instant expiryDate;
//
//    @Column(name = "reserved_until")
//    private Instant reservedUntil;
//
//    @Column(name = "expiry_alert_sent")
//    private boolean expiryAlertSent;
//
//    // quality & tests
//    @Column(name = "verified")
//    private boolean verified;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "quarantine_status")
//    private QuarantineStatus quarantineStatus;
//
//    @Type(type = "json")
//    @Column(columnDefinition = "jsonb", name = "test_results")
//    private Map<String, String> testResults; // flexible store for tests e.g., {"HIV":"NEG","HBsAg":"NEG"}
//
//    @Column(name = "last_tested_at")
//    private Instant lastTestedAt;
//
//    // priority, consent, access
//    @Enumerated(EnumType.STRING)
//    @Column(name = "priority_level")
//    private PriorityLevel priorityLevel;
//
//    @Column(name = "donor_consent")
//    private boolean donorConsent;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "access_level")
//    private AccessLevel accessLevel; // PUBLIC_VIEW, HOSPITAL_ONLY, ADMIN_ONLY
//
//    // audit & metadata
//    @Column(name = "created_by", nullable = false)
//    private UUID createdBy;
//
//    @Column(name = "created_at", nullable = false)
//    private Instant createdAt;
//
//    @Column(name = "updated_by", nullable = false)
//    private UUID updatedBy;
//
//    @Column(name = "updated_at", nullable = false)
//    private Instant updatedAt;
//
//    @Column(name = "audit_reference_id")
//    private UUID auditReferenceId;
//
//    @Type(type = "json")
//    @Column(columnDefinition = "jsonb", name = "metadata")
//    private Map<String, Object> metadata; // flexible key-values for search, e.g., {"blood_bank_region":"south","deviceId":"T-32"}
//
//    @ElementCollection
//    @Column(name = "tags")
//    private List<String> tags; // searchable tags/labels: ["emergency","neonate","rare-blood"]
//
//    @Type(type = "json")
//    @Column(columnDefinition = "jsonb", name = "linked_shipments")
//    private List<UUID> linkedShipmentIds; // track transfers
//
//    @Type(type = "json")
//    @Column(columnDefinition = "jsonb", name = "chain_of_custody")
//    private List<ChainOfCustodyEvent> chainOfCustody; // embeddable events (user,timestamp,action,notes)
//
//    @Column(name = "encryption_key_id")
//    private String encryptionKeyId; // for sensitive fields if encrypted
//
//    // relationships
//    @OneToMany(mappedBy = "bloodInventory")
//    private List<BloodUnitHistory> bloodUnitHistory;
//
//    @OneToOne(mappedBy = "bloodInventory")
//    private Location location;
//    
//    // Additional small flags
//    @Column(name = "active")
//    private boolean active = true;
//
//    @Column(name = "discard_reason")
//    private String discardReason;
//
//    // --- Supporting enums / embeddables should be defined elsewhere in your codebase ---
//}
