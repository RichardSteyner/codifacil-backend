package com.codifacil.codifacilbackend.models.repository;

import com.codifacil.codifacilbackend.models.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    public Optional<Person> findByEmail(String email);

    public Optional<Person> findByDocumentNumber(String documentNumber);

}
