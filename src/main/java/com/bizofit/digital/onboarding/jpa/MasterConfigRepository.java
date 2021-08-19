package com.bizofit.digital.onboarding.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bizofit.digital.onboarding.entity.MasterConfig;

@Repository
public interface MasterConfigRepository extends CrudRepository<MasterConfig, String> {
	
}
