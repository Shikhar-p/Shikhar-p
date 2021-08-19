package com.bizofit.digital.onboarding.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.bizofit.digital.onboarding.dto.CountryDTO;
import com.bizofit.digital.onboarding.dto.MasterConfigDTO;
import com.bizofit.digital.onboarding.dto.MasterFieldDTO;
import com.bizofit.digital.onboarding.dto.MasterFieldSetConfigDTO;
import com.bizofit.digital.onboarding.dto.MasterFieldSetDTO;
import com.bizofit.digital.onboarding.dto.MasterFieldSetGroupConfigDTO;
import com.bizofit.digital.onboarding.dto.MasterFieldSetGroupDTO;
import com.bizofit.digital.onboarding.dto.MasterInformationSubTypeDTO;
import com.bizofit.digital.onboarding.dto.MasterInformationTypeDTO;
import com.bizofit.digital.onboarding.dto.MasterProductApplicationSchemeDTO;
import com.bizofit.digital.onboarding.entity.MasterConfig;
import com.bizofit.digital.onboarding.entity.MasterCountry;
import com.bizofit.digital.onboarding.entity.MasterField;
import com.bizofit.digital.onboarding.entity.MasterFieldSet;
import com.bizofit.digital.onboarding.entity.MasterFieldSetConfig;
import com.bizofit.digital.onboarding.entity.MasterFieldSetGroup;
import com.bizofit.digital.onboarding.entity.MasterFieldSetGroupConfig;
import com.bizofit.digital.onboarding.entity.MasterInformationSubType;
import com.bizofit.digital.onboarding.entity.MasterInformationType;
import com.bizofit.digital.onboarding.entity.MasterProductApplicationScheme;
import com.bizofit.digital.onboarding.jpa.MasterConfigRepository;
import com.bizofit.digital.onboarding.jpa.MasterCountryRepository;
import com.bizofit.digital.onboarding.jpa.MasterFieldRepository;
import com.bizofit.digital.onboarding.jpa.MasterFieldSetConfigRepository;
import com.bizofit.digital.onboarding.jpa.MasterFieldSetGroupConfigRepository;
import com.bizofit.digital.onboarding.jpa.MasterFieldSetGroupRepository;
import com.bizofit.digital.onboarding.jpa.MasterFieldSetRepository;
import com.bizofit.digital.onboarding.jpa.MasterInformationSubTypeReposiroty;
import com.bizofit.digital.onboarding.jpa.MasterInformationTypeRepository;
import com.bizofit.digital.onboarding.jpa.MasterProductApplicationSchemeRepository;

@Service
public class MasterDataService {

	@Autowired
	private MasterCountryRepository masterCountryRepository;

	@Autowired
	private MasterInformationSubTypeReposiroty masterInformationSubTypeReposiroty;

	@Autowired
	private MasterFieldRepository masterFieldRepository;

	@Autowired
	private MasterFieldSetRepository masterFieldSetRepository;

	@Autowired
	private MasterFieldSetConfigRepository masterFieldSetConfigRepository;

	@Autowired
	private MasterFieldSetGroupRepository masterFieldSetGroupRepository;

	@Autowired
	private MasterFieldSetGroupConfigRepository masterFieldSetGroupConfigRepository;

	@Autowired
	private MasterInformationTypeRepository masterInformationTypeRepository;

	@Autowired
	private MasterProductApplicationSchemeRepository masterProductApplicationSchemeRepository;

	@Autowired
	private MasterConfigRepository masterConfigRepository;

	public List<CountryDTO> getAllCountry() {
		List<CountryDTO> countriesOut = new ArrayList<CountryDTO>();
		Iterable<MasterCountry> countries = masterCountryRepository.findAll();

		Consumer<MasterCountry> masterCountryConsumer = new Consumer<MasterCountry>() {
			@Override
			public void accept(MasterCountry masterCountry) {
				CountryDTO dt = new CountryDTO();
				dt.setCountryName(masterCountry.getCountryName());
				dt.setId(masterCountry.getId());
				countriesOut.add(dt);
			}
		};

		countries.forEach(masterCountryConsumer);

		return countriesOut;
	}

	public CountryDTO getCountryById(String id) {
		Optional<MasterCountry> country = masterCountryRepository.findById(id);
		if (country.isPresent()) {
			CountryDTO countryDTO = new CountryDTO();
			countryDTO.setId(country.get().getId());
			countryDTO.setCountryName(country.get().getCountryName());

			return countryDTO;
		}
		return null;
	}

	@Transactional
	public CountryDTO addCountry(CountryDTO countryDTO) {
		if (null == countryDTO.getId() || null == countryDTO.getCountryName()) {
			throw new IllegalStateException("Please send valid country to add");
		}

		Optional<MasterCountry> optionalMasterCountry = masterCountryRepository.findById(countryDTO.getId());
		if (!optionalMasterCountry.isPresent()) {
			throw new DuplicateKeyException("Duplicate record found with this key");
		}

		MasterCountry country = new MasterCountry();
		country.setId(countryDTO.getId());
		country.setCountryName(countryDTO.getCountryName());

		MasterCountry savedMasterCountry = masterCountryRepository.save(country);

		CountryDTO savedCountryDTO = new CountryDTO();
		savedCountryDTO.setId(savedMasterCountry.getId());
		savedCountryDTO.setCountryName(savedMasterCountry.getCountryName());
		return savedCountryDTO;
	}

	@Transactional
	public CountryDTO updateCountry(CountryDTO dto) {
		return masterCountryRepository.findById(dto.getId()).map(country -> {
			country.setCountryName(dto.getCountryName());

			MasterCountry savedMasterCountry = masterCountryRepository.save(country);
			CountryDTO countryDTO = new CountryDTO(savedMasterCountry.getCountryName(), savedMasterCountry.getId());
			return countryDTO;
		}).orElseThrow(() -> new NullPointerException("Country Id " + dto.getId() + " not found"));
	}

	@Transactional
	public Boolean deleteCountry(String id) {
		return masterCountryRepository.findById(id).map(country -> {
			masterCountryRepository.deleteById(id);
			return true;
		}).orElseThrow(() -> new NullPointerException("Country Id " + id + " not found"));
	}

	public List<MasterInformationSubTypeDTO> getAllMISTypes() {
		List<MasterInformationSubTypeDTO> typesOut = new ArrayList<MasterInformationSubTypeDTO>();
		Iterable<MasterInformationSubType> types = masterInformationSubTypeReposiroty.findAll();

		Consumer<MasterInformationSubType> masterSubTypeConsumer = new Consumer<MasterInformationSubType>() {
			@Override
			public void accept(MasterInformationSubType masterInformationSubType) {
				MasterInformationSubTypeDTO informationSubTypeDTO = new MasterInformationSubTypeDTO();
				informationSubTypeDTO.setId(masterInformationSubType.getId());
				informationSubTypeDTO.setType(masterInformationSubType.getType());
				typesOut.add(informationSubTypeDTO);
			}
		};

		types.forEach(masterSubTypeConsumer);

		return typesOut;
	}

	public MasterInformationSubTypeDTO getMISTypeById(String id) {
		Optional<MasterInformationSubType> subOptional = masterInformationSubTypeReposiroty.findById(id);
		if (subOptional.isPresent()) {
			MasterInformationSubTypeDTO dto = new MasterInformationSubTypeDTO();
			dto.setId(subOptional.get().getId());
			dto.setType(subOptional.get().getType());

			return dto;
		}
		return null;
	}

	@Transactional
	public MasterInformationSubTypeDTO addMasterInformationSubType(MasterInformationSubTypeDTO dto) {
		if (null == dto.getId() || null == dto.getType()) {
			throw new IllegalStateException("Please send valid MasterInformationSubType to add");
		}

		Optional<MasterInformationSubType> optionalMISType = masterInformationSubTypeReposiroty.findById(dto.getId());
		if (!optionalMISType.isPresent()) {
			throw new DuplicateKeyException("Duplicate record found with this key");
		}

		MasterInformationSubType subType = new MasterInformationSubType();
		subType.setId(dto.getId());
		subType.setType(dto.getType());

		MasterInformationSubType savedObject = masterInformationSubTypeReposiroty.save(subType);

		MasterInformationSubTypeDTO savedDTO = new MasterInformationSubTypeDTO();
		savedDTO.setId(savedObject.getId());
		savedDTO.setType(savedObject.getType());
		return savedDTO;
	}

	@Transactional
	public MasterInformationSubTypeDTO updateMasterInformationSubType(MasterInformationSubTypeDTO dto) {
		return masterInformationSubTypeReposiroty.findById(dto.getId()).map(subType -> {
			subType.setType(dto.getType());

			MasterInformationSubType savedObject = masterInformationSubTypeReposiroty.save(subType);
			MasterInformationSubTypeDTO subTypeDTO = new MasterInformationSubTypeDTO(savedObject.getType(),
					savedObject.getId());
			return subTypeDTO;
		}).orElseThrow(() -> new NullPointerException("MasterInformationSubType Id " + dto.getId() + " not found"));
	}

	@Transactional
	public Boolean deleteMasterInformationSubType(String id) {
		return masterInformationSubTypeReposiroty.findById(id).map(subType -> {
			masterInformationSubTypeReposiroty.deleteById(id);
			return true;
		}).orElseThrow(() -> new NullPointerException("MasterInformationSubType Id " + id + " not found"));
	}

	public List<MasterFieldDTO> getAllMasterFields() {
		List<MasterFieldDTO> fieldsOut = new ArrayList<MasterFieldDTO>();
		Iterable<MasterField> fields = masterFieldRepository.findAll();

		Consumer<MasterField> masterSubTypeConsumer = new Consumer<MasterField>() {
			@Override
			public void accept(MasterField masterField) {
				MasterFieldDTO dto = new MasterFieldDTO();
				dto.setId(masterField.getId());
				dto.setFieldName(masterField.getFieldName());
				dto.setFieldType(masterField.getFieldType());
				fieldsOut.add(dto);
			}
		};

		fields.forEach(masterSubTypeConsumer);

		return fieldsOut;
	}

	public MasterFieldDTO getMasterFieldById(String id) {
		Optional<MasterField> subOptional = masterFieldRepository.findById(id);
		if (subOptional.isPresent()) {
			MasterFieldDTO dto = new MasterFieldDTO();
			dto.setId(subOptional.get().getId());
			dto.setFieldName(subOptional.get().getFieldName());
			dto.setFieldType(subOptional.get().getFieldType());

			return dto;
		}
		return null;
	}

	@Transactional
	public MasterFieldDTO addMasterField(MasterFieldDTO dto) {
		if (null == dto.getId() || null == dto.getFieldName()) {
			throw new IllegalStateException("Please send valid MasterField to add");
		}

		Optional<MasterField> optionalMISType = masterFieldRepository.findById(dto.getId());
		if (!optionalMISType.isPresent()) {
			throw new DuplicateKeyException("Duplicate record found with this key");
		}

		MasterField subType = new MasterField();
		subType.setId(dto.getId());
		subType.setFieldName(dto.getFieldName());
		subType.setFieldType(dto.getFieldType());
		

		MasterField savedObject = masterFieldRepository.save(subType);

		MasterFieldDTO savedDTO = new MasterFieldDTO();
		savedDTO.setId(savedObject.getId());
		savedDTO.setFieldName(savedObject.getFieldName());
		return savedDTO;
	}

	@Transactional
	public MasterFieldDTO updateMasterField(MasterFieldDTO dto) {
		return masterFieldRepository.findById(dto.getId()).map(subType -> {
			subType.setFieldName(dto.getFieldName());

			MasterField savedObject = masterFieldRepository.save(subType);
			MasterFieldDTO subTypeDTO = new MasterFieldDTO(savedObject.getFieldName(), savedObject.getId(),savedObject.getFieldType());
			return subTypeDTO;
		}).orElseThrow(() -> new NullPointerException("MasterField Id " + dto.getId() + " not found"));
	}

	@Transactional
	public Boolean deleteMasterField(String id) {
		return masterFieldRepository.findById(id).map(subType -> {
			masterFieldRepository.deleteById(id);
			return true;
		}).orElseThrow(() -> new NullPointerException("MasterField Id " + id + " not found"));
	}

	public List<MasterFieldSetDTO> getAllMasterFieldSets() {
		List<MasterFieldSetDTO> fieldsOut = new ArrayList<MasterFieldSetDTO>();
		Iterable<MasterFieldSet> fields = masterFieldSetRepository.findAll();

		Consumer<MasterFieldSet> masterConsumer = new Consumer<MasterFieldSet>() {
			@Override
			public void accept(MasterFieldSet masterFieldSet) {
				MasterFieldSetDTO dto = new MasterFieldSetDTO();
				dto.setId(masterFieldSet.getId());
				dto.setFieldSetName(masterFieldSet.getFieldSetName());
				fieldsOut.add(dto);
			}
		};

		fields.forEach(masterConsumer);

		return fieldsOut;
	}

	public MasterFieldSetDTO getMasterFieldSetById(String id) {
		Optional<MasterFieldSet> subOptional = masterFieldSetRepository.findById(id);
		if (subOptional.isPresent()) {
			MasterFieldSetDTO dto = new MasterFieldSetDTO();
			dto.setId(subOptional.get().getId());
			dto.setFieldSetName(subOptional.get().getFieldSetName());

			return dto;
		}
		return null;
	}

	@Transactional
	public MasterFieldSetDTO addMasterFieldSet(MasterFieldSetDTO dto) {
		if (null == dto.getId() || null == dto.getFieldSetName()) {
			throw new IllegalStateException("Please send valid MasterFieldSet to add");
		}

		Optional<MasterFieldSet> optionalMISType = masterFieldSetRepository.findById(dto.getId());
		if (!optionalMISType.isPresent()) {
			throw new DuplicateKeyException("Duplicate record found with this key");
		}

		MasterFieldSet subType = new MasterFieldSet();
		subType.setId(dto.getId());
		subType.setFieldSetName(dto.getFieldSetName());

		MasterFieldSet savedObject = masterFieldSetRepository.save(subType);

		MasterFieldSetDTO savedDTO = new MasterFieldSetDTO();
		savedDTO.setId(savedObject.getId());
		savedDTO.setFieldSetName(savedObject.getFieldSetName());
		return savedDTO;
	}

	@Transactional
	public MasterFieldSetDTO updateMasterFieldSet(MasterFieldSetDTO dto) {
		return masterFieldSetRepository.findById(dto.getId()).map(subType -> {
			subType.setFieldSetName(dto.getFieldSetName());

			MasterFieldSet savedObject = masterFieldSetRepository.save(subType);
			MasterFieldSetDTO subTypeDTO = new MasterFieldSetDTO(savedObject.getFieldSetName(), savedObject.getId());
			return subTypeDTO;
		}).orElseThrow(() -> new NullPointerException("MasterFieldSet Id " + dto.getId() + " not found"));
	}

	@Transactional
	public Boolean deleteMasterFieldSet(String id) {
		return masterFieldRepository.findById(id).map(subType -> {
			masterFieldRepository.deleteById(id);
			return true;
		}).orElseThrow(() -> new NullPointerException("MasterFieldSet Id " + id + " not found"));
	}

	public List<MasterFieldSetConfigDTO> getAllMasterFieldSetConfigs() {
		List<MasterFieldSetConfigDTO> fieldsOut = new ArrayList<MasterFieldSetConfigDTO>();
		Iterable<MasterFieldSetConfig> fields = masterFieldSetConfigRepository.findAll();

		Consumer<MasterFieldSetConfig> masterConsumer = new Consumer<MasterFieldSetConfig>() {
			@Override
			public void accept(MasterFieldSetConfig masterFieldSet) {
				MasterFieldSetConfigDTO dto = new MasterFieldSetConfigDTO();
				dto.setId(masterFieldSet.getId());
				dto.setConditionIsMandatory(masterFieldSet.getConditionIsMandatory());
				dto.setIsMandatory(masterFieldSet.getIsMandatory());
				dto.setOrder(masterFieldSet.getOrder());
				dto.setVersion(masterFieldSet.getVersion());
				fieldsOut.add(dto);
			}
		};

		fields.forEach(masterConsumer);

		return fieldsOut;
	}

	public MasterFieldSetConfigDTO getMasterFieldSetConfigById(String id) {
		Optional<MasterFieldSetConfig> subOptional = masterFieldSetConfigRepository.findById(id);
		if (subOptional.isPresent()) {
			MasterFieldSetConfigDTO dto = new MasterFieldSetConfigDTO();
			dto.setId(subOptional.get().getId());
			dto.setConditionIsMandatory(subOptional.get().getConditionIsMandatory());
			dto.setIsMandatory(subOptional.get().getIsMandatory());
			dto.setOrder(subOptional.get().getOrder());
			dto.setVersion(subOptional.get().getVersion());

			return dto;
		}
		return null;
	}

	@Transactional
	public MasterFieldSetConfigDTO addMasterFieldSetConfig(MasterFieldSetConfigDTO dto) {
		if (null == dto.getId() || null == dto.getConditionIsMandatory() || null == dto.getIsMandatory()) {
			throw new IllegalStateException("Please send valid MasterFieldSetConfig to add");
		}

		Optional<MasterFieldSetConfig> optionalMISType = masterFieldSetConfigRepository.findById(dto.getId());
		if (!optionalMISType.isPresent()) {
			throw new DuplicateKeyException("Duplicate record found with this key");
		}

		MasterFieldSetConfig subType = new MasterFieldSetConfig();
		subType.setId(dto.getId());
		subType.setConditionIsMandatory(dto.getConditionIsMandatory());
		subType.setIsMandatory(dto.getIsMandatory());
		subType.setOrder(dto.getOrder());
		subType.setVersion(dto.getVersion());

		MasterFieldSetConfig savedObject = masterFieldSetConfigRepository.save(subType);

		MasterFieldSetConfigDTO savedDTO = new MasterFieldSetConfigDTO();
		savedDTO.setId(savedObject.getId());
		savedDTO.setConditionIsMandatory(savedObject.getConditionIsMandatory());
		savedDTO.setIsMandatory(savedObject.getIsMandatory());
		savedDTO.setOrder(savedObject.getOrder());
		savedDTO.setVersion(savedObject.getVersion());
		return savedDTO;
	}

	@Transactional
	public MasterFieldSetConfigDTO updateMasterFieldSetConfig(MasterFieldSetConfigDTO dto) {
		return masterFieldSetConfigRepository.findById(dto.getId()).map(subType -> {
			subType.setConditionIsMandatory(dto.getConditionIsMandatory());
			subType.setIsMandatory(dto.getIsMandatory());
			subType.setOrder(dto.getOrder());
			subType.setVersion(dto.getVersion());

			MasterFieldSetConfig savedObject = masterFieldSetConfigRepository.save(subType);
			MasterFieldSetConfigDTO subTypeDTO = new MasterFieldSetConfigDTO(savedObject.getId(),
					savedObject.getConditionIsMandatory(), savedObject.getIsMandatory(), savedObject.getOrder(),
					savedObject.getVersion());
			return subTypeDTO;
		}).orElseThrow(() -> new NullPointerException("MasterFieldSetConfig Id " + dto.getId() + " not found"));
	}

	@Transactional
	public Boolean deleteMasterFieldSetConfig(String id) {
		return masterFieldRepository.findById(id).map(subType -> {
			masterFieldRepository.deleteById(id);
			return true;
		}).orElseThrow(() -> new NullPointerException("MasterFieldSetConfig Id " + id + " not found"));
	}

	public List<MasterFieldSetGroupDTO> getAllMasterFieldSetGroups() {
		List<MasterFieldSetGroupDTO> fieldsOut = new ArrayList<MasterFieldSetGroupDTO>();
		Iterable<MasterFieldSetGroup> fields = masterFieldSetGroupRepository.findAll();

		Consumer<MasterFieldSetGroup> masterConsumer = new Consumer<MasterFieldSetGroup>() {
			@Override
			public void accept(MasterFieldSetGroup fieldSetGroup) {
				MasterFieldSetGroupDTO dto = new MasterFieldSetGroupDTO();
				dto.setId(fieldSetGroup.getId());
				dto.setFieldSetGroupName(fieldSetGroup.getFieldSetGroupName());
				fieldsOut.add(dto);
			}
		};

		fields.forEach(masterConsumer);

		return fieldsOut;
	}

	public MasterFieldSetGroupDTO getMasterFieldSetGroupById(String id) {
		Optional<MasterFieldSetGroup> subOptional = masterFieldSetGroupRepository.findById(id);
		if (subOptional.isPresent()) {
			MasterFieldSetGroupDTO dto = new MasterFieldSetGroupDTO();
			dto.setId(subOptional.get().getId());
			dto.setFieldSetGroupName(subOptional.get().getFieldSetGroupName());

			return dto;
		}
		return null;
	}

	@Transactional
	public MasterFieldSetGroupDTO addMasterFieldSetGroup(MasterFieldSetGroupDTO dto) {
		if (null == dto.getId() || null == dto.getFieldSetGroupName()) {
			throw new IllegalStateException("Please send valid MasterFieldSetGroup to add");
		}

		Optional<MasterFieldSetGroup> optionalMISType = masterFieldSetGroupRepository.findById(dto.getId());
		if (!optionalMISType.isPresent()) {
			throw new DuplicateKeyException("Duplicate record found with this key");
		}

		MasterFieldSetGroup subType = new MasterFieldSetGroup();
		subType.setId(dto.getId());
		subType.setFieldSetGroupName(dto.getFieldSetGroupName());

		MasterFieldSetGroup savedObject = masterFieldSetGroupRepository.save(subType);

		MasterFieldSetGroupDTO savedDTO = new MasterFieldSetGroupDTO();
		savedDTO.setId(savedObject.getId());
		savedDTO.setFieldSetGroupName(savedObject.getFieldSetGroupName());
		return savedDTO;
	}

	@Transactional
	public MasterFieldSetGroupDTO updateMasterFieldSetGroup(MasterFieldSetGroupDTO dto) {
		return masterFieldSetGroupRepository.findById(dto.getId()).map(subType -> {
			subType.setFieldSetGroupName(dto.getFieldSetGroupName());

			MasterFieldSetGroup savedObject = masterFieldSetGroupRepository.save(subType);
			MasterFieldSetGroupDTO subTypeDTO = new MasterFieldSetGroupDTO(savedObject.getId(),
					savedObject.getFieldSetGroupName());
			return subTypeDTO;
		}).orElseThrow(() -> new NullPointerException("MasterFieldSetGroup Id " + dto.getId() + " not found"));
	}

	@Transactional
	public Boolean deleteMasterFieldSetGroup(String id) {
		return masterFieldSetGroupRepository.findById(id).map(subType -> {
			masterFieldSetGroupRepository.deleteById(id);
			return true;
		}).orElseThrow(() -> new NullPointerException("MasterFieldSetGroup Id " + id + " not found"));
	}

	public List<MasterFieldSetGroupConfigDTO> getAllMasterFieldSetGroupConfigs() {
		List<MasterFieldSetGroupConfigDTO> fieldsOut = new ArrayList<MasterFieldSetGroupConfigDTO>();
		Iterable<MasterFieldSetGroupConfig> fields = masterFieldSetGroupConfigRepository.findAll();

		Consumer<MasterFieldSetGroupConfig> masterConsumer = new Consumer<MasterFieldSetGroupConfig>() {
			@Override
			public void accept(MasterFieldSetGroupConfig fieldSetGroup) {
				MasterFieldSetGroupConfigDTO dto = new MasterFieldSetGroupConfigDTO();
				dto.setId(fieldSetGroup.getId());
				dto.setOrder(fieldSetGroup.getOrder());
				dto.setVersion(fieldSetGroup.getVersion());
				fieldsOut.add(dto);
			}
		};

		fields.forEach(masterConsumer);

		return fieldsOut;
	}

	public MasterFieldSetGroupConfigDTO getMasterFieldSetGroupConfigById(String id) {
		Optional<MasterFieldSetGroupConfig> subOptional = masterFieldSetGroupConfigRepository.findById(id);
		if (subOptional.isPresent()) {
			MasterFieldSetGroupConfigDTO dto = new MasterFieldSetGroupConfigDTO();
			dto.setId(subOptional.get().getId());
			dto.setOrder(subOptional.get().getOrder());
			dto.setVersion(subOptional.get().getVersion());

			return dto;
		}
		return null;
	}

	@Transactional
	public MasterFieldSetGroupConfigDTO addMasterFieldSetGroupConfig(MasterFieldSetGroupConfigDTO dto) {
		if (null == dto.getId() || null == dto.getOrder() || null == dto.getVersion()) {
			throw new IllegalStateException("Please send valid MasterFieldSetGroupConfig to add");
		}

		Optional<MasterFieldSetGroupConfig> optionalMISType = masterFieldSetGroupConfigRepository.findById(dto.getId());
		if (!optionalMISType.isPresent()) {
			throw new DuplicateKeyException("Duplicate record found with this key");
		}

		MasterFieldSetGroupConfig subType = new MasterFieldSetGroupConfig();
		subType.setId(dto.getId());
		subType.setOrder(dto.getOrder());
		subType.setVersion(dto.getVersion());

		MasterFieldSetGroupConfig savedObject = masterFieldSetGroupConfigRepository.save(subType);

		MasterFieldSetGroupConfigDTO savedDTO = new MasterFieldSetGroupConfigDTO();
		savedDTO.setId(savedObject.getId());
		savedDTO.setOrder(savedObject.getOrder());
		savedDTO.setVersion(savedObject.getVersion());

		return savedDTO;
	}

	@Transactional
	public MasterFieldSetGroupConfigDTO updateMasterFieldSetGroupConfig(MasterFieldSetGroupConfigDTO dto) {
		return masterFieldSetGroupConfigRepository.findById(dto.getId()).map(subType -> {
			subType.setOrder(dto.getOrder());
			subType.setVersion(dto.getVersion());

			MasterFieldSetGroupConfig savedObject = masterFieldSetGroupConfigRepository.save(subType);
			MasterFieldSetGroupConfigDTO subTypeDTO = new MasterFieldSetGroupConfigDTO(savedObject.getId(),
					savedObject.getOrder(), savedObject.getVersion());
			return subTypeDTO;
		}).orElseThrow(() -> new NullPointerException("MasterFieldSetGroupConfig Id " + dto.getId() + " not found"));
	}

	@Transactional
	public Boolean deleteMasterFieldSetGroupConfig(String id) {
		return masterFieldSetGroupConfigRepository.findById(id).map(subType -> {
			masterFieldSetGroupConfigRepository.deleteById(id);
			return true;
		}).orElseThrow(() -> new NullPointerException("MasterFieldSetGroupConfig Id " + id + " not found"));
	}

	public List<MasterInformationTypeDTO> getAllMasterInformationTypes() {
		List<MasterInformationTypeDTO> fieldsOut = new ArrayList<MasterInformationTypeDTO>();
		Iterable<MasterInformationType> fields = masterInformationTypeRepository.findAll();

		Consumer<MasterInformationType> masterConsumer = new Consumer<MasterInformationType>() {
			@Override
			public void accept(MasterInformationType informationType) {
				MasterInformationTypeDTO dto = new MasterInformationTypeDTO();
				dto.setId(informationType.getId());
				dto.setType(informationType.getType());
				fieldsOut.add(dto);
			}
		};

		fields.forEach(masterConsumer);

		return fieldsOut;
	}

	public MasterInformationTypeDTO getMasterInformationTypeById(String id) {
		Optional<MasterInformationType> subOptional = masterInformationTypeRepository.findById(id);
		if (subOptional.isPresent()) {
			MasterInformationTypeDTO dto = new MasterInformationTypeDTO();
			dto.setId(subOptional.get().getId());
			dto.setType(subOptional.get().getType());

			return dto;
		}
		return null;
	}

	@Transactional
	public MasterInformationTypeDTO addMasterInformationType(MasterInformationTypeDTO dto) {
		if (null == dto.getId() || null == dto.getType()) {
			throw new IllegalStateException("Please send valid MasterInformationType to add");
		}

		Optional<MasterInformationType> optionalMISType = masterInformationTypeRepository.findById(dto.getId());
		if (!optionalMISType.isPresent()) {
			throw new DuplicateKeyException("Duplicate record found with this key");
		}

		MasterInformationType subType = new MasterInformationType();
		subType.setId(dto.getId());
		subType.setType(dto.getType());

		MasterInformationType savedObject = masterInformationTypeRepository.save(subType);

		MasterInformationTypeDTO savedDTO = new MasterInformationTypeDTO();
		savedDTO.setId(savedObject.getId());
		savedDTO.setType(savedObject.getType());

		return savedDTO;
	}

	@Transactional
	public MasterInformationTypeDTO updateMasterInformationType(MasterInformationTypeDTO dto) {
		return masterInformationTypeRepository.findById(dto.getId()).map(subType -> {
			subType.setType(dto.getType());

			MasterInformationType savedObject = masterInformationTypeRepository.save(subType);
			MasterInformationTypeDTO subTypeDTO = new MasterInformationTypeDTO(savedObject.getId(),
					savedObject.getType());
			return subTypeDTO;
		}).orElseThrow(() -> new NullPointerException("MasterInformationType Id " + dto.getId() + " not found"));
	}

	@Transactional
	public Boolean deleteMasterInformationType(String id) {
		return masterInformationTypeRepository.findById(id).map(subType -> {
			masterInformationTypeRepository.deleteById(id);
			return true;
		}).orElseThrow(() -> new NullPointerException("MasterInformationType Id " + id + " not found"));
	}

	public List<MasterProductApplicationSchemeDTO> getAllMasterProductApplicationSchemes() {
		List<MasterProductApplicationSchemeDTO> fieldsOut = new ArrayList<MasterProductApplicationSchemeDTO>();
		Iterable<MasterProductApplicationScheme> fields = masterProductApplicationSchemeRepository.findAll();

		Consumer<MasterProductApplicationScheme> masterConsumer = new Consumer<MasterProductApplicationScheme>() {
			@Override
			public void accept(MasterProductApplicationScheme applicationScheme) {
				MasterProductApplicationSchemeDTO dto = new MasterProductApplicationSchemeDTO();
				dto.setFormId(applicationScheme.getId());
				dto.setFormName(applicationScheme.getName());
				fieldsOut.add(dto);
			}
		};

		fields.forEach(masterConsumer);

		return fieldsOut;
	}

	public MasterProductApplicationSchemeDTO getMasterProductApplicationSchemeById(String id) {
		Optional<MasterProductApplicationScheme> subOptional = masterProductApplicationSchemeRepository.findById(id);
		if (subOptional.isPresent()) {
			MasterProductApplicationSchemeDTO dto = new MasterProductApplicationSchemeDTO();
			dto.setFormId(subOptional.get().getId());
			dto.setFormName(subOptional.get().getName());

			return dto;
		}
		return null;
	}

	@Transactional
	public MasterProductApplicationSchemeDTO addMasterProductApplicationScheme(MasterProductApplicationSchemeDTO dto) {
		if (null == dto.getFormId() || null == dto.getFormName()) {
			throw new IllegalStateException("Please send valid MasterProductApplicationScheme to add");
		}

		Optional<MasterProductApplicationScheme> optionalMISType = masterProductApplicationSchemeRepository
				.findById(dto.getFormId());
		if (!optionalMISType.isPresent()) {
			throw new DuplicateKeyException("Duplicate record found with this key");
		}

		MasterProductApplicationScheme subType = new MasterProductApplicationScheme();
		subType.setId(dto.getFormId());
		subType.setName(dto.getFormName());

		MasterProductApplicationScheme savedObject = masterProductApplicationSchemeRepository.save(subType);

		MasterProductApplicationSchemeDTO savedDTO = new MasterProductApplicationSchemeDTO();
		savedDTO.setFormId(savedObject.getId());
		savedDTO.setFormName(savedObject.getName());

		return savedDTO;
	}

	@Transactional
	public MasterProductApplicationSchemeDTO updateMasterProductApplicationScheme(
			MasterProductApplicationSchemeDTO dto) {
		return masterProductApplicationSchemeRepository.findById(dto.getFormId()).map(subType -> {
			subType.setName(dto.getFormName());

			MasterProductApplicationScheme savedObject = masterProductApplicationSchemeRepository.save(subType);
			MasterProductApplicationSchemeDTO subTypeDTO = new MasterProductApplicationSchemeDTO();
			subTypeDTO.setFormId(savedObject.getId());
			subTypeDTO.setFormName(savedObject.getName());
			return subTypeDTO;
		}).orElseThrow(
				() -> new NullPointerException("MasterProductApplicationScheme Id " + dto.getFormId() + " not found"));
	}

	@Transactional
	public Boolean deleteMasterProductApplicationScheme(String id) {
		return masterProductApplicationSchemeRepository.findById(id).map(subType -> {
			masterProductApplicationSchemeRepository.deleteById(id);
			return true;
		}).orElseThrow(() -> new NullPointerException("MasterProductApplicationScheme Id " + id + " not found"));
	}

	/**
	 * 
	 */
	public List<MasterConfigDTO> getAllMasterConfigs() {
		List<MasterConfigDTO> fieldsOut = new ArrayList<MasterConfigDTO>();
		Iterable<MasterConfig> fields = masterConfigRepository.findAll();

		Consumer<MasterConfig> masterConsumer = new Consumer<MasterConfig>() {
			@Override
			public void accept(MasterConfig masterConfig) {
				MasterConfigDTO dto = new MasterConfigDTO();
				dto.setId(masterConfig.getId());
				dto.setOccurance(masterConfig.getOccurance());
				dto.setOrder(masterConfig.getOrder());
				dto.setApplicableCondition(masterConfig.getApplicableCondition());
				fieldsOut.add(dto);
			}
		};

		fields.forEach(masterConsumer);

		return fieldsOut;
	}

	public MasterConfigDTO getMasterConfigById(String id) {
		Optional<MasterConfig> subOptional = masterConfigRepository.findById(id);
		if (subOptional.isPresent()) {
			MasterConfigDTO dto = new MasterConfigDTO();
			dto.setId(subOptional.get().getId());
			dto.setApplicableCondition(subOptional.get().getApplicableCondition());
			dto.setOccurance(subOptional.get().getOccurance());
			dto.setOrder(subOptional.get().getOrder());

			return dto;
		}
		return null;
	}

	@Transactional
	public MasterConfigDTO addMasterConfig(MasterConfigDTO dto) {
		if (null == dto.getId() || null == dto.getApplicableCondition() || null == dto.getOccurance()
				|| null == dto.getOrder()) {
			throw new IllegalStateException("Please send valid MasterConfig to add");
		}

		Optional<MasterConfig> optionalMISType = masterConfigRepository.findById(dto.getId());
		if (!optionalMISType.isPresent()) {
			throw new DuplicateKeyException("Duplicate record found with this key");
		}

		MasterConfig subType = new MasterConfig();
		subType.setId(dto.getId());
		subType.setApplicableCondition(dto.getApplicableCondition());
		subType.setOccurance(dto.getOccurance());
		subType.setOrder(dto.getOrder());

		MasterConfig savedObject = masterConfigRepository.save(subType);

		MasterConfigDTO savedDTO = new MasterConfigDTO();
		savedDTO.setId(savedObject.getId());
		savedDTO.setApplicableCondition(savedObject.getApplicableCondition());
		savedDTO.setOccurance(savedObject.getOccurance());
		savedDTO.setOrder(savedObject.getOrder());

		return savedDTO;
	}

	@Transactional
	public MasterConfigDTO updateMasterConfig(MasterConfigDTO dto) {
		return masterConfigRepository.findById(dto.getId()).map(subType -> {
			subType.setApplicableCondition(dto.getApplicableCondition());
			subType.setOccurance(dto.getOccurance());
			subType.setOrder(dto.getOrder());

			MasterConfig savedObject = masterConfigRepository.save(subType);
			MasterConfigDTO subTypeDTO = new MasterConfigDTO(savedObject.getId(), savedObject.getApplicableCondition(),
					savedObject.getOccurance(), savedObject.getOrder());
			return subTypeDTO;
		}).orElseThrow(() -> new NullPointerException("MasterConfig Id " + dto.getId() + " not found"));
	}

	@Transactional
	public Boolean deleteMasterConfig(String id) {
		return masterConfigRepository.findById(id).map(subType -> {
			masterConfigRepository.deleteById(id);
			return true;
		}).orElseThrow(() -> new NullPointerException("MasterConfig Id " + id + " not found"));
	}
	
}
