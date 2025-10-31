package com.blood_service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBloodBankVO {
	
    	private String name; // e.g., City Blood Bank, Hospital XYZ Blood Bank
	    private String type; // HOSPITAL, STANDALONE, MOBILE_UNIT
	    private Integer totalCapacityML; // maximum blood volume this bank can store
	    private boolean active; // bank operational or closed
	    private String contactInfo; // phone/email

}
