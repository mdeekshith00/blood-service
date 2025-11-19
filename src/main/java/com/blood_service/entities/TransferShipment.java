package com.blood_service.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transfer_shipment")
public class TransferShipment {

		private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "shipment_id")
		private Integer shipmentId;
		
	    @Column(nullable = true)
		private String fromLocation; // temperatureLog` (link to logs)
	    
	    @Column(nullable = true)
		private String toLocation;
		
		private String tempatureLog;
		
		private List<Integer> components;// (list of componentIds)
		
		private LocalDateTime shippedAt;
		
		private LocalDateTime receivedAt;
		
		private String carrierInfo;

}
