package com.bizofit.digital.onboarding.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bizofit.digital.onboarding.dto.AppPagesDto;
import com.bizofit.digital.onboarding.dto.CountryDTO;
import com.bizofit.digital.onboarding.dto.Field;
import com.bizofit.digital.onboarding.dto.MasterConfigDTO;
import com.bizofit.digital.onboarding.dto.MasterConfigWithMetaDTO;
import com.bizofit.digital.onboarding.dto.MasterFieldDTO;
import com.bizofit.digital.onboarding.dto.MasterFieldSetConfigDTO;
import com.bizofit.digital.onboarding.dto.MasterFieldSetGroupConfigDTO;
import com.bizofit.digital.onboarding.dto.MasterFieldSetGroupDTO;
import com.bizofit.digital.onboarding.dto.MasterInformationSubTypeDTO;
import com.bizofit.digital.onboarding.dto.MasterInformationTypeDTO;
import com.bizofit.digital.onboarding.dto.MasterProductApplicationSchemeDTO;
import com.bizofit.digital.onboarding.dto.Page;
import com.bizofit.digital.onboarding.service.MasterDataService;
import com.bizofit.digital.onboarding.service.MasterDataServiceCustom;

@RestController
@RequestMapping(path = "/v2/master/data")
public class MasterDataController {

	@Autowired
	private MasterDataService masterDataService;

	@Autowired
	private MasterDataServiceCustom masterDataServiceCustom;

	@GetMapping(path = "/countries", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForCountry() {
		List<CountryDTO> data = masterDataService.getAllCountry();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@GetMapping(path = "/countries/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getRecordForCountryById(@PathVariable String id) {
		CountryDTO data = masterDataService.getCountryById(id);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@PostMapping(path = "/countries", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addRecordForCountry(@RequestBody CountryDTO countryDTO) {
		CountryDTO data = masterDataService.addCountry(countryDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
				.toUri();
		return ResponseEntity.created(location).body(data);
	}

	@PutMapping(path = "/countries", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateRecordForCountry(@RequestBody CountryDTO countryDTO) {
		CountryDTO data = masterDataService.updateCountry(countryDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Country updated " + data);
	}

	@DeleteMapping(path = "/countries/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> deleteRecordForCountry(@PathVariable String id) {
		boolean data = masterDataService.deleteCountry(id);
		if (data) {
			ResponseEntity.status(HttpStatus.OK).body("Country deleted successfully");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting Country.");
	}

	@GetMapping(path = "/fields", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForMasterFields() {
		List<MasterFieldDTO> data = masterDataService.getAllMasterFields();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@GetMapping(path = "/fields/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getRecordForMasterFieldById(@PathVariable String id) {
		MasterFieldDTO data = masterDataService.getMasterFieldById(id);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@PostMapping(path = "/fields", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addRecordForMasterField(@RequestBody MasterFieldDTO dto) {
		MasterFieldDTO data = masterDataService.addMasterField(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
				.toUri();
		return ResponseEntity.created(location).body(data);
	}

	@PutMapping(path = "/fields", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateRecordForMasterField(@RequestBody MasterFieldDTO dto) {
		MasterFieldDTO data = masterDataService.updateMasterField(dto);
		return ResponseEntity.status(HttpStatus.OK).body("MasterField updated " + data);
	}

	@DeleteMapping(path = "/fields/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> deleteRecordForMasterField(@PathVariable String id) {
		boolean data = masterDataService.deleteMasterField(id);
		if (data) {
			ResponseEntity.status(HttpStatus.OK).body("MasterField deleted successfully");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting MasterField.");
	}

	@GetMapping(path = "/fieldsets", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForMasterFieldSets() {
		List<MasterFieldDTO> data = masterDataService.getAllMasterFields();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@GetMapping(path = "/fieldsets/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getRecordForMasterFieldSetById(@PathVariable String id) {
		MasterFieldDTO data = masterDataService.getMasterFieldById(id);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@PostMapping(path = "/fieldsets", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addRecordForMasterFieldSet(@RequestBody MasterFieldDTO dto) {
		MasterFieldDTO data = masterDataService.addMasterField(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
				.toUri();
		return ResponseEntity.created(location).body(data);
	}

	@PutMapping(path = "/fieldsets", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateRecordForMasterFieldSet(@RequestBody MasterFieldDTO dto) {
		MasterFieldDTO data = masterDataService.updateMasterField(dto);
		return ResponseEntity.status(HttpStatus.OK).body("MasterField updated " + data);
	}

	@DeleteMapping(path = "/fieldsets/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> deleteRecordForMasterFieldSet(@PathVariable String id) {
		boolean data = masterDataService.deleteMasterField(id);
		if (data) {
			ResponseEntity.status(HttpStatus.OK).body("MasterField deleted successfully");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting MasterField.");
	}

	@GetMapping(path = "/fieldsetconfigs", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForMasterFieldSetConfigs() {
		List<MasterFieldSetConfigDTO> data = masterDataService.getAllMasterFieldSetConfigs();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@GetMapping(path = "/fieldsetconfigs/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getRecordForMasterFieldSetConfigSetById(@PathVariable String id) {
		MasterFieldSetConfigDTO data = masterDataService.getMasterFieldSetConfigById(id);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@PostMapping(path = "/fieldsetconfigs", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addRecordForMasterFieldSetConfig(@RequestBody MasterFieldSetConfigDTO dto) {
		MasterFieldSetConfigDTO data = masterDataService.addMasterFieldSetConfig(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
				.toUri();
		return ResponseEntity.created(location).body(data);
	}

	@PutMapping(path = "/fieldsetconfigs", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateRecordForMasterFieldSetConfig(@RequestBody MasterFieldSetConfigDTO dto) {
		MasterFieldSetConfigDTO data = masterDataService.updateMasterFieldSetConfig(dto);
		return ResponseEntity.status(HttpStatus.OK).body("MasterFieldSetConfig updated " + data);
	}

	@DeleteMapping(path = "/fieldsetconfigs/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> deleteRecordForMasterFieldSetConfig(@PathVariable String id) {
		boolean data = masterDataService.deleteMasterFieldSetConfig(id);
		if (data) {
			ResponseEntity.status(HttpStatus.OK).body("MasterFieldSetConfig deleted successfully");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting MasterFieldSetConfig.");
	}

	@GetMapping(path = "/fieldsetgroups", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForMasterFieldSetGroups() {
		List<MasterFieldSetGroupDTO> data = masterDataService.getAllMasterFieldSetGroups();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@GetMapping(path = "/fieldsetgroups/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getRecordForMasterFieldSetGroupSetById(@PathVariable String id) {
		MasterFieldSetGroupDTO data = masterDataService.getMasterFieldSetGroupById(id);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@PostMapping(path = "/fieldsetgroups", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addRecordForMasterFieldSetGroup(@RequestBody MasterFieldSetGroupDTO dto) {
		MasterFieldSetGroupDTO data = masterDataService.addMasterFieldSetGroup(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
				.toUri();
		return ResponseEntity.created(location).body(data);
	}

	@PutMapping(path = "/fieldsetgroups", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateRecordForMasterFieldSetGroup(@RequestBody MasterFieldSetGroupDTO dto) {
		MasterFieldSetGroupDTO data = masterDataService.updateMasterFieldSetGroup(dto);
		return ResponseEntity.status(HttpStatus.OK).body("MasterFieldSetGroup updated " + data);
	}

	@DeleteMapping(path = "/fieldsetgroups/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> deleteRecordForMasterFieldSetGroup(@PathVariable String id) {
		boolean data = masterDataService.deleteMasterFieldSetConfig(id);
		if (data) {
			ResponseEntity.status(HttpStatus.OK).body("MasterFieldSetGroup deleted successfully");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting MasterFieldSetGroup.");
	}

	@GetMapping(path = "/fieldsetgroupconfigs", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForMasterFieldSetGroupConfigs() {
		List<MasterFieldSetGroupConfigDTO> data = masterDataService.getAllMasterFieldSetGroupConfigs();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@GetMapping(path = "/fieldsetgroupconfigs/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getRecordForMasterFieldSetGroupConfigById(@PathVariable String id) {
		MasterFieldSetGroupConfigDTO data = masterDataService.getMasterFieldSetGroupConfigById(id);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@PostMapping(path = "/fieldsetgroupconfigs", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addRecordForMasterFieldSetGroupConfig(@RequestBody MasterFieldSetGroupConfigDTO dto) {
		MasterFieldSetGroupConfigDTO data = masterDataService.addMasterFieldSetGroupConfig(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
				.toUri();
		return ResponseEntity.created(location).body(data);
	}

	@PutMapping(path = "/fieldsetgroupconfigs", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateRecordForMasterFieldSetGroupConfig(@RequestBody MasterFieldSetGroupConfigDTO dto) {
		MasterFieldSetGroupConfigDTO data = masterDataService.updateMasterFieldSetGroupConfig(dto);
		return ResponseEntity.status(HttpStatus.OK).body("MasterFieldSetGroupConfig updated " + data);
	}

	@DeleteMapping(path = "/fieldsetgroupconfigs/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> deleteRecordForMasterFieldSetGroupConfig(@PathVariable String id) {
		boolean data = masterDataService.deleteMasterFieldSetGroupConfig(id);
		if (data) {
			ResponseEntity.status(HttpStatus.OK).body("MasterFieldSetGroupConfig deleted successfully");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting MasterFieldSetGroupConfig.");
	}

	@GetMapping(path = "/informationsubtypes", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForMasterInformationSubTypes() {
		List<MasterInformationSubTypeDTO> data = masterDataService.getAllMISTypes();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@GetMapping(path = "/informationsubtypes/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getRecordForMasterInformationSubTypeById(@PathVariable String id) {
		MasterInformationSubTypeDTO data = masterDataService.getMISTypeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@PostMapping(path = "/informationsubtypes", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addRecordForMasterInformationSubType(@RequestBody MasterInformationSubTypeDTO dto) {
		MasterInformationSubTypeDTO data = masterDataService.addMasterInformationSubType(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
				.toUri();
		return ResponseEntity.created(location).body(data);
	}

	@PutMapping(path = "/informationsubtypes", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateRecordForMasterInformationSubType(@RequestBody MasterInformationSubTypeDTO dto) {
		MasterInformationSubTypeDTO data = masterDataService.updateMasterInformationSubType(dto);
		return ResponseEntity.status(HttpStatus.OK).body("MasterInformationSubType updated " + data);
	}

	@DeleteMapping(path = "/informationsubtypes/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> deleteRecordForMasterInformationSubType(@PathVariable String id) {
		boolean data = masterDataService.deleteMasterInformationSubType(id);
		if (data) {
			ResponseEntity.status(HttpStatus.OK).body("MasterInformationSubType deleted successfully");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting MasterInformationSubType.");
	}

	@GetMapping(path = "/informationtypes", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForMasterInformationTypes() {
		List<MasterInformationTypeDTO> data = masterDataService.getAllMasterInformationTypes();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@GetMapping(path = "/informationtypes/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getRecordForMasterInformationTypeById(@PathVariable String id) {
		MasterInformationTypeDTO data = masterDataService.getMasterInformationTypeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@PostMapping(path = "/informationtypes", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addRecordForMasterInformationType(@RequestBody MasterInformationTypeDTO dto) {
		MasterInformationTypeDTO data = masterDataService.addMasterInformationType(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
				.toUri();
		return ResponseEntity.created(location).body(data);
	}

	@PutMapping(path = "/informationtypes", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateRecordForMasterInformationType(@RequestBody MasterInformationTypeDTO dto) {
		MasterInformationTypeDTO data = masterDataService.updateMasterInformationType(dto);
		return ResponseEntity.status(HttpStatus.OK).body("MasterInformationSubType updated " + data);
	}

	@DeleteMapping(path = "/informationtypes/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> deleteRecordForMasterInformationType(@PathVariable String id) {
		boolean data = masterDataService.deleteMasterInformationType(id);
		if (data) {
			ResponseEntity.status(HttpStatus.OK).body("MasterInformationSubType deleted successfully");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting MasterInformationSubType.");
	}

	@GetMapping(path = "/productapplicationschemes", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForMasterProductApplicationSchemes() {
		List<MasterProductApplicationSchemeDTO> data = masterDataService.getAllMasterProductApplicationSchemes();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@GetMapping(path = "/productapplicationschemes/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getRecordForMasterProductApplicationSchemeById(@PathVariable String id) {
		MasterProductApplicationSchemeDTO data = masterDataService.getMasterProductApplicationSchemeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@PostMapping(path = "/productapplicationschemes", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addRecordForMasterProductApplicationScheme(
			@RequestBody MasterProductApplicationSchemeDTO dto) {
		MasterProductApplicationSchemeDTO data = masterDataService.addMasterProductApplicationScheme(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getFormId())
				.toUri();
		return ResponseEntity.created(location).body(data);
	}

	@PutMapping(path = "/productapplicationschemes", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateRecordForMasterProductApplicationScheme(
			@RequestBody MasterProductApplicationSchemeDTO dto) {
		MasterProductApplicationSchemeDTO data = masterDataService.updateMasterProductApplicationScheme(dto);
		return ResponseEntity.status(HttpStatus.OK).body("MasterInformationSubType updated " + data);
	}

	@DeleteMapping(path = "/productapplicationschemes/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> deleteRecordForMasterProductApplicationScheme(@PathVariable String id) {
		boolean data = masterDataService.deleteMasterProductApplicationScheme(id);
		if (data) {
			ResponseEntity.status(HttpStatus.OK).body("MasterProductApplicationScheme deleted successfully");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting MasterProductApplicationScheme.");
	}

	@GetMapping(path = "/masterconfigs", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForMasterConfigs() {
		List<MasterConfigDTO> data = masterDataService.getAllMasterConfigs();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@GetMapping(path = "/masterconfigs/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getRecordForMasterConfigById(@PathVariable String id) {
		MasterConfigDTO data = masterDataService.getMasterConfigById(id);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@PostMapping(path = "/masterconfigs", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addRecordForMasterConfig(@RequestBody MasterConfigDTO dto) {
		MasterConfigDTO data = masterDataService.addMasterConfig(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(data.getId())
				.toUri();
		return ResponseEntity.created(location).body(data);
	}

	@PutMapping(path = "/masterconfigs", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateRecordForMasterConfig(@RequestBody MasterConfigDTO dto) {
		MasterConfigDTO data = masterDataService.updateMasterConfig(dto);
		return ResponseEntity.status(HttpStatus.OK).body("MasterConfig updated " + data);
	}

	@DeleteMapping(path = "/masterconfigs/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> deleteRecordForMasterConfig(@PathVariable String id) {
		boolean data = masterDataService.deleteMasterConfig(id);
		if (data) {
			ResponseEntity.status(HttpStatus.OK).body("MasterConfig deleted successfully");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in deleting MasterProductApplicationScheme.");
	}

	@GetMapping(path = "/application/forms", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForProductApplicationScheme() {
		List<MasterProductApplicationSchemeDTO> data = masterDataService.getAllMasterProductApplicationSchemes();
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@GetMapping(path = "/application/meta/{applicationId}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsForProductApplicationScheme(@PathVariable String applicationId) {
		List<MasterConfigWithMetaDTO> data = masterDataServiceCustom.getResponseForMasterConfigMeta(applicationId);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}
	
	
	@GetMapping(path = "/applicationPages", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllRecordsWithApplicationAndPages() {
		AppPagesDto app = new AppPagesDto();
		app.setApplicationId("ebc1234def");
		app.setApplicationName("Demo Application");
		
		List<Page> pages = new ArrayList<>();
		Page page = new Page();
		page.setPageNumber("1");
		page.setPageName("demo Page 1");
		
		List<Field> f = new ArrayList<>();
		Field field = new Field();
		
		field.setDisplayname("First display name");
		field.setId("AbF123");
		field.setName("First DemoName");
		field.setRequired(true);
		field.setType("Text");
		field.setValidationMessage("No empty field required");
		field.setWidget("first widget");
		
		f.add(field);
		
		page.setFields(f);
		
		pages.add(page);
		
		app.setPages(pages);
				
		System.out.println("Dummy content data...."+app);
		return ResponseEntity.status(HttpStatus.OK).body(app);
	}
	
	
	
	
	

}
