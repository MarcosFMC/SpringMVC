package com.example.DentalClinicMVC.service.impl;

import com.example.DentalClinicMVC.entity.Dentist;
import com.example.DentalClinicMVC.exceptions.ResourceNotFoundException;
import com.example.DentalClinicMVC.repository.DentistRepository;
import com.example.DentalClinicMVC.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistServiceImpl implements IDentistService {

    private DentistRepository dentistRepository;

    @Autowired
    public DentistServiceImpl(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }
    @Override
    public Dentist save(Dentist dentist){
        return dentistRepository.save(dentist);
    }
    @Override
    public Optional<Dentist> findById(Long id){
        return dentistRepository.findById(id);
    }
    @Override
    public Optional<Dentist> findByRegistration(Integer registration) {
        return dentistRepository.findByRegistration(registration);
    }
    @Override
    public void update(Dentist dentist){
        dentistRepository.save(dentist);
    }
    @Override
    public void delete(Long id) throws ResourceNotFoundException{
        Optional<Dentist> dentistOptional = dentistRepository.findById(id);

        if(dentistOptional.isPresent()){
            dentistRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("No se pudo eliminar el odontologo con el id: " + id);
        }
    }
    @Override
    public List<Dentist> findAll(){
        return dentistRepository.findAll();
    }
}
