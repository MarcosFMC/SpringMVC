package com.example.DentalClinicMVC.repository;

import com.example.DentalClinicMVC.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistRepository extends JpaRepository<Dentist,Long> {
    @Query("SELECT d FROM Dentist d WHERE d.registration = ?1 ")
    Optional<Dentist> findByRegistration(Integer registration);
}
