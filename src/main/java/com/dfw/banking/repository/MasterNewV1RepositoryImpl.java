package com.dfw.banking.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class MasterNewV1RepositoryImpl implements MasterNewV1Repository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getQueryResponse() {
		String sql = "select distinct t1.order,t1.\"Information_type\",t1.\"Information_sub_type\",t1.occurance,t1.\"applicable_condition\",\r\n"
				+ "t1.\"field_set_group\",t1.\"field_group_id\",t2.\"field_set_name\",t2.\"scope\" ,t1.product_application_scheme,t4.field_name,\r\n"
				+ "t3.field_id \r\n" + "from master_new_v1 as t1\r\n" + "INNER JOIN master_field_set_group as t2\r\n"
				+ "on t1.field_group_id = t2.group_id\r\n" + "inner join master_field_set  as t3\r\n"
				+ "on t2.field_set_id = t3.field_set_id\r\n" + "inner join master_field as t4\r\n"
				+ "on t4.id = t3.field_id\r\n" + "where t1.product_application_scheme ='individual_saving'\r\n"
				+ "and t2.scope like '%UK%' and t3.\"scope\" like'%UK%';";
		System.out.println("SQL :: " + sql);
		try {
			return jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			System.out.println("Error in getting result inMasterNewV1");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Error in getting result for MasterNewV1", e);
		}
	}

}
