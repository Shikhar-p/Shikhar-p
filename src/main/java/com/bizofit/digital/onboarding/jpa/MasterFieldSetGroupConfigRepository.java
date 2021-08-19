package com.bizofit.digital.onboarding.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bizofit.digital.onboarding.entity.MasterFieldSetGroupConfig;

@Repository
public interface MasterFieldSetGroupConfigRepository extends CrudRepository<MasterFieldSetGroupConfig, String> {

}
