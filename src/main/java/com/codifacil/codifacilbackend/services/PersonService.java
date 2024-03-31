package com.codifacil.codifacilbackend.services;

import com.codifacil.codifacilbackend.common.CommonService;
import com.codifacil.codifacilbackend.models.entity.Person;

import java.util.Optional;

public interface PersonService extends CommonService<Person, Long> {

    public Optional<Person> findByEmail(String email);

    public Optional<Person> findByDocumentNumber(String documentNumber);

}
