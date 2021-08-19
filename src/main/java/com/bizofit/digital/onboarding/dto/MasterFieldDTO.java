package com.bizofit.digital.onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterFieldDTO {

	private String id;

	private String fieldName;
	
	private String fieldType;
}
