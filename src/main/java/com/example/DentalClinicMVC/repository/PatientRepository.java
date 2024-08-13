package com.example.DentalClinicMVC.repository;


import com.example.DentalClinicMVC.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    public Patient findByEmail(String string);
}
