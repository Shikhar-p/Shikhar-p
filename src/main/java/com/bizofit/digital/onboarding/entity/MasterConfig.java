package com.bizofit.digital.onboarding.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the master_config database table.
 * 
 */
@Entity
@Table(name = "master_config")
@NamedQuery(name = "MasterConfig.findAll", query = "SELECT m FROM MasterConfig m")
@NoArgsConstructor
@Data
public class MasterConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name = "applicable_condition")
	private Boolean applicableCondition;

	private String occurance;

	private Integer order;

	// bi-directional many-to-one association to MasterCountry
	@ManyToOne
	@JoinColumn(name = "master_country_id")
	private MasterCountry masterCountry;

	// bi-directional many-to-one association to MasterFieldSetGroup
	@ManyToOne
	@JoinColumn(name = "master_field_set_group_id")
	private MasterFieldSetGroup masterFieldSetGroup;

	// bi-directional many-to-one association to MasterInformationSubType
	@ManyToOne
	@JoinColumn(name = "master_information_sub_type_id")
	private MasterInformationSubType masterInformationSubType;

	// bi-directional many-to-one association to MasterInformationType
	@ManyToOne
	@JoinColumn(name = "master_information_type_id")
	private MasterInformationType masterInformationType;

	// bi-directional many-to-one association to MasterProductApplicationScheme
	@ManyToOne
	@JoinColumn(name = "master_production_app_scheme_id")
	private MasterProductApplicationScheme masterProductApplicationScheme;

}