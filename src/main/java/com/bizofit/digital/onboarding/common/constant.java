package com.bizofit.digital.onboarding.common;

public class constant {
	
	public static String MASTER_CONFIG_META = "select t10.id  as application_id,t10.\"name\"  as application_name,t9.\"type\" as master_information_type, \r\n"
			+ "t8.\"type\" as master_information_sub_type,t1.\"order\" as field_group_order,t4.\"order\" as field_order,t1.occurance as group_occurance,t1.applicable_condition,t2.country_name,\r\n"
			+ "t3.field_set_group_name,\r\n"
			+ "t4.\"version\"  as field_set_version,t5.field_set_name,t6.is_mandatory as field_mandatory,\r\n"
			+ "t6.condition_is_mandatory as field_condition_is_mandatory,t6.\"version\"  as field_version,t7.field_name,t7.id as field_id,t7.field_type from master_config t1\r\n"
			+ "inner join master_country t2\r\n"
			+ "on t1.master_country_id = t2.id \r\n"
			+ "inner join master_field_set_group t3\r\n"
			+ "on t1.master_field_set_group_id = t3.id \r\n"
			+ "inner join master_field_set_group_config  t4\r\n"
			+ "on t3.id = t4.field_set_grp_id \r\n"
			+ "inner join master_field_set t5\r\n"
			+ "on t4.field_set_id = t5.id \r\n"
			+ "inner join master_field_set_config t6\r\n"
			+ "on t5.id = t6.field_set_id \r\n"
			+ "inner join master_field t7\r\n"
			+ "on t6.field_id = t7.id \r\n"
			+ "inner join master_information_sub_type t8\r\n"
			+ "on t1.master_information_sub_type_id = t8.id \r\n"
			+ "inner join master_information_type t9\r\n"
			+ "on t1.master_information_type_id = t9.id\r\n"
			+ "inner join master_product_application_scheme t10\r\n"
			+ "on t1.master_production_app_scheme_id = t10.id \r\n"
			+ "where t10.id = ? \r\n"
			+ "order by t1.\"order\" asc  ,t6.\"order\" asc";

}
