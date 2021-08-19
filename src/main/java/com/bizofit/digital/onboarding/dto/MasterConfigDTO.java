package com.bizofit.digital.onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterConfigDTO {

	private String id;

	private Boolean applicableCondition;

	private String occurance;

	private Integer order;
}
