package com.dfw.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dfw.banking.service.MasterNewV1Service;

@RestController
@RequestMapping(path = "/masternewv1")
public class MasterNewV1Contoller {

	@Autowired
	private MasterNewV1Service v1Service;

	@GetMapping(path = "/getallrecords", produces = "application/json")
	public ResponseEntity<?> getAllRecordsForMasterNewV1() {
		return ResponseEntity.status(HttpStatus.OK).body(v1Service.getResponseForMasterNewV1());
	}
}
