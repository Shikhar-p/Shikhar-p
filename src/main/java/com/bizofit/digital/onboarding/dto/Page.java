package com.bizofit.digital.onboarding.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {
	
	private String pageNumber;
	private String pageName;
	private List<Field> fields = new ArrayList<>();

}
