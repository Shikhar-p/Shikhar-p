package com.bizofit.digital.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bizofit.digital.onboarding.common.constant;
import com.bizofit.digital.onboarding.dto.MasterConfigWithMetaDTO;
import com.bizofit.digital.onboarding.mapper.MasterConfigMetaRowMapper;

@Service
public class MasterDataServiceCustom {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	MasterConfigMetaRowMapper masterConfigMetaRowMapper = new MasterConfigMetaRowMapper();

	public List<MasterConfigWithMetaDTO> getResponseForMasterConfigMeta(String applicationId) {
		List<MasterConfigWithMetaDTO> masterConfigMetas = jdbcTemplate.query(constant.MASTER_CONFIG_META, masterConfigMetaRowMapper, applicationId);
		return masterConfigMetas;
	}

}
