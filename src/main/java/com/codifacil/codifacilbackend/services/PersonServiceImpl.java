package com.codifacil.codifacilbackend.services;

import com.codifacil.codifacilbackend.common.CommonServiceImpl;
import com.codifacil.codifacilbackend.models.entity.Person;
import com.codifacil.codifacilbackend.models.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl extends CommonServiceImpl<Person, Long, PersonRepository>
        implements PersonService {

    @Override
    public Optional<Person> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Person> findByDocumentNumber(String documentNumber) {
        return repository.findByDocumentNumber(documentNumber);
    }
}
