package com.blood_service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blood_service.service.BloodInventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/blood-inventory")
@RequiredArgsConstructor
public class BloodInventoryController {
	
	private final BloodInventoryService bloodInventoryService;
	

}
