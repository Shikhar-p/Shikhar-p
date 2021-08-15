package com.dfw.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dfw.banking.master.field.dto.FieldSet;
import com.dfw.banking.service.MasterFieldSetService;

@RestController
@RequestMapping(path = "/masterfieldset")
public class MasterFieldSetController {

	@Autowired
	private MasterFieldSetService masterFieldSetService;

	// http://localhost:8080/masterfieldset/getfieldresponse?fieldId=ba916753-40ac-41ff-acf7-c8d069816937
	@GetMapping(value = "/getfieldresponse", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getFieldResponse(@RequestParam String fieldId) {
		if (null == fieldId || fieldId.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send field id with request");
		}
		String response = masterFieldSetService.getResponse(fieldId);
		System.out.println("Response from controller => " + response);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(value = "/v1/field/sets", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllFields() {
		List<FieldSet> response = masterFieldSetService.getResponseForAll();
		System.out.println("Response from controller => " + response);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
