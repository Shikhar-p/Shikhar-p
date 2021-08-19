package com.bizofit.digital.onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterFieldSetGroupDTO {

	private String id;

	private String fieldSetGroupName;
}
