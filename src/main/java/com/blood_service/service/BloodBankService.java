package com.blood_service.service;

import com.blood_service.dto.BloodBankDto;
import com.blood_service.dto.LocationDto;
import com.blood_service.vo.CreateBloodBankVO;
import com.blood_service.vo.LocationVo;
import com.blood_service.vo.UpdateBloodBankVO;
import com.common.dto.BloodInventoryDto;

public interface BloodBankService {
	
	public BloodBankDto createBloodBank(CreateBloodBankVO createBloodBankVo);
	public String verifyBloodBank(Integer bankId);
	public BloodBankDto getBloodBank(Integer bankId);
	public BloodBankDto updateBloodBank(Integer bankId ,UpdateBloodBankVO createBloodBankVo);
	public LocationDto addLocation(Integer bankId , LocationVo locationVo);
	public String sendBloodToBank(BloodInventoryDto bloodInventoryDto);

}
