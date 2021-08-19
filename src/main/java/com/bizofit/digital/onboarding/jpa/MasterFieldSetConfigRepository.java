package com.bizofit.digital.onboarding.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bizofit.digital.onboarding.entity.MasterFieldSetConfig;

@Repository
public interface MasterFieldSetConfigRepository extends CrudRepository<MasterFieldSetConfig, String> {

}
