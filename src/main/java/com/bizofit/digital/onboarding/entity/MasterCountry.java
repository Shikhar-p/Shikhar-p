package com.bizofit.digital.onboarding.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * The persistent class for the master_country database table.
 * 
 */
@Entity
@Table(name = "master_country")
@NamedQuery(name = "MasterCountry.findAll", query = "SELECT m FROM MasterCountry m")
@NoArgsConstructor
@Data
public class MasterCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name = "country_name")
	private String countryName;

	// bi-directional many-to-one association to MasterConfig
	@OneToMany(mappedBy = "masterCountry")
	private List<MasterConfig> masterConfigs;	
	
}