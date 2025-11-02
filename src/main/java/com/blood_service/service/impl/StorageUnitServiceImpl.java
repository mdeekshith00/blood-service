package com.blood_service.service.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blood_service.dto.BloodBankDto;
import com.blood_service.dto.StorageUnitDto;
import com.blood_service.entities.BloodBank;
import com.blood_service.entities.StorageUnit;
import com.blood_service.repositary.BloodBankRepositary;
import com.blood_service.repositary.StorageUnitRespositary;
import com.blood_service.service.StorageUnitService;
import com.blood_service.vo.StorageUnitVo;
import com.common.constants.ErrorConstants;
import com.common.exception.BloodBankBusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageUnitServiceImpl  implements StorageUnitService {
	
	private final BloodBankRepositary bloodBankRepo;
	private final StorageUnitRespositary storageUnitrepo;
	
	@Override
	public StorageUnitDto addStorageUnittoBloodBank(Integer bankId, StorageUnitVo storageUnitVo) {
		// TODO Auto-generated method stub
		BloodBank bloodBank =	bloodBankRepo.findByBankIdAndActive(bankId, true)
				.orElseThrow(()-> new BloodBankBusinessException(ErrorConstants.DONATION_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
		      
		Integer capacityOfBank =  bloodBank.getTotalCapacityML();
		
		if(capacityOfBank < storageUnitVo.getCapacityML())
    	     throw 	new BloodBankBusinessException(ErrorConstants.NO_STORAGE_UNIT,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA);

		StorageUnit storageUnit = new StorageUnit();
		
		Optional.ofNullable(storageUnitVo.getStorageType()).ifPresent(storageUnit::setStorageType);
		Optional.ofNullable(storageUnitVo.getName()).ifPresent(storageUnit::setName);
		Optional.ofNullable(storageUnitVo.getType()).ifPresent(storageUnit::setType);
		Optional.ofNullable(storageUnitVo.getTemperatureLog()).ifPresent(storageUnit::setTemperatureLog);
		Optional.ofNullable(storageUnitVo.getMetadata()).ifPresent(storageUnit::setMetadata);
		Optional.ofNullable(storageUnitVo.getCapacityML()).ifPresent(storageUnit::setCapacityML);
//		 saving storage unti 
		storageUnit = storageUnitrepo.save(storageUnit);
		
		bloodBank.setTotalCapacityML(bloodBank.getTotalCapacityML()-storageUnit.getCapacityML());
		bloodBankRepo.save(bloodBank);
		
		return StorageUnitDto.builder()
				.storageUnitId(storageUnit.getStorageUnitId())
				.storageType(storageUnit.getStorageType().toString())
				.name(storageUnit.getName())
				.type(storageUnit.getType())
				.capacityML(storageUnit.getCapacityML())
				.temperatureLog(storageUnit.getTemperatureLog())
				.metadata(null)
				.bloodBank(BloodBankDto.builder()
						.name(bloodBank.getName())
						.totalCapacityML(bloodBank.getTotalCapacityML())
						.build())
				.build();
	}
	



}
