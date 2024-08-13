package com.example.DentalClinicMVC.service;

import com.example.DentalClinicMVC.entity.Dentist;
import com.example.DentalClinicMVC.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
public interface IDentistService {
    Dentist save(Dentist dentist);
    Optional<Dentist> findById(Long id);
    Optional<Dentist> findByRegistration(Integer registration);
    void update(Dentist dentist);
    void delete(Long id) throws ResourceNotFoundException;
    List<Dentist> findAll();
}
