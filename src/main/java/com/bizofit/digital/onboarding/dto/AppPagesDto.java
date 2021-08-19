package com.bizofit.digital.onboarding.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppPagesDto {
	
	private String applicationId;
	private String applicationName;
	
	List<Page> pages;

}
