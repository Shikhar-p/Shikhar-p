package com.bizofit.digital.onboarding.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bizofit.digital.onboarding.entity.MasterInformationSubType;

@Repository
public interface MasterInformationSubTypeReposiroty extends CrudRepository<MasterInformationSubType, String> {

}
