package com.example.DentalClinicMVC.service;

import com.example.DentalClinicMVC.entity.Patient;
import com.example.DentalClinicMVC.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Patient save(Patient p);
    Optional<Patient> findById(Long id);
    void update(Patient p);
    void delete(Long id) throws ResourceNotFoundException;
    List<Patient> findAll();
    Patient findByEmail(String email);
}
