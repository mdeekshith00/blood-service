package com.blood_service.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blood_service.entities.BloodInventory;

@Repository
public interface BloodInventoryRepositary extends JpaRepository<BloodInventory, Integer>{
	

}
