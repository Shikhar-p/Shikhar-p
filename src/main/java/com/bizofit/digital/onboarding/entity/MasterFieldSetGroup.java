package com.bizofit.digital.onboarding.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * The persistent class for the master_field_set_group database table.
 * 
 */
@Entity
@Table(name="master_field_set_group")
@NamedQuery(name="MasterFieldSetGroup.findAll", query="SELECT m FROM MasterFieldSetGroup m")
@NoArgsConstructor
public class MasterFieldSetGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Setter
	@Getter
	private String id;

	@Column(name="field_set_group_name")
	@Setter
	@Getter
	private String fieldSetGroupName;

	//bi-directional many-to-one association to MasterConfig
	@OneToMany(mappedBy="masterFieldSetGroup")
	@Setter
	@Getter
	private List<MasterConfig> masterConfigs;

	//bi-directional many-to-one association to MasterFieldSetGroupConfig
	@OneToMany(mappedBy="masterFieldSetGroup")
	@Setter
	@Getter
	private List<MasterFieldSetGroupConfig> masterFieldSetGroupConfigs;

}