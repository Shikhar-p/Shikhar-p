package com.dfw.banking;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class BankingAppApplication  {//implements CommandLineRunner

	private static final Logger log = LoggerFactory.getLogger(BankingAppApplication.class);

	@Autowired
    private JdbcTemplate jdbcTemplate;
//	@PersistenceContext
//    EntityManager entityManager;

	private List<Map<String, Object>> queryForList;

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		customQueryExecution();
//	}

	private void customQueryExecution() {
		String sql = "select distinct master_field.id ,master_field.field_name ,master_field_set.field_set_name,"
				+ "master_field_set.field_set_id  from master_field_set "
				+ "inner join master_field_set_name   "
				+ "on master_field_set_name.id  = master_field_set.field_set_id "
				+ "inner join  master_field on "
				+ "master_field.id  = master_field_set.field_id "
				+ "where master_field_set.field_set_id='ba916753-40ac-41ff-acf7-c8d069816937' "
				+ "order by master_field_set.field_set_name ";
		System.out.println("SQL :: " + sql);
		queryForList = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : queryForList) {
            System.out.println("ROW: " + row);
        }
		
//		String sql = "select distinct master_field.id ,master_field.field_name ,master_field_set.field_set_name,"
//				+ "master_field_set.field_set_id  from master_field_set "
//				+ "inner join master_field_set_name   "
//				+ "on master_field_set_name.id  = master_field_set.field_set_id "
//				+ "inner join  master_field on "
//				+ "master_field.id  = master_field_set.field_id "
//				+ "where master_field_set.field_set_id='ba916753-40ac-41ff-acf7-c8d069816937' "
//				+ "order by master_field_set.field_set_name ";
//		Query query = entityManager.createQuery(sql);
//		List resultList = query.getResultList();
//        System.out.println(resultList + " ---- and size => " + resultList.size());
	}

}
