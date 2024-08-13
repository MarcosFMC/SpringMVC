package com.example.DentalClinicMVC.service.impl;

import com.example.DentalClinicMVC.entity.Patient;
import com.example.DentalClinicMVC.exceptions.ResourceNotFoundException;
import com.example.DentalClinicMVC.repository.PatientRepository;
import com.example.DentalClinicMVC.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements IPatientService {
    
    private PatientRepository patientRepository;
    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Override
    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }
    @Override
    public Optional<Patient> findById(Long id){
        return patientRepository.findById(id);
    }
    @Override
    public void update(Patient patient){
        patientRepository.save(patient);
    }
    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(id);

        if(patientOptional.isPresent()){
            patientRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("No se pudo eliminar el paciente con el id: " + id);
        }
    }
    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    @Override
    public Patient findByEmail(String email){
        return patientRepository.findByEmail(email);
    }
}
