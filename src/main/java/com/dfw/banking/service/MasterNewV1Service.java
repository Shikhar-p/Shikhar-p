package com.dfw.banking.service;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfw.banking.repository.MasterNewV1Repository;

@Service
public class MasterNewV1Service {

	@Autowired
	private MasterNewV1Repository v1Repository;

	public String getResponseForMasterNewV1() {
		List<Map<String, Object>> queryResponse = v1Repository.getQueryResponse();
		String infoType = null;
		JSONObject finalResponse= new JSONObject();
		JSONArray array = null;

		for (Map<String, Object> row : queryResponse) {
			if(null == infoType) {
				infoType = (String) row.get("Information_type");
				array = new JSONArray();
			} else if(null != infoType && !infoType.equals(row.get("Information_type"))) {
				finalResponse.put(infoType, array);
				infoType = (String) row.get("Information_type");
				array = new JSONArray();
			}

			JSONObject type = new JSONObject();
			type.put("Information_sub_type", row.get("Information_sub_type"));
			type.put("Information_sub_type_value", row.get("Information_sub_type"));

			JSONObject field = new JSONObject();
			field.put("field_id", row.get("field_id"));
			field.put("field_name", row.get("field_name"));
			type.put("field_data", field);

			array.put(type);
		}
		finalResponse.put(infoType, array);
		System.out.println("final response :: " +finalResponse);
		return finalResponse.toString();
	}
}
