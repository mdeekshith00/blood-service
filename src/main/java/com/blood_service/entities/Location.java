package com.blood_service.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

import com.common.enums.StorageType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "blood_inventory_location")
public class Location implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blood_inventory_location_id")
	private Integer location_id;

	 // GPS coordinates
    @Column(nullable = true)
    private Double latitude;

    @Column(nullable = true)
    private Double longitude;

    // Full address fields
    @Column(nullable = true)
    private String addressLine1;

    @Column(nullable = true)
    private String addressLine2;

    @Column(nullable = true)
    private String city;

    @Column(nullable = true)
    private String state;

    @Column(nullable = true)
    private String country;

    @Column(nullable = true)
    private String postalCode;

    @Column(columnDefinition = "TEXT")
    private String metadataJson; // store flexible JSON metadata

    // Type of storage location (BloodBank, Hospital storage, Mobile unit, etc.)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StorageType storageLocation;

    // Timestamps
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    // 🔗 Relation to BloodBank (one bank can have multiple locations)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_bank_id")
    @JsonBackReference
    private BloodBank bloodBank;


}
