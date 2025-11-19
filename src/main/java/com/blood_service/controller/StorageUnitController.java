package com.blood_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blood_service.dto.StorageUnitDto;
import com.blood_service.service.StorageUnitService;
import com.blood_service.vo.StorageUnitVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/storage-unit")
@RequiredArgsConstructor
public class StorageUnitController {
	
	private final StorageUnitService storageUnitService;
	
	@PostMapping("/add-storageunit-to-bank/{bankId}")
	public ResponseEntity<StorageUnitDto>  addStorageUnittoBloodBank(@PathVariable Integer bankId ,@RequestBody StorageUnitVo storageUnitVo) {
		StorageUnitDto storageUnitDto  =  storageUnitService.addStorageUnittoBloodBank(bankId, storageUnitVo);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(storageUnitDto);	
	}


}
