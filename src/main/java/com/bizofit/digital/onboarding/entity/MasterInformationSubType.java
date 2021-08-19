package com.bizofit.digital.onboarding.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * The persistent class for the master_information_sub_type database table.
 * 
 */
@Entity
@Table(name="master_information_sub_type")
@NamedQuery(name="MasterInformationSubType.findAll", query="SELECT m FROM MasterInformationSubType m")
@NoArgsConstructor
public class MasterInformationSubType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Setter
	@Getter
	private String id;

	@Setter
	@Getter
	private String type;

	//bi-directional many-to-one association to MasterConfig
	@OneToMany(mappedBy="masterInformationSubType")
	@Setter
	@Getter
	private List<MasterConfig> masterConfigs;
}