package com.bizofit.digital.onboarding.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the master_field_set_group_config database table.
 * 
 */
@Entity
@Table(name="master_field_set_group_config")
@NamedQuery(name="MasterFieldSetGroupConfig.findAll", query="SELECT m FROM MasterFieldSetGroupConfig m")
@NoArgsConstructor
public class MasterFieldSetGroupConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Setter
	@Getter
	private String id;

	@Setter
	@Getter
	private Integer order;

	@Setter
	@Getter
	private Integer version;

	//bi-directional many-to-one association to MasterFieldSet
	@ManyToOne
	@JoinColumn(name="field_set_id")
	@Setter
	@Getter
	private MasterFieldSet masterFieldSet;

	//bi-directional many-to-one association to MasterFieldSetGroup
	@ManyToOne
	@JoinColumn(name="field_set_grp_id")
	@Setter
	@Getter
	private MasterFieldSetGroup masterFieldSetGroup;
}