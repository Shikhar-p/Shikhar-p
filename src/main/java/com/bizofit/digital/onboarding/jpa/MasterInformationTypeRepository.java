package com.bizofit.digital.onboarding.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bizofit.digital.onboarding.entity.MasterInformationType;

@Repository
public interface MasterInformationTypeRepository extends CrudRepository<MasterInformationType, String> {

}
