package com.blood_service.service;

import com.blood_service.dto.StorageUnitDto;
import com.blood_service.vo.StorageUnitVo;

public interface StorageUnitService {
	
	public StorageUnitDto addStorageUnittoBloodBank(Integer bankId , StorageUnitVo storageUnitVo);

}
