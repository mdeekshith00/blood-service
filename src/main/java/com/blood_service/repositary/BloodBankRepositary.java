package com.blood_service.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blood_service.entities.BloodBank;

public interface BloodBankRepositary extends JpaRepository<BloodBank, Integer>{
	
	Optional<BloodBank> findByNameIgnoreCaseAndActive(String name , boolean active);
	Optional<BloodBank> findByBankIdAndActive(Integer bankId , boolean active);
	

}
