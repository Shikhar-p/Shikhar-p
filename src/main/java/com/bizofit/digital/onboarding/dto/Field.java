package com.bizofit.digital.onboarding.dto;

import lombok.Data;

@Data
public class Field {
	
	private String displayname;
	private String id;
	private String name;
	private Boolean required;
	private String type;
	private String validationMessage;
	private String widget;
}
