package com.dfw.banking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfw.banking.master.field.dto.Field;
import com.dfw.banking.master.field.dto.FieldSet;
import com.dfw.banking.repository.MasterFieldSetRepository;

@Service
public class MasterFieldSetService {

	@Autowired
	private MasterFieldSetRepository masterFieldSetRepository;

	public String getResponse(String fieldId) {
		List<Map<String, Object>> queryForList = masterFieldSetRepository.getResponse(fieldId);
		JSONArray array = new JSONArray();
		for (Map<String, Object> row : queryForList) {
			System.out.println("ROW: " + row);
			JSONObject field = new JSONObject();
			field.put("id", row.get("id"));
			field.put("field_name", row.get("field_name"));
			field.put("field_set_name", row.get("field_set_name"));
			field.put("field_set_id", row.get("field_set_id"));
			array.put(field);
		}
		System.out.println("JSON respnse => " + array);

		return array.toString();
	}

	public List<FieldSet> getResponseForAll() {
		List<Map<String, Object>> queryForList = masterFieldSetRepository.getAllResponse();
		JSONArray array = new JSONArray();
		Map<String, List<Field>> fieldSet = new HashMap<String, List<Field>>();
		for (Map<String, Object> row : queryForList) {
			System.out.println("ROW: " + row);
			Field field = new Field();
			field.setFieldId("" + row.get("id"));
			field.setFieldName("" + row.get("field_name"));
			field.setFieldType("" + "String");
			if (fieldSet.containsKey(row.get("field_set_id") + ":" + row.get("field_set_name"))) {
				List<Field> fieldsIn = fieldSet.get(row.get("field_set_id") + ":" + row.get("field_set_name"));
				fieldsIn.add(field);
			} else {
				List<Field> list = new ArrayList<>();
				list.add(field);
				fieldSet.put(row.get("field_set_id") + ":" + row.get("field_set_name"), list);
			}
		}
		System.out.println("JSON respnse => " + array);
		List<FieldSet> fieldSets = new ArrayList<>();
		for (Map.Entry<String, List<Field>> entry : fieldSet.entrySet()) {
			FieldSet fieldSetVar = new FieldSet();
			fieldSetVar.setFields(entry.getValue());
			String key = entry.getKey();
			String[] vals = key.split(":");
			fieldSetVar.setFieldSetId(vals[0]);
			fieldSetVar.setFieldSetName(vals[1]);
			fieldSets.add(fieldSetVar);
		}
		return fieldSets;
	}
}
