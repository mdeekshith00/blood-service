package com.blood_service.entities;

import java.io.Serializable;
import java.time.Instant;

import com.common.enums.StorageType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Column(nullable = true)
	private String latitude;
	@Column(nullable = true)
	private String lontitude;
	
	@Column(nullable = false)
    private StorageType storageLocation; 
	
	private Instant createdAt;
	
	private Instant updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blood_inventory_id")
	@JsonManagedReference
	private BloodInventory bloodInventory;

}
