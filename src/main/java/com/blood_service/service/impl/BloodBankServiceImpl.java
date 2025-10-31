package com.blood_service.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blood_service.dto.BloodBankDto;
import com.blood_service.dto.LocationDto;
import com.blood_service.dto.StorageUnitDto;
import com.blood_service.entities.BloodBank;
import com.blood_service.entities.BloodInventory;
import com.blood_service.entities.Location;
import com.blood_service.entities.StorageUnit;
import com.blood_service.repositary.BloodBankRepositary;
import com.blood_service.repositary.BloodInventoryRepositary;
import com.blood_service.repositary.LocationRepositary;
import com.blood_service.repositary.StorageUnitRespositary;
import com.blood_service.service.BloodBankService;
import com.blood_service.vo.CreateBloodBankVO;
import com.blood_service.vo.LocationVo;
import com.blood_service.vo.UpdateBloodBankVO;
import com.common.constants.ErrorConstants;
import com.common.dto.BloodInventoryDto;
import com.common.enums.AccessLevel;
import com.common.enums.BloodGroupType;
import com.common.enums.BloodInventoryStatusType;
import com.common.enums.BloodUnitSourceType;
import com.common.enums.PriorityLevel;
import com.common.enums.QuarantineStatus;
import com.common.enums.RhFactor;
import com.common.enums.StorageType;
import com.common.exception.BloodBankBusinessException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BloodBankServiceImpl implements BloodBankService {
	
	private final BloodBankRepositary bloodBankRepositary;
	private final StorageUnitRespositary storageUnitRespositary;
	private final BloodInventoryRepositary bloodInventoryRepositary;
	private final LocationRepositary locationRepositary; 

	@Override
	public BloodBankDto createBloodBank(CreateBloodBankVO createBloodBankVo) {
		// TODO Auto-generated method stub
		BloodBank bank = new BloodBank();
		Optional.ofNullable(createBloodBankVo.getName()).ifPresent(bank::setName);
		Optional.ofNullable(createBloodBankVo.getType()).ifPresent(bank::setType);
		Optional.ofNullable(createBloodBankVo.getTotalCapacityML()).ifPresent(bank::setTotalCapacityML);
		Optional.ofNullable(createBloodBankVo.getContactInfo()).ifPresent(bank::setContactInfo);
		
		bloodBankRepositary.save(bank);
		return  BloodBankDto
				.builder()
				.name(bank.getName())
				.type(bank.getType())
				.totalCapacityML(bank.getTotalCapacityML())
				.contactInfo(bank.getContactInfo())
				.metadataJson(bank.getMetadataJson())
				.build();

	}

	@Override
	public String verifyBloodBank(Integer bankId) {
		// TODO Auto-generated method stub
	  BloodBank bloodBank =	bloodBankRepositary.findById(bankId)
		.orElseThrow(()-> new BloodBankBusinessException(ErrorConstants.DONATION_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
      
	  Optional<Location> bankBloodLocation =  bloodBank.getLocations().stream().findAny();
	  
	  if(bankBloodLocation.isEmpty()) 
	       throw new BloodBankBusinessException(ErrorConstants.DONATION_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA);
	  bloodBank.setActive(true);
	  bloodBankRepositary.save(bloodBank);
	  
	  return "Sucessfully Verified BloodBank ..";
	}

	@Override
	public BloodBankDto getBloodBank(Integer bankId) {
		// TODO Auto-generated method stub
		BloodBank bloodBank =	bloodBankRepositary.findById(bankId)
				.orElseThrow(()-> new BloodBankBusinessException(ErrorConstants.DONATION_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
		      
		Location location = bloodBank.getLocations().get(0);
		StorageUnit stoargeUnit = bloodBank.getStorageUnits().get(0);
		return BloodBankDto.builder()
				.name(bloodBank.getName())
				.type(bloodBank.getType())
				.totalCapacityML(bloodBank.getTotalCapacityML())
				.contactInfo(bloodBank.getContactInfo())
				.metadataJson(bloodBank.getMetadataJson())
				.location(LocationDto.builder()
						.latitude(location.getLatitude())
						.longitude(location.getLongitude())
						.addressLine1(location.getAddressLine1())
						.addressLine2(location.getAddressLine2())
						.city(location.getCity())
						.state(location.getState())
						.country(location.getCountry())
						.postalCode(location.getPostalCode())
						.metadataJson(location.getMetadataJson())
						.storageLocation(location.getStorageLocation().name())
						.createdAt(location.getCreatedAt())
						.updatedAt(location.getUpdatedAt())
						.build())
				.storageUnit(StorageUnitDto.builder()
						.storageUnitId(stoargeUnit.getStorageUnitId())
						.storageType(stoargeUnit.getStorageType().name())
						.name(stoargeUnit.getName())
						.type(stoargeUnit.getType())
						.capacityML(stoargeUnit.getCapacityML())
						.temperatureLog(stoargeUnit.getTemperatureLog())
						.metadata(stoargeUnit.getMetadata())
						.build())
				.build();
		
	}

	@Override
	public BloodBankDto updateBloodBank(Integer bankId, UpdateBloodBankVO createBloodBankVo) {
		// TODO Auto-generated method stub
		BloodBank bloodBank =	bloodBankRepositary.findById(bankId)
				.orElseThrow(()-> new BloodBankBusinessException(ErrorConstants.DONATION_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
		      
		
		return BloodBankDto.builder().build();
	}

	@Override
	public LocationDto addLocation(Integer bankId, LocationVo locationVo) {
		// TODO Auto-generated method stub
		BloodBank bloodBank =	bloodBankRepositary.findById(bankId)
				.orElseThrow(()-> new BloodBankBusinessException(ErrorConstants.DONATION_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
		      
		Location location = new Location();
		Optional.ofNullable(locationVo.getLatitude()).ifPresent(location::setLatitude);
		Optional.ofNullable(locationVo.getLongitude()).ifPresent(location::setLongitude);
		Optional.ofNullable(locationVo.getAddressLine1()).ifPresent(location::setAddressLine1);
		Optional.ofNullable(locationVo.getAddressLine2()).ifPresent(location::setAddressLine2);
		Optional.ofNullable(locationVo.getCity()).ifPresent(location::setCity);
		Optional.ofNullable(locationVo.getState()).ifPresent(location::setState);
		Optional.ofNullable(locationVo.getCountry()).ifPresent(location::setCountry);
		Optional.ofNullable(locationVo.getPostalCode()).ifPresent(location::setPostalCode);
		Optional.ofNullable(locationVo.getStorageLocation()).ifPresent(location::setStorageLocation);
		
		location.setCreatedAt(Instant.now());
		locationRepositary.save(location);
		
		bloodBank.getLocations().add(location);
		bloodBankRepositary.save(bloodBank);
		
		return LocationDto.builder()
				.latitude(location.getLatitude())
				.longitude(location.getLongitude())
				.addressLine1(location.getAddressLine1())
				.addressLine2(location.getAddressLine2())
				.city(location.getCity())
				.state(location.getState())
				.country(location.getCountry())
				.postalCode(location.getPostalCode())
				.metadataJson(location.getMetadataJson())
				.storageLocation(location.getStorageLocation().name())
				.createdAt(location.getCreatedAt())
				.updatedAt(location.getUpdatedAt())
				.build();
	}

	@Override
	@Transactional
	public String sendBloodToBank(BloodInventoryDto bloodInventoryDto) {
		// TODO Auto-generated method stub
		log.info("fetch blood bank name :{}" , bloodInventoryDto.getTargetBloodBank());
		BloodBank bloodBank =	bloodBankRepositary.findByNameIgnoreCaseAndActive(bloodInventoryDto.getTargetBloodBank(), true)
				.orElseThrow(()-> new BloodBankBusinessException(ErrorConstants.DONATION_DETAILS_NOT_FOUND ,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA));
		      
          boolean stoageUnit =  bloodBank.getStorageUnits().isEmpty();
          // checking for storage unit for this blood bank
          if(stoageUnit) 
     	     throw 	new BloodBankBusinessException(ErrorConstants.NO_STORAGE_UNIT,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA);

	    Double components =  bloodInventoryDto.getBloodComponets().stream().filter(Objects::nonNull).mapToDouble(value -> value.getVolumeMl()).sum();
	    int finalValue =  (int) Math.round(components);
	    
	    // Helpers for conversions
	    ZoneId zone = ZoneId.of("Asia/Kolkata"); // your environment timezone
	    Instant collectedAtInstant = null;
	    
	    if (bloodInventoryDto.getCollectedAt() != null) {
	        collectedAtInstant = bloodInventoryDto.getCollectedAt().atZone(zone).toInstant();
	    }

	    List<BloodInventory> inventories = new ArrayList<>();
	    Optional<StorageUnit> remainedUnit = bloodBank.getStorageUnits().stream()
	    		                 .filter(unit -> unit.getCapacityML() >= finalValue).findFirst(); 
        
    	if(remainedUnit.isEmpty()) 
   	     throw 	new BloodBankBusinessException(ErrorConstants.NO_STORAGE_UNIT,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA);
    	
	    if (bloodInventoryDto.getBloodComponets() != null) {
	   
	        for (var comp : bloodInventoryDto.getBloodComponets()) {
	            BloodInventory inventory = new BloodInventory();

	            if (bloodInventoryDto.getBloodGroup() != null) {
	                try {
	                    inventory.setBloodGroup(BloodGroupType.valueOf(bloodInventoryDto.getBloodGroup()));
	                } catch (IllegalArgumentException e) {
	                    throw new BloodBankBusinessException(ErrorConstants.INVALID_BLOOD_GROUP + bloodInventoryDto.getBloodGroup(),HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA);
	                }
	            }

	            // rh factor inference (simple heuristic; adapt if your enum names differ)
	            String bg = bloodInventoryDto.getBloodGroup() != null ? bloodInventoryDto.getBloodGroup().toUpperCase() : "";
	            if (bg.contains("POS") || bg.contains("_POSITIVE") || bg.endsWith("+")) {
	                inventory.setRhFactor(RhFactor.POSITIVE);
	            } else {
	                inventory.setRhFactor(RhFactor.NEGATIVE);
	            }

	            // volume
	            inventory.setVolumeML(comp.getVolumeMl() != null ? comp.getVolumeMl().doubleValue() : 0.0);

	            // source type and id
	            inventory.setSourceType(BloodUnitSourceType.DONOR); // donation-service -> direct donor
	            inventory.setSourceId(bloodInventoryDto.getDonationId() != null ? bloodInventoryDto.getDonationId() : null);

	            // collection and expiry
	            inventory.setCollectionDate(bloodInventoryDto.getCollectedAt());
	            if (comp.getExpiryDate() != null) {
	                inventory.setExpiryDate(comp.getExpiryDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
	            } else {
	                // default expiry: 35 days from collection (example) - change per your rules
	                inventory.setExpiryDate((collectedAtInstant != null ? collectedAtInstant : Instant.now()).plus(Duration.ofDays(35)));
	            }

	            // status - your entity used BloodUnitSourceType for status; assume AVAILABLE present
	            inventory.setStatus(BloodInventoryStatusType.AVAILABLE);
	            inventory.setAssignedTo("BloodBank");
	            inventory.setVerified(true);
	            inventory.setPriorityLevel(PriorityLevel.NORMAL);
	            try {
	                inventory.setComponentType(comp.getComponentType());
	            } catch (IllegalArgumentException e) {
	            	throw new BloodBankBusinessException(ErrorConstants.INVALID_COMPONENT_TYPE,HttpStatus.BAD_REQUEST,ErrorConstants.INVALID_DATA);
	            }
	            inventory.setDonorConsent(true);
	            inventory.setQuarantineStatus(QuarantineStatus.CLEARED);

	            inventory.setBloodFrom(bloodInventoryDto.getDonorId().toString());
	            inventory.setCreatedAt(LocalDateTime.now());
	            inventory.setUpdatedBy(null);
	            inventory.setUpdatedAt(LocalDateTime.now());
	            inventory.setAuditReferenceId(UUID.randomUUID());
	            inventory.setExpiryAlertSent(false);
	            inventory.setEncryptionKeyId(null);
	            inventory.setAccessLevel(AccessLevel.HOSPITAL_ONLY);

	            bloodInventoryRepositary.save(inventory);
	            inventories.add(inventory);
	        
	        }
	    } else {
	        throw new BloodBankBusinessException( ErrorConstants.NO_BLOOD_COMPOMENTS,HttpStatus.BAD_REQUEST,"No blood components provided to store");
	    }
//       saving and updating voulme of storage unit
	    remainedUnit.get().getBloodInventories().addAll(inventories);
	    remainedUnit.get().setCapacityML(remainedUnit.get().getCapacityML()-finalValue);
	    storageUnitRespositary.save(remainedUnit.get());
	    

	    log.info("Stored {} inventory records to bank '{}' (bankId={})",inventories.size(), bloodBank.getName(), bloodBank.getBankId());
	    return "Sucessfully Blood Send to Inventory and StorageUnit ...";
	    
	}

}
