package com.bizofit.digital.onboarding.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the master_field_set_config database table.
 * 
 */
@Entity
@Table(name="master_field_set_config")
@NamedQuery(name="MasterFieldSetConfig.findAll", query="SELECT m FROM MasterFieldSetConfig m")
public class MasterFieldSetConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Setter
	@Getter
	private String id;

	@Column(name="condition_is_mandatory")
	@Setter
	@Getter
	private String conditionIsMandatory;

	@Column(name="is_mandatory")
	@Setter
	@Getter
	private String isMandatory;

	@Setter
	@Getter
	private Integer order;

	@Setter
	@Getter
	private Integer version;

	//bi-directional many-to-one association to MasterField
	@ManyToOne
	@JoinColumn(name="field_id")
	@Setter
	@Getter
	private MasterField masterField;

	//bi-directional many-to-one association to MasterFieldSet
	@ManyToOne
	@JoinColumn(name="field_set_id")
	@Setter
	@Getter
	private MasterFieldSet masterFieldSet;

}