package com.codifacil.codifacilbackend.models.repository;

import com.codifacil.codifacilbackend.models.entity.QuickText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickTextRepository extends JpaRepository<QuickText, Long> {

    @Query("select q from QuickText q where q.owner.id=?1")
    public Iterable<QuickText> findByOwner(Long ownerId);

}
