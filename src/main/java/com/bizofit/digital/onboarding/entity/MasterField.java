package com.bizofit.digital.onboarding.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * The persistent class for the master_field database table.
 * 
 */
@Entity
@Table(name="master_field")
@NamedQuery(name="MasterField.findAll", query="SELECT m FROM MasterField m")
@NoArgsConstructor
@Data
public class MasterField implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="field_name")
	private String fieldName;
	
	@Column(name="field_type")
	private String fieldType;

	//bi-directional many-to-one association to MasterFieldSetConfig
	@OneToMany(mappedBy="masterField")
	private List<MasterFieldSetConfig> masterFieldSetConfigs;
}