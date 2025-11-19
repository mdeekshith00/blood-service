package com.blood_service.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blood_service.entities.BloodUnitHistory;

public interface BloodUnitHistoryRepositary extends JpaRepository<BloodUnitHistory, Integer>{

}
