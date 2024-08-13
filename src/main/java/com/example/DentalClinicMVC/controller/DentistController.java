package com.example.DentalClinicMVC.controller;


import com.example.DentalClinicMVC.entity.Dentist;
import com.example.DentalClinicMVC.exceptions.ResourceNotFoundException;
import com.example.DentalClinicMVC.service.IDentistService;
import com.example.DentalClinicMVC.service.impl.DentistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class DentistController {
    private final IDentistService dentistService;
    @Autowired
    public DentistController(DentistServiceImpl dentistService)
    {
        this.dentistService = dentistService;
    }
    @GetMapping
    public ResponseEntity<List<Dentist>> findAll(){
        return ResponseEntity.ok(dentistService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findById(@PathVariable Long id){
         Optional<Dentist> dentistOptional = dentistService.findById(id);

         if(dentistOptional.isPresent()){
             return ResponseEntity.ok(dentistOptional.get());
         }else {
             return ResponseEntity.notFound().build();
         }
    }
    @PostMapping
    public ResponseEntity<Dentist> save(@RequestBody Dentist dentist){
        return ResponseEntity.ok(dentistService.save(dentist));
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Dentist dentist){

        ResponseEntity<String> response;
        Optional<Dentist> dentistOptional = dentistService.findById(dentist.getId());

        if(dentistOptional.isPresent()){
            dentistService.update(dentist);
            response = ResponseEntity.ok("Se actualizo correctamente el odontologo con nombre: " + dentist.getName());
        }
        else {
            response = ResponseEntity.ok("No se encontro el odontologo con el id: " + dentist.getId());
        }

        return response;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        dentistService.delete(id);
        return ResponseEntity.ok("Se elimino el odontologo con el id: "+ id);
    }
    @GetMapping("/registration/{registration}")
    public ResponseEntity<Dentist> findByRegistration(@PathVariable Integer registration){

        Optional<Dentist> dentistOptional = dentistService.findByRegistration(registration);

        if(dentistOptional.isPresent()){
            return ResponseEntity.ok(dentistOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
