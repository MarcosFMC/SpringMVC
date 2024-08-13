package com.example.DentalClinicMVC.controller;

import com.example.DentalClinicMVC.entity.Patient;
import com.example.DentalClinicMVC.exceptions.ResourceNotFoundException;
import com.example.DentalClinicMVC.service.IPatientService;
import com.example.DentalClinicMVC.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    private IPatientService patientService;
    @Autowired
    public PatientController(PatientServiceImpl patientService)
    {
        this.patientService = patientService;
    }
    @GetMapping
    public ResponseEntity<List<Patient>> findAll(){
        return ResponseEntity.ok(patientService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id){
        Optional<Patient> PatientOptional = patientService.findById(id);

        if(PatientOptional.isPresent()){
            return ResponseEntity.ok(PatientOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient Patient){
        return ResponseEntity.ok(patientService.save(Patient));
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Patient Patient){

        ResponseEntity<String> response;
        Optional<Patient> PatientOptional = patientService.findById(Patient.getId());

        if(PatientOptional.isPresent()){
            patientService.update(Patient);
            response = ResponseEntity.ok("Se actualizo correctamente el odontologo con nombre: " + Patient.getName());
        }
        else {
            response = ResponseEntity.ok("No se encontro el odontologo con el id: " + Patient.getId());
        }

        return response;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        patientService.delete(id);
        return ResponseEntity.ok("Se elimino el paciente con el id: " + id);
    }
}
