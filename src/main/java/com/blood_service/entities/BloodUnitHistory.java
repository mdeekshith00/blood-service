package com.blood_service.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import com.common.enums.Action;
import com.common.enums.DestinationType;
import com.common.enums.SourceLocationType;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "blood_unit_history")
public class BloodUnitHistory implements Serializable {

        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer blood_unit_history_id;    
        
        @Enumerated(EnumType.STRING)  
        @Column(nullable = false)
	    private Action action; 
        
        @Column(nullable = false)
	    private Instant performedAt;        // Timestamp when action occurred
        
        @Enumerated(EnumType.STRING)  
        @Column(nullable = false)
	    private SourceLocationType sourceLocation;      
	    
	    @Enumerated(EnumType.STRING)  
	    @Column(nullable = false)
	    private DestinationType destination; 
	    
	    @Column(nullable = false)
	    private String notes;               // Optional: reason or extra info
	    
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "blood_inventory_id")
	    @JsonBackReference
	    private BloodInventory BloodInventory;            // Reference to BloodUnit
	    
	    private UUID performedBy;           // User ID performing the action

}
