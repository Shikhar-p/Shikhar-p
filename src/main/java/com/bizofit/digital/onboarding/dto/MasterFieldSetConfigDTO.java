package com.bizofit.digital.onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterFieldSetConfigDTO {

	private String id;

	private String conditionIsMandatory;

	private String isMandatory;

	private Integer order;

	private Integer version;
}
