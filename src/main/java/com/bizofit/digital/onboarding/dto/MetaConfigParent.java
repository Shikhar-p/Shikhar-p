package com.bizofit.digital.onboarding.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MetaConfigParent {
	
	private String applicationId;
    private String applicationName;
    private List<Page> pages = new ArrayList<Page>();

}
