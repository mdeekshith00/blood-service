package com.blood_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blood_service.dto.BloodBankDto;
import com.blood_service.dto.LocationDto;
import com.blood_service.service.BloodBankService;
import com.blood_service.vo.CreateBloodBankVO;
import com.blood_service.vo.LocationVo;
import com.blood_service.vo.UpdateBloodBankVO;
import com.common.dto.BloodInventoryDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/blood-inventory")
@RequiredArgsConstructor
public class BloodBankController {
	
	private final BloodBankService bloodBankService; 
	
	@PostMapping("/create-bank")
	public ResponseEntity<BloodBankDto>  createBloodBank(@RequestBody CreateBloodBankVO createBloodBankVo) {
		BloodBankDto bloodBankDto = bloodBankService.createBloodBank(createBloodBankVo);
		return ResponseEntity.status(HttpStatus.CREATED).body(bloodBankDto);
	}
	@PostMapping("/verify-bank")
	public ResponseEntity<String> verifyBloodBank(@RequestParam Integer bankId) {
		String msg = bloodBankService.verifyBloodBank(bankId);
		return ResponseEntity.status(HttpStatus.CREATED).body(msg);
	}
	@GetMapping
	public ResponseEntity<BloodBankDto> getBloodBank(@PathVariable Integer bankId) {
		BloodBankDto bloodBankDto = bloodBankService.getBloodBank(bankId);
		return ResponseEntity.status(HttpStatus.CREATED).body(bloodBankDto);
	}
	@PutMapping("/update-bloodbank/{bankId}")
	public ResponseEntity<BloodBankDto> updateBloodBank(@PathVariable Integer bankId ,@RequestBody UpdateBloodBankVO createBloodBankVo) {
		BloodBankDto bloodBankDto = bloodBankService.updateBloodBank(bankId, createBloodBankVo);
		return ResponseEntity.status(HttpStatus.CREATED).body(bloodBankDto);
	}
	@PostMapping("/add-location")
	public ResponseEntity<LocationDto> addLocation(@PathVariable Integer bankId ,@RequestBody LocationVo locationVo) {
		LocationDto locationDto = bloodBankService.addLocation(bankId, locationVo);
		return ResponseEntity.status(HttpStatus.CREATED).body(locationDto);
	}
	
	@PostMapping("/send-bloodtobank")
	public ResponseEntity<String> sendBloodToBank(@RequestBody BloodInventoryDto bloodInventoryDto ) {
		 log.info("Received request: {}", bloodInventoryDto.getDonationId());
		String msg = bloodBankService.sendBloodToBank(bloodInventoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(msg);
	}

}
