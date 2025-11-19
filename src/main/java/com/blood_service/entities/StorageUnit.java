package com.blood_service.entities;

import java.io.Serializable;
import java.util.List;

import com.common.enums.StorageType;

import jakarta.persistence.CascadeType;
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

@Entity
@Table(name = "storage_unit")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageUnit implements Serializable {

	    private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer storageUnitId;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "bank_id", nullable = false)
	    private BloodBank bloodBank; // link to parent bank
	    
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private StorageType storageType; // REFRIGERATOR, FREEZER, PLATELET_AGITATOR

	    @Column(nullable = false)
	    private String name; // e.g., Fridge1, FreezerA

	    @Column(nullable = false)
	    private String type; // REFRIGERATOR, FREEZER, PLASMA_STORAGE, PLATELET_STORAGE

	    @Column(nullable = false)
	    private Integer capacityML; // storage capacity

	    @Column(nullable = true)
	    private String temperatureLog; // link to temp sensors or logs

	    @Column(nullable = true)
	    private String metadata; // flexible JSON string for future data (IoT device, alarm settings)

	    @OneToMany(mappedBy = "storageUnit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<BloodInventory> bloodInventories; // all units in this storage
	

}
