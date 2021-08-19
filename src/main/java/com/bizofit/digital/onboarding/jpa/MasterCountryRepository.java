package com.bizofit.digital.onboarding.jpa;

import org.springframework.data.repository.CrudRepository;

import com.bizofit.digital.onboarding.entity.MasterCountry;

public interface MasterCountryRepository extends CrudRepository<MasterCountry, String> {

}
