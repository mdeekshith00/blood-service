package com.blood_service.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blood_service.entities.Location;

public interface LocationRepositary extends JpaRepository<Location, Integer>{

}
