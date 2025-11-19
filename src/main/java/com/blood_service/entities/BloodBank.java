package com.blood_service.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Entity
@Table(name = "blood_bank")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodBank implements Serializable {

	    private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer bankId;

	    @Column(nullable = false)
	    private String name; // e.g., City Blood Bank, Hospital XYZ Blood Bank

	    @Column(nullable = false)
	    private String type; // HOSPITAL, STANDALONE, MOBILE_UNIT

	    @Column(nullable = true)
	    private Integer totalCapacityML; // maximum blood volume this bank can store

	    @Column(nullable = false)
	    private boolean active; // bank operational or closed

	    @Column(nullable = true)
	    private String contactInfo; // phone/email
	    
	    @Column(columnDefinition = "TEXT") // or "LONGTEXT" for MySQL, "TEXT" for Postgres
	    private String metadataJson; // store JSON like {"temperatureSensor":"active","alert":"true"}

	    @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<StorageUnit> storageUnits; // multiple fridges/freezers/sections
	    
	    @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Location> locations; // bank can have multiple locations (branches, hospital storage, mobile units)

	

}
