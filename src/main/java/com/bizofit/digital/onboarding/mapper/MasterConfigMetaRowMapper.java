package com.bizofit.digital.onboarding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bizofit.digital.onboarding.dto.MasterConfigWithMetaDTO;

public class MasterConfigMetaRowMapper implements RowMapper<MasterConfigWithMetaDTO> {
    @Override
    public MasterConfigWithMetaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    	MasterConfigWithMetaDTO masterConfig = new MasterConfigWithMetaDTO();
    	masterConfig.setApplicationName(rs.getString("application_name"));
    	masterConfig.setApplicationId(rs.getString("application_id"));
    	masterConfig.setMasterInformationSubType(rs.getString("master_information_sub_type"));
    	masterConfig.setFieldGroupOrder(rs.getString("field_group_order"));
    	masterConfig.setFieldOrder(rs.getString("field_order"));
    	masterConfig.setApplicableCondition(rs.getString("applicable_condition"));
    	masterConfig.setCountryName(rs.getString("country_name"));
    	masterConfig.setFieldSetGroupName(rs.getString("field_set_group_name"));
    	masterConfig.setFieldSetVersion(rs.getString("field_set_version"));
    	masterConfig.setFieldSetName(rs.getString("field_set_name"));
    	masterConfig.setFieldRequired(rs.getString("field_mandatory"));
    	masterConfig.setFieldName(rs.getString("field_name"));
    	masterConfig.setFieldId(rs.getString("field_id"));
    	masterConfig.setFieldType(rs.getString("field_type"));
        return masterConfig;
    }
}