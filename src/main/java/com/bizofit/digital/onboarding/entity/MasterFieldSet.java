package com.bizofit.digital.onboarding.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * The persistent class for the master_field_set database table.
 * 
 */
@Entity
@Table(name="master_field_set")
@NamedQuery(name="MasterFieldSet.findAll", query="SELECT m FROM MasterFieldSet m")
@NoArgsConstructor
public class MasterFieldSet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Setter
	@Getter
	private String id;

	@Column(name="field_set_name")
	@Setter
	@Getter
	private String fieldSetName;

	//bi-directional many-to-one association to MasterFieldSetConfig
	@OneToMany(mappedBy="masterFieldSet")
	@Setter
	@Getter
	private List<MasterFieldSetConfig> masterFieldSetConfigs;

	//bi-directional many-to-one association to MasterFieldSetGroupConfig
	@OneToMany(mappedBy="masterFieldSet")
	@Setter
	@Getter
	private List<MasterFieldSetGroupConfig> masterFieldSetGroupConfigs;
}