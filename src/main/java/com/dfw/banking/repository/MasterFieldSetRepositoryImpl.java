package com.dfw.banking.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class MasterFieldSetRepositoryImpl implements MasterFieldSetRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getResponse(String fieldId) {
		// 'ba916753-40ac-41ff-acf7-c8d069816937'
		String sql = "select distinct master_field.id ,master_field.field_name ,master_field_set.field_set_name,"
				+ "master_field_set.field_set_id  from master_field_set " + "inner join master_field_set_name   "
				+ "on master_field_set_name.id  = master_field_set.field_set_id " + "inner join  master_field on "
				+ "master_field.id  = master_field_set.field_id " + "where master_field_set.field_set_id= '" + fieldId
				+ "' " + "order by master_field_set.field_set_name;";
		System.out.println("SQL :: " + sql);
		try {
			return jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			System.out.println("Error in getting result for passing field id " + fieldId);
//			throw new ClassNotFoundException("Error in getting result for passing field id " + fieldId);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Error in getting result for passing field id " + fieldId, e);
		}
	}

	@Override
	public List<Map<String, Object>> getAllResponse() {
		// 'ba916753-40ac-41ff-acf7-c8d069816937'
		String sql = "select distinct master_field.id ,master_field.field_name ,master_field_set.field_set_name,"
				+ "master_field_set.field_set_id  from master_field_set " + "inner join master_field_set_name   "
				+ "on master_field_set_name.id  = master_field_set.field_set_id " + "inner join  master_field on "
				+ "master_field.id  = master_field_set.field_id " + " order by master_field_set.field_set_name;";
		System.out.println("SQL :: " + sql);
		try {
			return jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			System.out.println("Error in getting result for passing field id ");
//			throw new ClassNotFoundException("Error in getting result for passing field id " + fieldId);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in getting result  " + e);
		}
	}
}
