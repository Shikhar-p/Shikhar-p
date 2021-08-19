package com.bizofit.digital.onboarding.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the master_product_application_scheme database
 * table.
 * 
 */
@Entity
@Table(name = "master_product_application_scheme")
@NamedQuery(name = "MasterProductApplicationScheme.findAll", query = "SELECT m FROM MasterProductApplicationScheme m")
@NoArgsConstructor
@Data
public class MasterProductApplicationScheme implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	// bi-directional many-to-one association to MasterConfig
	@OneToMany(mappedBy = "masterProductApplicationScheme")
	private List<MasterConfig> masterConfigs;

}